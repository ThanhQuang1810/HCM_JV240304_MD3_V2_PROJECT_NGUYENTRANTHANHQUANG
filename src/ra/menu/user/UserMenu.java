package ra.menu.user;

import ra.entity.Customers;

import java.util.Scanner;
import ra.run.Main;


import ra.util.IOFile;

import java.io.IOException;
import java.util.List;

public class UserMenu {
    private Customers loggedInCustomer;
    private IOFile<Customers> ioFile = new IOFile<>();

    public UserMenu(Customers customer) {
        this.loggedInCustomer = customer;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Người Dùng");
            System.out.println("1. Trang Chính");
            System.out.println("2. Giỏ Hàng");
            System.out.println("3. Phản Hồi");
            System.out.println("4. Thông Tin Cá Nhân");
            System.out.println("5. Đăng Xuất");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Trang Chính
                    break;
                case 2:
                    // Giỏ Hàng
                    break;
                case 3:
                    // Phản Hồi
                    break;
                case 4:
                    personalInfoMenu();
                    break;
                case 5:
                    Main.logout(); // Đăng xuất
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void personalInfoMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Trang Thông Tin Cá Nhân");
            System.out.println("1. Đổi Mật Khẩu");
            System.out.println("2. Hiển Thị Thông Tin Cá Nhân");
            System.out.println("3. Chỉnh Sửa Thông Tin Cá Nhân");
            System.out.println("4. Quay Lại");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    displayPersonalInfo();
                    break;
                case 3:
                    editPersonalInfo();
                    break;
                case 4:
                    return; // Quay lại menu chính
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void changePassword() {
        Scanner scanner = new Scanner(System.in);

        // Yêu cầu nhập mật khẩu cũ
        System.out.print("Nhập mật khẩu cũ: ");
        String oldPassword = scanner.nextLine();

        // Kiểm tra mật khẩu cũ
        if (!loggedInCustomer.getPassword().equals(oldPassword)) {
            System.out.println("Mật khẩu cũ không đúng.");
            return;
        }

        // Nhập mật khẩu mới
        while (true) {
            System.out.print("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();

            // Kiểm tra mật khẩu mới không rỗng
            if (newPassword.isEmpty()) {
                System.out.println("Mật khẩu không được để trống.");
                continue;
            }

            // Cập nhật mật khẩu và lưu vào file
            loggedInCustomer.setPassword(newPassword);
            updateCustomerData();
            System.out.println("Đổi mật khẩu thành công.");
            break;
        }
    }


    private void displayPersonalInfo() {
        System.out.println("Thông Tin Cá Nhân:");
        System.out.println("Họ: " + loggedInCustomer.getLastName());
        System.out.println("Tên: " + loggedInCustomer.getFirstName());
        System.out.println("Tên đăng nhập: " + loggedInCustomer.getUsername());
        System.out.println("Email: " + loggedInCustomer.getEmail());
        System.out.println("Số điện thoại: " + loggedInCustomer.getPhone());
        System.out.println("Địa chỉ: " + loggedInCustomer.getAddress());
    }

    private void editPersonalInfo() {
        Scanner scanner = new Scanner(System.in);

        // Cập nhật thông tin cá nhân
        System.out.print("Nhập email mới (hiện tại: " + loggedInCustomer.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            loggedInCustomer.setEmail(newEmail);
        }

        System.out.print("Nhập số điện thoại mới (hiện tại: " + loggedInCustomer.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            loggedInCustomer.setPhone(newPhone);
        }

        System.out.print("Nhập địa chỉ mới (hiện tại: " + loggedInCustomer.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            loggedInCustomer.setAddress(newAddress);
        }

        // Lưu thông tin mới vào file
        updateCustomerData();
        System.out.println("Thông tin cá nhân đã được cập nhật.");
    }

    private void updateCustomerData() {
        // Đọc danh sách người dùng từ file
        List<Customers> customersList;
        try {
            customersList = ioFile.readFromFile(IOFile.USER_PATH);
            // Cập nhật thông tin người dùng trong danh sách
            for (int i = 0; i < customersList.size(); i++) {
                if (customersList.get(i).getUsername().equals(loggedInCustomer.getUsername())) {
                    customersList.set(i, loggedInCustomer);
                    break;
                }
            }
            // Ghi danh sách cập nhật vào file
            ioFile.writeToFile(IOFile.USER_PATH, customersList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
    }
}


