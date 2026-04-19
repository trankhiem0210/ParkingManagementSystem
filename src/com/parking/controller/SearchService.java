package com.parking.controller;

import java.time.format.DateTimeFormatter;

import com.parking.models.ParkingTicket;
import com.parking.repository.DataStorage;

public class SearchService {
	private DataStorage storage = DataStorage.getInstance();

	public String searchVehicle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return "Vui lòng nhập từ khóa tìm kiếm (Biển số, Mã vé, hoặc Mã thẻ tháng)!";
		}

		String searchKey = keyword.trim().toLowerCase();
		
		// Nếu người dùng nhập mã thẻ tháng, tìm ra biển số xe của thẻ đó để dễ đối chiếu
		String plateFromCard = storage.getListCards().stream()
				.filter(c -> c.getCardId().toLowerCase().equals(searchKey))
				.map(c -> c.getPlateNumber().toLowerCase())
				.findFirst()
				.orElse(null);
		
		// Áp dụng từ khóa biển số xe nếu tìm thấy thẻ tháng, ngược lại dùng nguyên gốc
		String plateToSearch = (plateFromCard != null) ? plateFromCard : searchKey;

		StringBuilder resultBuilder = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		boolean isFound = false;

		// Tìm trong danh sách các xe ĐANG ĐỖ trong bãi
		for (ParkingTicket ticket : storage.getListTickets()) {
			String plate = ticket.getPlateNumber().toLowerCase();
			String ticketId = ticket.getTicketId().toLowerCase();
			
			if (plate.contains(plateToSearch) || ticketId.contains(searchKey)) {
				resultBuilder.append("[ĐANG ĐỖ] ")
							 .append("Biển số: ").append(ticket.getPlateNumber())
							 .append(" | Mã vé: ").append(ticket.getTicketId())
							 .append(" | Giờ vào: ").append(ticket.getEntryTime().format(formatter))
							 .append("\n");
				isFound = true;
			}
		}

		// Tìm trong danh sách LỊCH SỬ các xe đã rời bãi
		for (ParkingTicket ticket : storage.getListHistoryTickets()) {
			String plate = ticket.getPlateNumber().toLowerCase();
			String ticketId = ticket.getTicketId().toLowerCase();
			
			if (plate.contains(plateToSearch) || ticketId.contains(searchKey)) {
				resultBuilder.append("[ĐÃ RỜI BÃI] ")
							 .append("Biển số: ").append(ticket.getPlateNumber())
							 .append(" | Mã vé: ").append(ticket.getTicketId())
							 .append(" | Giờ vào: ").append(ticket.getEntryTime().format(formatter))
							 .append(" | Giờ ra: ").append(ticket.getExitTime() != null ? ticket.getExitTime().format(formatter) : "N/A")
							 .append(" | Phí: ").append(ticket.getTotalFee()).append(" VND\n");
				isFound = true;
			}
		}

		return isFound ? resultBuilder.toString() : "Không tìm thấy thông tin xe nào phù hợp với: " + keyword;
	}
}