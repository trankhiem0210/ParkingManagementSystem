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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.parking.models.User;
import com.parking.repository.DataStorage;

/**
 * 
 */
public class LoginFrame extends JFrame {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JLabel lblMessage;

	public LoginFrame() {
		setTitle("Đăng Nhập Hệ Thống");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Giua man hinh
		setLayout(new BorderLayout());

		initComponents();
	}

	private void initComponents() {
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		mainPanel.add(lblTitle, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 20));
		
		formPanel.add(new JLabel("Tên đăng nhập:"));
		txtUsername = new JTextField();
		formPanel.add(txtUsername);

		formPanel.add(new JLabel("Mật khẩu:"));
		txtPassword = new JPasswordField();
		formPanel.add(txtPassword);

		formPanel.add(new JLabel("")); // Placeholder
		btnLogin = new JButton("Đăng Nhập");
		formPanel.add(btnLogin);

		mainPanel.add(formPanel, BorderLayout.CENTER);

		lblMessage = new JLabel(" ", SwingConstants.CENTER);
		lblMessage.setForeground(Color.RED);
		mainPanel.add(lblMessage, BorderLayout.SOUTH);

		add(mainPanel);

		// Su kien dang nhap
		btnLogin.addActionListener(e -> performLogin());
	}

	private void performLogin() {
		String username = txtUsername.getText().trim();
		String password = new String(txtPassword.getPassword());

		if (username.isEmpty() || password.isEmpty()) {
			lblMessage.setText("Vui lòng nhập đầy đủ thông tin!");
			return;
		}

		DataStorage storage = DataStorage.getInstance();
		
		// Tim kiem User trong DataStorage
		User loggedInUser = storage.getListUsers().stream()
				.filter(u -> u.getName().equals(username) && u.getPassword().equals(password))
				.findFirst().orElse(null);

		if (loggedInUser != null) {
			// Dang nhap thanh cong: Dong LoginFrame va mo MainFrame
			this.dispose(); 
			
			SwingUtilities.invokeLater(() -> {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);
			});
		} else {
			lblMessage.setText("Sai tên đăng nhập hoặc mật khẩu!");
		}
	}
}
