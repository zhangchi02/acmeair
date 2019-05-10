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
// this sourcecode is modified by Huawei Technologies Co., Ltd.
package com.acmeair.web.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.acmeair.entities.Flight;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name="Flight")
public class FlightInfo {
	
	@XmlElement(name="_id")
	private String flightId;	
	private String flightSegmentId;		
	private Date scheduledDepartureTime;
	private Date scheduledArrivalTime;
	private BigDecimal firstClassBaseCost;
	private BigDecimal economyClassBaseCost;
	private int numFirstClassSeats;
	private int numEconomyClassSeats;
	private String airplaneTypeId;
	private FlightSegmentInfo flightSegment;
	
	@XmlElement(name="pkey")
	private FlightPKInfo pkey;
	
	public FlightInfo(){
		
	}
	
	public FlightInfo(Flight flight){
		this.flightId = flight.getFlightId();
		this.flightSegmentId = flight.getFlightSegmentId();
		this.scheduledDepartureTime = flight.getScheduledDepartureTime();
		this.scheduledArrivalTime = flight.getScheduledArrivalTime();
		this.firstClassBaseCost = flight.getFirstClassBaseCost();
		this.economyClassBaseCost = flight.getEconomyClassBaseCost();
		this.numFirstClassSeats = flight.getNumFirstClassSeats();
		this.numEconomyClassSeats = flight.getNumEconomyClassSeats();
		this.airplaneTypeId = flight.getAirplaneTypeId();
		if(flight.getFlightSegment() != null){
			this.flightSegment = new FlightSegmentInfo(flight.getFlightSegment());
		} else {
			this.flightSegment = null;
		}
		this.pkey = new FlightPKInfo(this.flightSegmentId, this.flightId);
	}
	
	public String getFlightId() {
		return flightId;
	}
	public void setid(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightSegmentId() {
		return flightSegmentId;
	}
	public void setFlightSegmentId(String flightSegmentId) {
		this.flightSegmentId = flightSegmentId;
	}
	public Date getScheduledDepartureTime() {
		return scheduledDepartureTime;
	}
	public void setScheduledDepartureTime(Date scheduledDepartureTime) {
		this.scheduledDepartureTime = scheduledDepartureTime;
	}
	public Date getScheduledArrivalTime() {
		return scheduledArrivalTime;
	}
	public void setScheduledArrivalTime(Date scheduledArrivalTime) {
		this.scheduledArrivalTime = scheduledArrivalTime;
	}
	public BigDecimal getFirstClassBaseCost() {
		return firstClassBaseCost;
	}
	public void setFirstClassBaseCost(BigDecimal firstClassBaseCost) {
		this.firstClassBaseCost = firstClassBaseCost;
	}
	public BigDecimal getEconomyClassBaseCost() {
		return economyClassBaseCost;
	}
	public void setEconomyClassBaseCost(BigDecimal economyClassBaseCost) {
		this.economyClassBaseCost = economyClassBaseCost;
	}
	public int getNumFirstClassSeats() {
		return numFirstClassSeats;
	}
	public void setNumFirstClassSeats(int numFirstClassSeats) {
		this.numFirstClassSeats = numFirstClassSeats;
	}
	public int getNumEconomyClassSeats() {
		return numEconomyClassSeats;
	}
	public void setNumEconomyClassSeats(int numEconomyClassSeats) {
		this.numEconomyClassSeats = numEconomyClassSeats;
	}
	public String getAirplaneTypeId() {
		return airplaneTypeId;
	}
	public void setAirplaneTypeId(String airplaneTypeId) {
		this.airplaneTypeId = airplaneTypeId;
	}
	public FlightSegmentInfo getFlightSegment() {
		return flightSegment;
	}
	public void setFlightSegment(FlightSegmentInfo flightSegment) {
		this.flightSegment = flightSegment;
	}
	public FlightPKInfo getPkey(){
		return pkey;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FlightInfo that = (FlightInfo) o;
		return numFirstClassSeats == that.numFirstClassSeats &&
				numEconomyClassSeats == that.numEconomyClassSeats &&
				Objects.equals(flightId, that.flightId) &&
				Objects.equals(flightSegmentId, that.flightSegmentId) &&
				Objects.equals(scheduledDepartureTime, that.scheduledDepartureTime) &&
				Objects.equals(scheduledArrivalTime, that.scheduledArrivalTime) &&
				Objects.equals(firstClassBaseCost, that.firstClassBaseCost) &&
				Objects.equals(economyClassBaseCost, that.economyClassBaseCost) &&
				Objects.equals(airplaneTypeId, that.airplaneTypeId) &&
				Objects.equals(flightSegment, that.flightSegment) &&
				Objects.equals(pkey, that.pkey);
	}

	@Override
	public String toString() {
		return "FlightInfo{" +
				"flightId='" + flightId + '\'' +
				", flightSegmentId='" + flightSegmentId + '\'' +
				", scheduledDepartureTime=" + scheduledDepartureTime +
				", scheduledArrivalTime=" + scheduledArrivalTime +
				", firstClassBaseCost=" + firstClassBaseCost +
				", economyClassBaseCost=" + economyClassBaseCost +
				", numFirstClassSeats=" + numFirstClassSeats +
				", numEconomyClassSeats=" + numEconomyClassSeats +
				", airplaneTypeId='" + airplaneTypeId + '\'' +
				", flightSegment=" + flightSegment +
				", pkey=" + pkey +
				'}';
	}
}
