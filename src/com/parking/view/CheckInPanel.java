/**
 * 
 */
package com.parking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.parking.controller.CheckInService;
import com.parking.models.VehicleType;

/**
 * 
 */
public class CheckInPanel extends JPanel {
	private JTextField txtPlate;
	private JComboBox<VehicleType> cbVehicleType;
	private JTextField txtIdCard;
	private JButton btnCheckIn;
	private JTextField txtMessage;

	private CheckInService checkInService;

	public CheckInPanel() {
		checkInService = new CheckInService();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Tieu de
		JLabel lblTitle = new JLabel("QUẢN LÝ CHECK-IN", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		// Form nhap lieu
		JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 20));
		
		formPanel.add(new JLabel("Biển số xe (*):"));
		txtPlate = new JTextField();
		formPanel.add(txtPlate);

		formPanel.add(new JLabel("Loại xe:"));
		cbVehicleType = new JComboBox<>(VehicleType.values());
		formPanel.add(cbVehicleType);

		formPanel.add(new JLabel("Mã thẻ tháng (nếu có):"));
		txtIdCard = new JTextField();
		formPanel.add(txtIdCard);

		formPanel.add(new JLabel("")); // Placeholder
		btnCheckIn = new JButton("Xác nhận Check In");
		formPanel.add(btnCheckIn);

		add(formPanel, BorderLayout.CENTER);

		// Vung hien thi thong bao
		txtMessage = new JTextField(" ");
		txtMessage.setHorizontalAlignment(JTextField.CENTER);
		txtMessage.setEditable(false);
		txtMessage.setBorder(null);
		txtMessage.setOpaque(false);
		txtMessage.setFont(new Font("Arial", Font.ITALIC, 14));
		add(txtMessage, BorderLayout.SOUTH);

		// Xu ly su kien click
		btnCheckIn.addActionListener(e -> performCheckIn());
	}

	private void performCheckIn() {
		String plate = txtPlate.getText().trim();
		VehicleType type = (VehicleType) cbVehicleType.getSelectedItem();
		String idCard = txtIdCard.getText().trim();

		if (plate.isEmpty()) {
			txtMessage.setText("Vui lòng nhập biển số xe!");
			txtMessage.setForeground(Color.RED);
			return;
		}

		// Goi dich vu xu ly logic
		String result = checkInService.performCheckIn(plate, type, idCard);
		
		// Hien thi ket qua len UI
		txtMessage.setText(result);
		if (result.startsWith("Check-in thanh cong")) {
			txtMessage.setForeground(new Color(0, 153, 0)); // Mau xanh la
			txtPlate.setText("");
			txtIdCard.setText("");
		} else {
			txtMessage.setForeground(Color.RED);
		}
	}
}
