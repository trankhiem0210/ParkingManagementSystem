/**
 * 
 */
package com.parking.repository;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.parking.models.ParkingArea;
import com.parking.models.ParkingSlot;
import com.parking.models.ParkingTicket;
import com.parking.models.SubscriptionCard;
import com.parking.models.User;
import com.parking.models.UserRole;
import com.parking.models.VehicleType;

/**
 * 
 */
public class DataStorage {
	private static DataStorage instance;
	
	private List<User> listUsers;
	private List<ParkingSlot> listSlots;
	private List<SubscriptionCard> listCards;
	private List<ParkingTicket> listTickets;	//Xe dang o trong bai
	private List<ParkingTicket> listHistoryTickets;	//Xe da check-out
	private List<ParkingArea> listAreas;
	
	private DataStorage() {
		listUsers = new ArrayList<User>();
		listSlots = new ArrayList<ParkingSlot>();
		listCards = new ArrayList<SubscriptionCard>();
		listTickets = new ArrayList<ParkingTicket>();
		listHistoryTickets = new ArrayList<ParkingTicket>();
		listAreas = new ArrayList<ParkingArea>();
		
		//
		initDefaultData();
	}
	public static DataStorage getInstance() {
		if	(instance == null) {
			instance = new DataStorage();
		}
		return instance;
		
	}
	private void initDefaultData() {
		// TODO Auto-generated method stub
		//Them tai khoan admin va nhan vien mau
		listUsers.add(new User("Nam", "123", "Nguyen Van Nam", UserRole.ADMIN));
		listUsers.add(new User("Minh", "123", "Nguyen Van Minh", UserRole.STAFF));
		
		// Khoi tao cac the thang mau (Neu can)
		listCards.add(new SubscriptionCard("C-001", "ABC-123", "Khách Hàng A", VehicleType.CAR, LocalDate.now().plusMonths(1))); // Thẻ còn hạn 1 tháng
		listCards.add(new SubscriptionCard("C-002", "DEF-456", "Khách Hàng B", VehicleType.CAR, LocalDate.now().minusDays(5)));  // Thẻ đã hết hạn 5 ngày trước
		// Khoi tao cac khu vuc do xe
		listAreas.add(new ParkingArea("A", "Khu A", VehicleType.CAR));
		// listAreas.add(new ParkingArea("B", "Khu B", VehicleType.MOTORBIKE)); // Có thể thêm khu B, C nếu hệ thống có VehicleType tương ứng
		/**
		 * Khoi tao vi tri do
		 * VD: 10 slots cho khu A
		 */
		for (int i = 1; i < 10; i++) {
			listSlots.add(new ParkingSlot("A-"+ i, VehicleType.CAR, false));
		}
		
	}
	public List<User> getListUsers() {
		return listUsers;
	}
	public List<ParkingSlot> getListSlots() {
		return listSlots;
	}
	public List<SubscriptionCard> getListCards() {
		return listCards;
	}
	public List<ParkingTicket> getListTickets() {
		return listTickets;
	}
	public List<ParkingTicket> getListHistoryTickets() {
		return listHistoryTickets;
	}
	public List<ParkingArea> getListAreas() {
		return listAreas;
	}
}
