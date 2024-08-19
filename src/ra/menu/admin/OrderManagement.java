package ra.menu.admin;

import java.util.Scanner;

public class OrderManagement {
    public void manage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Order Management");
            System.out.println("1. View Orders");
            System.out.println("2. Update Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Code to view orders
                    System.out.println("View Orders");
                    break;
                case 2:
                    // Code to update order
                    System.out.println("Update Order");
                    break;
                case 3:
                    // Code to delete order
                    System.out.println("Delete Order");
                    break;
                case 4:
                    System.out.println("Returning to Admin Menu...");
                    return; // Back to Admin Menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
