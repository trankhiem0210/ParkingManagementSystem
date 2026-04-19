package com.parking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.parking.controller.SubscriptionService;
import com.parking.models.VehicleType;

public class SubscriptionPanel extends JPanel {
	private JTextField txtPlateNumber;
	private JTextField txtOwnerName;
	private JComboBox<VehicleType> cbVehicleType;
	private JComboBox<Integer> cbMonths;
	private JButton btnRegister;
	private JTextField txtMessage;

	private SubscriptionService subscriptionService;

	public SubscriptionPanel() {
		subscriptionService = new SubscriptionService();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Tiêu đề
		JLabel lblTitle = new JLabel("ĐĂNG KÝ VÉ THÁNG", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		// Form nhập liệu
		JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 20));

		formPanel.add(new JLabel("Biển số xe (*):"));
		txtPlateNumber = new JTextField();
		formPanel.add(txtPlateNumber);

		formPanel.add(new JLabel("Tên chủ xe (*):"));
		txtOwnerName = new JTextField();
		formPanel.add(txtOwnerName);

		formPanel.add(new JLabel("Loại xe:"));
		cbVehicleType = new JComboBox<>(VehicleType.values());
		formPanel.add(cbVehicleType);

		formPanel.add(new JLabel("Số tháng đăng ký:"));
		Integer[] months = {1, 2, 3, 6, 12};
		cbMonths = new JComboBox<>(months);
		formPanel.add(cbMonths);

		formPanel.add(new JLabel("")); // Placeholder
		btnRegister = new JButton("Đăng ký thẻ");
		formPanel.add(btnRegister);

		add(formPanel, BorderLayout.CENTER);

		// Vùng hiển thị thông báo
		txtMessage = new JTextField(" ");
		txtMessage.setHorizontalAlignment(JTextField.CENTER);
		txtMessage.setEditable(false);
		txtMessage.setBorder(null);
		txtMessage.setOpaque(false);
		txtMessage.setFont(new Font("Arial", Font.ITALIC, 14));
		add(txtMessage, BorderLayout.SOUTH);

		// Xử lý sự kiện click
		btnRegister.addActionListener(e -> performRegistration());
	}

	private void performRegistration() {
		String plateNumber = txtPlateNumber.getText().trim();
		String ownerName = txtOwnerName.getText().trim();
		VehicleType vehicleType = (VehicleType) cbVehicleType.getSelectedItem();
		int months = (int) cbMonths.getSelectedItem();

		String result = subscriptionService.registerSubscriptionCard(plateNumber, ownerName, vehicleType, months);

		txtMessage.setText(result);
		if (result.startsWith("Đăng ký thành công")) {
			txtMessage.setForeground(new Color(0, 153, 0)); // Màu xanh lá
			txtPlateNumber.setText("");
			txtOwnerName.setText("");
			cbMonths.setSelectedIndex(0);
		} else {
			txtMessage.setForeground(Color.RED);
		}
	}
}