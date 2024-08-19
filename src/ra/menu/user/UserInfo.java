package ra.menu.user;

import ra.entity.Customers;
import ra.designimpl.CustomersServiceImpl;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInfo {
    private static CustomersServiceImpl customerService = new CustomersServiceImpl();
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10,11}"); // Định dạng số điện thoại Việt Nam (10 hoặc 11 chữ số)
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); // Định dạng email

    public void display() {
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
                    displayUserInfo();
                    break;
                case 3:
                    editUserInfo();
                    break;
                case 4:
                    return; // Quay lại menu trước đó
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu cũ: ");
        String oldPassword = scanner.nextLine();
        System.out.print("Nhập mật khẩu mới: ");
        String newPassword = scanner.nextLine();

        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
            System.out.println("Tên đăng nhập và mật khẩu không được để trống.");
            return;
        }

        Optional<Customers> customerOpt = customerService.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customers customer = customerOpt.get();
            if (customer.getPassword().equals(oldPassword)) {
                customer.setPassword(newPassword);
                customerService.save(customer);
                System.out.println("Đổi mật khẩu thành công.");
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu cũ không đúng.");
            }
        } else {
            System.out.println("Tên đăng nhập không tồn tại.");
        }
    }

    private void displayUserInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();

        if (username.isEmpty()) {
            System.out.println("Tên đăng nhập không được để trống.");
            return;
        }

        Optional<Customers> customerOpt = customerService.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customers customer = customerOpt.get();
            System.out.println("Thông Tin Cá Nhân:");
            System.out.println("Họ: " + customer.getLastName());
            System.out.println("Tên: " + customer.getFirstName());
            System.out.println("Tên đăng nhập: " + customer.getUsername());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Số điện thoại: " + customer.getPhone());
            System.out.println("Địa chỉ: " + customer.getAddress());
        } else {
            System.out.println("Tên đăng nhập không tồn tại.");
        }
    }

    private void editUserInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();

        if (username.isEmpty()) {
            System.out.println("Tên đăng nhập không được để trống.");
            return;
        }

        Optional<Customers> customerOpt = customerService.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customers customer = customerOpt.get();
            System.out.print("Nhập họ mới (hiện tại: " + customer.getLastName() + "): ");
            String lastName = scanner.nextLine();
            System.out.print("Nhập tên mới (hiện tại: " + customer.getFirstName() + "): ");
            String firstName = scanner.nextLine();
            System.out.print("Nhập email mới (hiện tại: " + customer.getEmail() + "): ");
            String email = scanner.nextLine();
            System.out.print("Nhập số điện thoại mới (hiện tại: " + customer.getPhone() + "): ");
            String phone = scanner.nextLine();
            System.out.print("Nhập địa chỉ mới (hiện tại: " + customer.getAddress() + "): ");
            String address = scanner.nextLine();

            if (email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                System.out.println("Email, số điện thoại và địa chỉ không được để trống.");
                return;
            }

            if (!EMAIL_PATTERN.matcher(email).matches()) {
                System.out.println("Email không hợp lệ.");
                return;
            }

            if (!PHONE_PATTERN.matcher(phone).matches()) {
                System.out.println("Số điện thoại không hợp lệ.");
                return;
            }

            customer.setLastName(lastName);
            customer.setFirstName(firstName);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setAddress(address);

            customerService.save(customer);
            System.out.println("Chỉnh sửa thông tin cá nhân thành công.");
        } else {
            System.out.println("Tên đăng nhập không tồn tại.");
        }
    }
}
