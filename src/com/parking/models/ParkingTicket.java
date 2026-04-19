/**
 * 
 */
package com.parking.models;

import java.time.LocalDateTime;

/**
 * 
 */
public class ParkingTicket {
	private String ticketId;	//
	private String plateNumber;	//Bien so xe
	private VehicleType vehicleType;	//
	private LocalDateTime entryTime;	//Thoi diem vao
	private LocalDateTime exitTime;	//Thoi diem ra
	private double totalFee;	//So tien tinh toan duoc 
	private boolean isCheckOut;
	/**
	 * @param ticketId
	 * @param plateNumber
	 * @param vehicleType
	 * @param entryTime
	 * @param exitTime
	 * @param totalFee
	 * @param isCheckOut
	 */
	public ParkingTicket(String ticketId, String plateNumber, VehicleType vehicleType, LocalDateTime entryTime,
			LocalDateTime exitTime, double totalFee, boolean isCheckOut) {
		super();
		this.ticketId = ticketId;
		this.plateNumber = plateNumber;
		this.vehicleType = vehicleType;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.totalFee = totalFee;
		this.isCheckOut = false;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}
	public LocalDateTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}
	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	public boolean isCheckOut() {
		return isCheckOut;
	}
	public void setCheckOut(boolean isCheckOut) {
		this.isCheckOut = isCheckOut;
	} 
	
}
