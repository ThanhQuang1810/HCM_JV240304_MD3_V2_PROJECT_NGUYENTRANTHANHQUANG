package ra.menu.admin;

import ra.designimpl.CustomersServiceImpl;
import ra.entity.Customers;
import ra.util.IOFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserManagement {

    private CustomersServiceImpl customerService;
    private IOFile<Customers> ioFile;

    public UserManagement(CustomersServiceImpl customerService) {
        this.customerService = customerService;
        this.ioFile = new IOFile<>();
    }

    public void manage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("User Management");
            System.out.println("1. View Users");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewUsers();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    System.out.println("Returning to Admin Menu...");
                    return; // Back to Admin Menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewUsers() {
        List<Customers> customersList = customerService.getAll();

        if (customersList == null || customersList.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.printf("%-10s %-15s %-15s %-15s %-25s %-15s %-30s %-10s %-10s%n",
                "ID", "Username", "First Name", "Last Name", "Email", "Phone", "Address", "Blocked", "Role");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Customers customer : customersList) {
            System.out.printf("%-10d %-15s %-15s %-15s %-25s %-15s %-30s %-10s %-10s%n",
                    customer.getCustomerId(),
                    customer.getUsername(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.isBlocked() ? "Yes" : "No",
                    customer.isRole() ? "Admin" : "User");
        }
    }






    private void updateUser() {
        Scanner scanner = new Scanner(System.in);

        // Hiển thị danh sách người dùng
        viewUsers();

        System.out.println("Enter the ID of the user to update:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Optional<Customers> customerOpt = customerService.findById(userId);
        if (customerOpt.isPresent()) {
            Customers customer = customerOpt.get();
            System.out.println("Updating access for user with ID " + userId);

            System.out.print("Block user? (yes/no, leave empty to keep current): ");
            String blockChoice = scanner.nextLine();
            if (!blockChoice.isEmpty()) {
                customer.setBlocked(blockChoice.equalsIgnoreCase("yes"));
            }

            // Cập nhật quyền truy cập vào danh sách
            customerService.updateAccess(customer);

            // Lưu lại vào file
            try {
                ioFile.writeToFile(IOFile.USER_PATH, customerService.getAll());
                System.out.println("User access updated successfully.");
            } catch (IOException e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        } else {
            System.out.println("User with ID " + userId + " does not exist.");
        }
    }




    private void deleteUser() {
        Scanner scanner = new Scanner(System.in);

        // Hiển thị danh sách người dùng
        viewUsers();

        System.out.println("Enter the ID of the user to delete:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Optional<Customers> customerOpt = customerService.findById(userId);
        if (customerOpt.isPresent()) {
            customerService.delete(userId);
            try {
                ioFile.writeToFile(IOFile.USER_PATH, customerService.getAll());
                System.out.println("User with ID " + userId + " has been deleted.");
            } catch (IOException e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        } else {
            System.out.println("User with ID " + userId + " does not exist.");
        }
    }

}
