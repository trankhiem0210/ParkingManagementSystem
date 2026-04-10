/**
 * 
 */
package com.parking.controller;

import java.time.LocalDateTime;

import com.parking.models.ParkingSlot;
import com.parking.models.ParkingTicket;
import com.parking.models.SubscriptionCard;
import com.parking.models.VehicleType;
import com.parking.repository.DataStorage;

/**
 * 
 */
public class CheckInService {
	private DataStorage storage = DataStorage.getInstance();
	
	// Xu ly check-in
	public String performCheckIn(String plate, VehicleType type, String idCard) {
		// Kiem tra the thang (neu co)
		if (idCard != null && !idCard.trim().isEmpty()) {
			SubscriptionCard card = storage.getListCards().stream()
					.filter(c -> c.getCardId().equals(idCard))
					.findFirst().orElse(null);
			if (card == null || !card.getPlateNumber().equals(plate) || !card.isValid()) {
				return "The thang khong hop le";
			}
		}
		
		// Tu dong gan vi tri do (tim cho trong)
		ParkingSlot availableSlot = storage.getListSlots().stream()
				.filter(slot -> !slot.isOccupied() && slot.getAreaType().equals(type))
				.findFirst().orElse(null);
				
		// Kiem tra con cho trong cho loai xe nay
		if (availableSlot == null) {
			return "Het cho do cho loai xe " + type;
		}
		
		availableSlot.setOccupied(true);
		
		// Tao va luu thong tin ve xe vao he thong
		String ticketId = "T-" + System.currentTimeMillis(); // Tao ma ve tu dong
		ParkingTicket ticket = new ParkingTicket(ticketId, plate, type, LocalDateTime.now(), null, 0.0, false);
		storage.getListTickets().add(ticket);
		
		return "Check-in thanh cong. Vi tri do: " + availableSlot.getSlotId() + " (Mã vé: " + ticketId + ")";
	}
}
