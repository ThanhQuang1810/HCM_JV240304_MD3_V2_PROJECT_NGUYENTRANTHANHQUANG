package ra.menu.admin;

import java.util.Scanner;

public class CategoryManagement {
    public void manage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Category Management");
            System.out.println("1. Add Category");
            System.out.println("2. View Categories");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Code to add category
                    System.out.println("Add Category");
                    break;
                case 2:
                    // Code to view categories
                    System.out.println("View Categories");
                    break;
                case 3:
                    // Code to update category
                    System.out.println("Update Category");
                    break;
                case 4:
                    // Code to delete category
                    System.out.println("Delete Category");
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
