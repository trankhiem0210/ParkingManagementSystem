/**
 * 
 */
package com.parking.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.parking.models.ParkingTicket;
import com.parking.repository.DataStorage;

/**
 * 
 */
public class ReportPanel extends JPanel {
	private JLabel lblTotalRevenue;
	private JLabel lblTotalTickets;
	private JButton btnRefresh;

	public ReportPanel() {
		initComponents();
		refreshReport(); // Goi lan dau de hien thi so lieu hien tai
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Tieu de
		JLabel lblTitle = new JLabel("BÁO CÁO DOANH THU", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		// Thong tin thong ke
		JPanel infoPanel = new JPanel(new GridLayout(2, 1, 10, 20));
		
		lblTotalTickets = new JLabel("Tổng số lượt xe ra: 0", SwingConstants.CENTER);
		lblTotalTickets.setFont(new Font("Arial", Font.PLAIN, 18));
		infoPanel.add(lblTotalTickets);

		lblTotalRevenue = new JLabel("Tổng doanh thu: 0 VND", SwingConstants.CENTER);
		lblTotalRevenue.setFont(new Font("Arial", Font.BOLD, 20));
		infoPanel.add(lblTotalRevenue);

		add(infoPanel, BorderLayout.CENTER);

		// Nut lam moi
		btnRefresh = new JButton("Làm Mới Báo Cáo");
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 16));
		add(btnRefresh, BorderLayout.SOUTH);

		// Nhap chuot de lam moi tinh toan lai
		btnRefresh.addActionListener(e -> refreshReport());
	}

	private void refreshReport() {
		DataStorage storage = DataStorage.getInstance();
		
		int totalTickets = storage.getListHistoryTickets().size();
		
		// Tinh tong so tien (totalFee) trong lich su ve
		double totalRevenue = storage.getListHistoryTickets().stream()
				.mapToDouble(ParkingTicket::getTotalFee)
				.sum();

		lblTotalTickets.setText("Tổng số lượt xe ra: " + totalTickets);
		lblTotalRevenue.setText(String.format("Tổng doanh thu: %,.0f VND", totalRevenue));
	}
}
