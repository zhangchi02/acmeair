/*******************************************************************************
* Copyright (c) 2013-2015 IBM Corp.
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
// this sourcecode is modified by Huawei Technologies Co., Ltd.
package com.acmeair.morphia.entities;

import com.acmeair.entities.Customer;
import com.acmeair.entities.CustomerAddress;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Document(collection="customer")
@Entity(name = "customer")
public class CustomerImpl implements Customer, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String username;
	private String password;
	private String memberShipStatus;
	private int total_miles;
	private int miles_ytd;

	@Embedded
	private CustomerAddressImpl address;
	private String phoneNumber;
	private String phoneNumberType;
	
	public CustomerImpl() {
	}
	
	public CustomerImpl(String username, String password, String memberShipStatus, int total_miles, int miles_ytd, CustomerAddressImpl address, String phoneNumber, String phoneNumberType) {
		this.username = username;
		this.password = password;
		this.memberShipStatus = memberShipStatus;
		this.total_miles = total_miles;
		this.miles_ytd = miles_ytd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.phoneNumberType = phoneNumberType;
	}

	public String getCustomerId(){
		return username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMemberShipStatus() {
		return memberShipStatus;
	}
	
	public void setMemberShipStatus(String memberShipStatus) {
		this.memberShipStatus = memberShipStatus;
	}
	
	public int getTotal_miles() {
		return total_miles;
	}
	
	public void setTotal_miles(int total_miles) {
		this.total_miles = total_miles;
	}
	
	public int getMiles_ytd() {
		return miles_ytd;
	}
	
	public void setMiles_ytd(int miles_ytd) {
		this.miles_ytd = miles_ytd;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

	public CustomerAddressImpl getAddress() {
		return address;
	}

	public void setAddress(CustomerAddress address) {
		this.address = (CustomerAddressImpl) address;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", memberShipStatus="
				+ memberShipStatus + ", total_miles=" + total_miles + ", miles_ytd="
				+ miles_ytd + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", phoneNumberType=" + phoneNumberType + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerImpl other = (CustomerImpl) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (miles_ytd != other.miles_ytd)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (phoneNumberType != other.phoneNumberType)
			return false;
		if (memberShipStatus != other.memberShipStatus)
			return false;
		if (total_miles != other.total_miles)
			return false;
		return true;
	}

}
