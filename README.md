# Hệ Thống Quản Lý Bãi Đỗ Xe (Parking Management System)

## Giới thiệu
Đây là một ứng dụng phần mềm được xây dựng bằng Java (sử dụng thư viện Java Swing cho giao diện người dùng) nhằm mục đích quản lý bãi đỗ xe một cách hiệu quả và tự động hóa các quy trình thủ công. Hệ thống cho phép nhân viên vận hành thực hiện các thao tác check-in, check-out cho các phương tiện, quản lý vé tháng và xem báo cáo doanh thu.

## Tính năng chính
- **Đăng nhập hệ thống:** Xác thực người dùng với các vai trò khác nhau (Ví dụ: Quản trị viên, Nhân viên).
- **Quản lý Check-In:**
  - Ghi nhận thông tin xe vào bãi (Biển số xe, Loại phương tiện).
  - Hỗ trợ kiểm tra và xử lý thẻ tháng (nếu có).
  - Tự động sắp xếp và chỉ định vị trí đỗ phù hợp cho phương tiện.
- **Quản lý Check-Out:**
  - Ghi nhận thông tin xe ra khỏi bãi thông qua mã vị trí đỗ và mã vé xe.
  - Tự động tính toán phí gửi xe dựa trên thời gian đỗ thực tế và chính sách giá (miễn phí đối với vé tháng còn hạn).
- **Đăng ký vé tháng (Subscription):**
  - Ghi nhận và đăng ký thẻ tháng mới cho các phương tiện.
  - Kiểm tra và ngăn chặn đăng ký trùng lặp mã thẻ hoặc biển số xe.
- **Báo cáo doanh thu:**
  - Thống kê tổng số lượt xe đã ra khỏi bãi.
  - Thống kê tổng doanh thu thu được theo thời gian thực.

## Công nghệ sử dụng
- **Ngôn ngữ lập trình:** Java
- **Giao diện người dùng (GUI):** Java Swing, Java AWT
- **Phương pháp thiết kế:** Thiết kế Hướng đối tượng (Object-Oriented Design - OOD)

## Cấu trúc dự án
Dự án được tổ chức theo kiến trúc phân lớp, tách biệt rõ ràng giữa giao diện, xử lý logic và dữ liệu:
- `com.parking.models`: Chứa các lớp đại diện cho thực thể trong hệ thống như `User`, `VehicleType`, `ParkingSlot`, `ParkingTicket`, `SubscriptionCard`, `PricingPolicy`,...
- `com.parking.view`: Chứa các thành phần giao diện người dùng (GUI) như `LoginFrame`, `MainFrame`, `CheckInPanel`, `CheckOutPanel`, `SubscriptionPanel`, `ReportPanel`.
- `com.parking.controller`: Chứa các dịch vụ xử lý nghiệp vụ logic như `CheckInService`, `CheckOutService`, `SubscriptionService`.
- `com.parking.repository`: Chứa các lớp chịu trách nhiệm lưu trữ và truy xuất xuất dữ liệu (ví dụ: `DataStorage`, `FileHandler`).

## Yêu cầu hệ thống
- **Java Development Kit (JDK):** Phiên bản 8 trở lên.
- **IDE:** Eclipse, IntelliJ IDEA, NetBeans, hoặc bất kỳ trình soạn thảo mã nào có hỗ trợ Java.

## Hướng dẫn cài đặt và khởi chạy
1. Tải toàn bộ mã nguồn của dự án về máy tính của bạn.
2. Mở thư mục dự án bằng IDE yêu thích của bạn (Eclipse/IntelliJ IDEA).
3. Đảm bảo cấu hình JDK cho project đã được thiết lập đúng.
4. Tìm đến lớp `MainFrame.java` nằm trong package `com.parking.view`.
5. Chạy (Run) chương trình từ lớp `MainFrame`. Màn hình đăng nhập (`LoginFrame`) sẽ hiển thị đầu tiên.
6. Nhập tài khoản và mật khẩu (đã được khởi tạo sẵn trong lớp `DataStorage`) để bắt đầu sử dụng hệ thống.

## Định hướng phát triển tương lai
- Lưu trữ dữ liệu lâu dài bằng Cơ sở dữ liệu (MySQL, SQL Server) thay vì lưu trữ trên RAM (`DataStorage`).
- Thêm tính năng in vé xe (hỗ trợ máy in nhiệt).
- Cải thiện giao diện người dùng (UI/UX) bằng cách tích hợp các thư viện giao diện hiện đại hơn như FlatLaf.
- Thêm hệ thống nhận diện biển số xe tự động (ALPR) bằng hình ảnh camera.