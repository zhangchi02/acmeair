/*******************************************************************************
* Copyright 2017 Huawei Technologies Co., Ltd
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/
package com.acmeair.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@Profile("SpringCloud")
class SpringAuthenticationCommand extends AuthenticationCommand {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationCommand.class);

  @Autowired
  private LoadBalancerClient loadBalancer;

  SpringAuthenticationCommand() {
    super(new RestTemplate());
  }

  @Override
  protected String getCustomerServiceAddress() {
    String address = loadBalancer.choose("customerServiceApp").getUri().toString();
    logger.info("Just get the address {} from LoadBalancer.", address);
    return address + "/rest";
  }

  @Override
  protected Object validationRequest(String sessionId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("sessionId", sessionId);

    return new HttpEntity<>(map, headers);
  }
}
