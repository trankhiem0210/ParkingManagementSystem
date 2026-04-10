/**
 * 
 */
package com.parking.controller;

import com.parking.models.SubscriptionCard;
import com.parking.repository.DataStorage;

/**
 * 
 */
public class SubscriptionService {
	private DataStorage storage = DataStorage.getInstance();
	
	// Dang ky ve thang moi
	public String registerSubscriptionCard(SubscriptionCard newCard) {
		if (newCard == null || newCard.getCardId() == null || newCard.getCardId().trim().isEmpty()) {
			return "Thong tin the khong hop le";
		}
		
		// Kiem tra xem the da ton tai chua (trung Card ID)
		boolean isCardExist = storage.getListCards().stream()
				.anyMatch(c -> c.getCardId().equals(newCard.getCardId()));
				
		if (isCardExist) {
			return "Ma the nay da ton tai trong he thong";
		}
		
		// Kiem tra bien so xe da duoc dang ky the thang chua
		boolean isPlateExist = storage.getListCards().stream()
				.anyMatch(c -> c.getPlateNumber().equals(newCard.getPlateNumber()));
				
		if (isPlateExist) {
			return "Bien so xe nay da duoc dang ky ve thang truoc do";
		}
		
		// Them the vao he thong luu tru
		storage.getListCards().add(newCard);
		return "Dang ky the thang thanh cong cho bien so: " + newCard.getPlateNumber();
	}
	
	// TODO: Them cac method de cap nhat trang thai the (Gia han the, huy the) 
	// Vi du: public String renewSubscription(String cardId) { ... }
}
