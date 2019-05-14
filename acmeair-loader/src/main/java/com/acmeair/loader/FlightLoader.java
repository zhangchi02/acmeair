/*******************************************************************************
* Copyright (c) 2013 IBM Corp.
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
package com.acmeair.loader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.math.*;

import com.acmeair.entities.AirportCodeMapping;
import com.acmeair.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlightLoader {

  @Value("${flights.max.per.segment:30}")
	private int maxFlightsPerSegment;
	
	@Autowired
	private FlightService flightService;

	public void loadFlights() throws Exception {
		InputStream csvInputStream = FlightLoader.class.getResourceAsStream("/mileage.csv");
		
		LineNumberReader lnr = new LineNumberReader(new InputStreamReader(csvInputStream));
		String line1 = lnr.readLine();
		StringTokenizer st = new StringTokenizer(line1, ",");
		ArrayList<AirportCodeMapping> airports = new ArrayList<AirportCodeMapping>();
		
		// read the first line which are airport names
		while (st.hasMoreTokens()) {
			AirportCodeMapping acm = flightService.createAirportCodeMapping(null, st.nextToken());
		//	acm.setAirportName(st.nextToken());
			airports.add(acm);
		}
		// read the second line which contains matching airport codes for the first line
		String line2 = lnr.readLine();
		st = new StringTokenizer(line2, ",");
		int ii = 0;
		while (st.hasMoreTokens()) {
			String airportCode = st.nextToken();
			airports.get(ii).setAirportCode(airportCode);
			ii++;
		}
		// read the other lines which are of format:
		// airport name, aiport code, distance from this airport to whatever airport is in the column from lines one and two
		String line;
		int flightNumber = 0;
		while (true) {
			line = lnr.readLine();
			if (line == null || line.trim().equals("")) {
				break;
			}
			st = new StringTokenizer(line, ",");
			String airportName = st.nextToken();
			String airportCode = st.nextToken();
			if (!alreadyInCollection(airportCode, airports)) {
				AirportCodeMapping acm = flightService.createAirportCodeMapping(airportCode, airportName);
				airports.add(acm);
			}
			int indexIntoTopLine = 0;
			while (st.hasMoreTokens()) {
				String milesString = st.nextToken();
				if (milesString.equals("NA")) {
					indexIntoTopLine++;
					continue;
				}
				int miles = Integer.parseInt(milesString);
				String toAirport = airports.get(indexIntoTopLine).getAirportCode();
				String flightId = "AA" + flightNumber;			
				flightService.storeFlightSegment(flightId, airportCode, toAirport, miles);
        for (int daysFromNow = 0; daysFromNow < maxFlightsPerSegment; daysFromNow++) {
					Date departureTime = Date.from(OffsetDateTime.now(ZoneId.of("Asia/Shanghai"))
							.truncatedTo(ChronoUnit.DAYS)
							.plusDays(daysFromNow)
							.toInstant());
					Date arrivalTime = getArrivalTime(departureTime, miles);
					flightService.createNewFlight(flightId, departureTime, arrivalTime, new BigDecimal(500), new BigDecimal(200), 10, 200, "B747");
					
				}
				flightNumber++;
				indexIntoTopLine++;
			}
		}
		
		for (int jj = 0; jj < airports.size(); jj++) {
			flightService.storeAirportMapping(airports.get(jj));
		}
		lnr.close();
	}
	
	private static Date getArrivalTime(Date departureTime, int mileage) {
		double averageSpeed = 600.0; // 600 miles/hours
		double hours = (double) mileage / averageSpeed; // miles / miles/hour = hours
		double partsOfHour = hours % 1.0;
		int minutes = (int)(60.0 * partsOfHour);
		Calendar c = Calendar.getInstance();
		c.setTime(departureTime);
		c.add(Calendar.HOUR, (int)hours);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}
	
	static private boolean alreadyInCollection(String airportCode, ArrayList<AirportCodeMapping> airports) {
		for (int ii = 0; ii < airports.size(); ii++) {
			if (airports.get(ii).getAirportCode().equals(airportCode)) {
				return true;
			}
		}
		return false;
	}
}
