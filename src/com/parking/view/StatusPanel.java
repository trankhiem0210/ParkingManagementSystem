package com.parking.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.parking.models.ParkingSlot;
import com.parking.models.VehicleType;
import com.parking.repository.DataStorage;

public class StatusPanel extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnRefresh;

	public StatusPanel() {
		initComponents();
		refreshData(); // Lần đầu mở tab sẽ tự động tải dữ liệu
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Tiêu đề
		JLabel lblTitle = new JLabel("TRẠNG THÁI BÃI XE", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		// Khởi tạo bảng dữ liệu
		String[] columnNames = {"Loại xe", "Tổng số chỗ", "Đang sử dụng", "Còn trống"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setEnabled(false); // Chỉ để hiển thị (không cho edit trực tiếp trên cell)

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		// Nút làm mới
		btnRefresh = new JButton("Làm mới trạng thái");
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRefresh.addActionListener(e -> refreshData());
		add(btnRefresh, BorderLayout.SOUTH);
	}

	private void refreshData() {
		DataStorage storage = DataStorage.getInstance();
		List<ParkingSlot> slots = storage.getListSlots();

		// Nhóm các slot đỗ xe theo loại phương tiện
		Map<VehicleType, List<ParkingSlot>> slotsByType = slots.stream()
				.collect(Collectors.groupingBy(ParkingSlot::getAreaType));

		tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng

		for (VehicleType type : VehicleType.values()) {
			List<ParkingSlot> typeSlots = slotsByType.getOrDefault(type, new ArrayList<>());
			long total = typeSlots.size();
			long occupied = typeSlots.stream().filter(ParkingSlot::isOccupied).count();
			long available = total - occupied;

			tableModel.addRow(new Object[]{type.name(), total, occupied, available});
		}
	}
}