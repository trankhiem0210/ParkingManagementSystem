/**
 * 
 */
package com.parking.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import com.parking.models.ParkingSlot;
import com.parking.models.ParkingTicket;
import com.parking.repository.DataStorage;

/**
 * 
 */
public class CheckOutService {
	private DataStorage storage = DataStorage.getInstance();
	
	// Xu ly check-out dua tren ma vi tri do va ma ve xe
	public String performCheckOut(String slotId, String ticketId) {
		// Tim vi tri do theo ID
		ParkingSlot slot = storage.getListSlots().stream()
				.filter(s -> s.getSlotId().equals(slotId))
				.findFirst().orElse(null);
				
		if (slot == null) {
			return "Khong tim thay vi tri do: " + slotId;
		}
		
		if (!slot.isOccupied()) {
			return "Vi tri do nay hien dang trong, khong the check-out";
		}
		
		// Tim ve xe tuong ung
		ParkingTicket ticket = storage.getListTickets().stream()
				.filter(t -> t.getTicketId().equals(ticketId) && !t.isCheckOut())
				.findFirst().orElse(null);
				
		if (ticket == null) {
			return "Khong tim thay ve xe hoac ve da duoc thanh toan: " + ticketId;
		}

		// Kiem tra xem xe co the thang (Subscription Card) hop le khong
		boolean hasValidSubscription = storage.getListCards().stream()
				.anyMatch(c -> c.getPlateNumber().equals(ticket.getPlateNumber()) && c.isValid());

		// Tinh toan thoi gian va chi phi
		ticket.setExitTime(LocalDateTime.now());
		double totalFee = 0.0;
		
		if (!hasValidSubscription) {
			long hours = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toHours();
			if (hours == 0) {
				hours = 1; // Thu phi it nhat la 1 gio cho khach le
			}
			totalFee = hours * 10000.0; // Gia su 10.000 VND / 1 gio
		}
		
		ticket.setTotalFee(totalFee);
		ticket.setCheckOut(true);
		
		// Chuyen ve xe sang lich su
		storage.getListTickets().remove(ticket);
		storage.getListHistoryTickets().add(ticket);

		// Giai phong vi tri do
		slot.setOccupied(false);
		return String.format("Check-out thanh cong! Số tiền cần thanh toán: %,.0f VND", totalFee);
	}
}
