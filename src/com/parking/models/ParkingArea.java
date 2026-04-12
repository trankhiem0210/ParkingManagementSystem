/**
 * 
 */
package com.parking.models;

/**
 * 
 */
public class ParkingArea {
	private String areaId;
	private String areaName;
	private VehicleType vehicleType; // Loại phương tiện dành riêng cho khu vực này

	public ParkingArea(String areaId, String areaName, VehicleType vehicleType) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.vehicleType = vehicleType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}