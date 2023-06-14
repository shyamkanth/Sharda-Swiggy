import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String[] menu = {
                "1. Pizza",
                "2. Burger",
                "3. Salad",
                "4. Pasta",
                "5. Sandwich"
        };

        System.out.println("Menu:");
        for (String item : menu) {
            System.out.println(item);
        }

        System.out.print("Enter your choice (1-5): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= 5) {
            String foodItem = menu[choice - 1].substring(3);
            System.out.println("Your order of " + foodItem + " has been accepted!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

        scanner.close();
    }
}

