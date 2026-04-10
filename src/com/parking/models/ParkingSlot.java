/**
 * 
 */
package com.parking.models;

/**
 * 
 */
public class ParkingSlot {
	private String slotId;	//Moi slot se co 1 id rieng
	private VehicleType areaType;	//Khu A cho oto, Khu B cho xe may
	private boolean isOccupied;	//Trang thai trong hoac da co xe
	/**
	 * @param slotId
	 * @param areaType
	 * @param isOccupied
	 */
	public ParkingSlot(String slotId, VehicleType areaType, boolean isOccupied) {
		super();
		this.slotId = slotId;
		this.areaType = areaType;
		this.isOccupied = isOccupied;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public VehicleType getAreaType() {
		return areaType;
	}
	public void setAreaType(VehicleType areaType) {
		this.areaType = areaType;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	
}
