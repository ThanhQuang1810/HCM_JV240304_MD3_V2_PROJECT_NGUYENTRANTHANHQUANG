package ra.menu.admin;

import java.util.Scanner;

public class ProductManagement {
    public void manage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Product Management");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Code to add product
                    System.out.println("Add Product");
                    break;
                case 2:
                    // Code to view products
                    System.out.println("View Products");
                    break;
                case 3:
                    // Code to update product
                    System.out.println("Update Product");
                    break;
                case 4:
                    // Code to delete product
                    System.out.println("Delete Product");
                    break;
                case 5:
                    System.out.println("Returning to Admin Menu...");
                    return; // Back to Admin Menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
