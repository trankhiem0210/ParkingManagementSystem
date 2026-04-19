/**
 * 
 */
package com.parking.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 * 
 */
public class MainFrame extends JFrame {

	public MainFrame() {
		// Thiet lap cac thuoc tinh co ban cho cua so chinh
		setTitle("Hệ Thống Quản Lý Bãi Đỗ Xe");
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Hien thi cua so o giua man hinh
		setLayout(new BorderLayout());

		initComponents();
	}

	private void initComponents() {
		// Thiet lap Panel phia tren chua nut Dang xuat
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnLogout = new JButton("Đăng xuất");
		btnLogout.addActionListener(e -> {
			this.dispose(); // Dong cua so chinh (MainFrame)
			SwingUtilities.invokeLater(() -> {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true); // Mo lai cua so dang nhap
			});
		});
		topPanel.add(btnLogout);
		add(topPanel, BorderLayout.NORTH);

		// Tao JTabbedPane de chua cac man hinh chuc nang theo dang Tab
		JTabbedPane tabbedPane = new JTabbedPane();

		// Khoi tao cac Panel chuc nang
		CheckInPanel checkInPanel = new CheckInPanel();
		CheckOutPanel checkOutPanel = new CheckOutPanel();
		ReportPanel reportPanel = new ReportPanel();
		SearchPanel searchPanel = new SearchPanel();
		StatusPanel statusPanel = new StatusPanel();
		SubscriptionPanel subscriptionPanel = new SubscriptionPanel();

		// Them cac Panel vao Tab
		tabbedPane.addTab("Quản lý Check-In", checkInPanel);
		tabbedPane.addTab("Quản lý Check-Out", checkOutPanel);
		tabbedPane.addTab("Đăng ký vé tháng", subscriptionPanel);
		tabbedPane.addTab("Tra cứu/Tìm kiếm", searchPanel);
		tabbedPane.addTab("Trạng thái bãi xe", statusPanel);
		tabbedPane.addTab("Báo Cáo Doanh Thu", reportPanel);

		// Them JTabbedPane vao vi tri trung tam cua MainFrame
		add(tabbedPane, BorderLayout.CENTER);
	}

	// Diem bat dau chuong trinh duoc chuyen sang man hinh dang nhap
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Thay vi mo MainFrame, ta mo LoginFrame
			LoginFrame loginFrame = new LoginFrame();
			loginFrame.setVisible(true);
		});
	}
}
