/**
 * 
 */
package com.parking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.parking.controller.CheckOutService;

/**
 * 
 */
public class CheckOutPanel extends JPanel {
	private JTextField txtSlotId;
	private JTextField txtTicketId;
	private JButton btnCheckOut;
	private JLabel lblMessage;

	private CheckOutService checkOutService;

	public CheckOutPanel() {
		checkOutService = new CheckOutService();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Tieu de
		JLabel lblTitle = new JLabel("QUẢN LÝ CHECK-OUT", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		// Form nhap lieu
		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 20));
		
		formPanel.add(new JLabel("Mã vị trí đỗ (*):"));
		txtSlotId = new JTextField();
		formPanel.add(txtSlotId);

		formPanel.add(new JLabel("Mã vé xe (*):"));
		txtTicketId = new JTextField();
		formPanel.add(txtTicketId);

		formPanel.add(new JLabel("")); // Placeholder
		btnCheckOut = new JButton("Xác nhận Check Out");
		formPanel.add(btnCheckOut);

		add(formPanel, BorderLayout.CENTER);

		// Vung hien thi thong bao
		lblMessage = new JLabel(" ", SwingConstants.CENTER);
		lblMessage.setFont(new Font("Arial", Font.ITALIC, 14));
		add(lblMessage, BorderLayout.SOUTH);

		// Xu ly su kien click
		btnCheckOut.addActionListener(e -> performCheckOut());
	}

	private void performCheckOut() {
		String slotId = txtSlotId.getText().trim();
		String ticketId = txtTicketId.getText().trim();

		if (slotId.isEmpty() || ticketId.isEmpty()) {
			lblMessage.setText("Vui lòng nhập đầy đủ mã vị trí đỗ và mã vé xe!");
			lblMessage.setForeground(Color.RED);
			return;
		}

		// Goi dich vu xu ly logic
		String result = checkOutService.performCheckOut(slotId, ticketId);
		
		// Hien thi ket qua len UI
		lblMessage.setText(result);
		if (result.startsWith("Check-out thanh cong")) {
			lblMessage.setForeground(new Color(0, 153, 0)); // Mau xanh la
			txtSlotId.setText("");
			txtTicketId.setText("");
		} else {
			lblMessage.setForeground(Color.RED);
		}
	}
}
