package ra.menu.admin;

import ra.designimpl.CustomersServiceImpl;
import ra.menu.admin.CategoryManagement;
import ra.menu.admin.OrderManagement;
import ra.menu.admin.ProductManagement;
import ra.menu.admin.UserManagement;

import java.util.Scanner;

public class AdminMenu {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        CustomersServiceImpl customerService = new CustomersServiceImpl();
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Manage Categories");
            System.out.println("2. Manage Orders");
            System.out.println("3. Manage Products");
            System.out.println("4. Manage Users");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    CategoryManagement categoryManagement = new CategoryManagement();
                    categoryManagement.manage();
                    break;
                case 2:
                    OrderManagement orderManagement = new OrderManagement();
                    orderManagement.manage();
                    break;
                case 3:
                    ProductManagement productManagement = new ProductManagement();
                    productManagement.manage();
                    break;
                case 4:
                    UserManagement userManagement = new UserManagement(customerService); // Tạo đối tượng với tham số
                    userManagement.manage();

                    break;
                case 5:
                    System.out.println("Logging out...");
                    return; // Log out and return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
