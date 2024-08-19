package ra.run;

import ra.designimpl.CustomersServiceImpl;
import ra.entity.Customers;
import ra.menu.admin.AdminMenu;
import ra.menu.user.UserMenu;
import ra.util.IOFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static CustomersServiceImpl customerService = new CustomersServiceImpl();
    private static boolean isAdmin;
    private static IOFile<Customers> ioFile = new IOFile<>();
    private static Customers loggedInCustomer;


    public static void logout() {
        loggedInCustomer = null;
        System.out.println("Đã đăng xuất.");
    }


    public static void main(String[] args) {
        try {
            List<Customers> customersList = ioFile.readFromFile(IOFile.USER_PATH);
            customerService.setCustomersList(customersList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Chọn một tùy chọn:");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("3. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (login()) {
                        if (isAdmin) {
                            AdminMenu adminMenu = new AdminMenu();
                            adminMenu.displayMenu();
                        } else {
                            UserMenu userMenu = new UserMenu(loggedInCustomer); // Truyền người dùng đã đăng nhập vào menu người dùng
                            userMenu.displayMenu();
                        }
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Thoát...");
                    return; // Exit the application
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    private static boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        // Đọc danh sách người dùng từ file
        List<Customers> customersList;
        try {
            customersList = ioFile.readFromFile(IOFile.USER_PATH);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
            return false;
        }

        // Kiểm tra tài khoản admin
        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Đăng nhập admin thành công.");
            isAdmin = true;
            return true;
        }

        // Kiểm tra các tài khoản người dùng khác
        Optional<Customers> customerOpt = customersList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .findFirst();
        if (customerOpt.isPresent()) {
            Customers customer = customerOpt.get();
            if (customer.getPassword().equals(password) && !customer.isBlocked()) {
                System.out.println("Đăng nhập người dùng thành công.");
                isAdmin = false;
                loggedInCustomer = customer;
                return true;
            } else if (customer.isBlocked()) {
                System.out.println("Tài khoản đã bị khóa.");
            } else {
                System.out.println("Mật khẩu không đúng.");
            }
        } else {
            System.out.println("Tên đăng nhập không tồn tại.");
        }

        return false;
    }





    private static void register() {
        Scanner scanner = new Scanner(System.in);
        String username, password, email, phone, address, lastName, firstName;

        // Đọc danh sách khách hàng từ file
        List<Customers> customersList;
        try {
            customersList = ioFile.readFromFile(IOFile.USER_PATH);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
            customersList = new ArrayList<>(); // Khởi tạo danh sách rỗng nếu không đọc được file
        }

        // Nhập họ
        while (true) {
            System.out.print("Nhập họ: ");
            lastName = scanner.nextLine();
            if (lastName.isEmpty()) {
                System.out.println("Họ không được để trống.");
                continue;
            }
            break;
        }

        // Nhập tên
        while (true) {
            System.out.print("Nhập tên: ");
            firstName = scanner.nextLine();
            if (firstName.isEmpty()) {
                System.out.println("Tên không được để trống.");
                continue;
            }
            break;
        }

        // Nhập tên đăng nhập
        while (true) {
            System.out.print("Nhập tên đăng nhập: ");
            username = scanner.nextLine();

            // Kiểm tra xem tên đăng nhập đã tồn tại hay chưa
            String finalUsername = username;
            boolean usernameExists = customersList.stream()
                    .anyMatch(customer -> customer.getUsername().equals(finalUsername));

            if (usernameExists) {
                System.out.println("Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                continue;
            }

            if (username.isEmpty()) {
                System.out.println("Tên đăng nhập không được để trống.");
                continue;
            }
            break;
        }

        // Nhập mật khẩu
        while (true) {
            System.out.print("Nhập mật khẩu: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Mật khẩu không được để trống.");
                continue;
            }
            break;
        }

        // Nhập email
        while (true) {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("Email không được để trống.");
                continue;
            }
            if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("Email không hợp lệ.");
                continue;
            }
            break;
        }

        // Nhập số điện thoại
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phone = scanner.nextLine();
            if (phone.isEmpty()) {
                System.out.println("Số điện thoại không được để trống.");
                continue;
            }
            if (!phone.matches("^\\d{10}$")) {
                System.out.println("Số điện thoại không hợp lệ.");
                continue;
            }
            break;
        }

        // Nhập địa chỉ
        while (true) {
            System.out.print("Nhập địa chỉ: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("Địa chỉ không được để trống.");
                continue;
            }
            break;
        }

        // Tạo đối tượng Customers và thêm vào danh sách
        Customers customer = new Customers();
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setBlocked(false); // Người dùng mới không bị khóa
        customer.setRole(false); // Vai trò là user

        customersList.add(customer);

        // Ghi danh sách khách hàng vào file
        try {
            ioFile.writeToFile(IOFile.USER_PATH, customersList);
            System.out.println("Đăng ký người dùng thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu dữ liệu: " + e.getMessage());
        }


        // kiểm tra có bao nhiêu tài khoản trog user.txt
        try {
            List<Customers> customersLists = ioFile.readFromFile(IOFile.USER_PATH);
            System.out.println("Danh sách người dùng sau khi đăng ký:");
            for (Customers customers : customersLists) {
                System.out.println(customers.getUsername());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }

    }



}


