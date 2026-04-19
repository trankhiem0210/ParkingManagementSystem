package com.parking.controller;

import java.time.LocalDate;
import java.util.List;

import com.parking.models.SubscriptionCard;
import com.parking.models.VehicleType;
import com.parking.repository.DataStorage;

public class SubscriptionService {
	private DataStorage storage = DataStorage.getInstance();

	public String registerSubscriptionCard(String plateNumber, String ownerName, VehicleType vehicleType, int months) {
		if (plateNumber == null || plateNumber.trim().isEmpty()) {
			return "Biển số xe không được để trống!";
		}
		if (ownerName == null || ownerName.trim().isEmpty()) {
			return "Tên chủ xe không được để trống!";
		}
		if (months <= 0) {
			return "Số tháng đăng ký phải lớn hơn 0!";
		}

		List<SubscriptionCard> cards = storage.getListCards();

		// Kiểm tra xem biển số xe đã có thẻ tháng còn hạn chưa
		boolean hasActiveCard = cards.stream()
				.anyMatch(c -> c.getPlateNumber().equalsIgnoreCase(plateNumber) && c.isValid());

		if (hasActiveCard) {
			return "Biển số xe này đã có thẻ tháng đang còn hiệu lực!";
		}

		// Tạo mã thẻ mới (Ví dụ: C-00X)
		String newCardId = String.format("C-%03d", cards.size() + 1);
		LocalDate expiryDate = LocalDate.now().plusMonths(months);

		SubscriptionCard newCard = new SubscriptionCard(newCardId, plateNumber.toUpperCase(), ownerName, vehicleType, expiryDate);
		cards.add(newCard);

		return "Đăng ký thành công! Mã thẻ: " + newCardId + " (Hạn: " + expiryDate + ")";
	}
}