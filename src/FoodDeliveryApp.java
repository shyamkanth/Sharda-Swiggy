import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoodDeliveryApp {
    final List<Restaurant> restaurants;
    final List<Dish> dishes;

    public FoodDeliveryApp() {
        restaurants = new ArrayList<>();
        dishes = new ArrayList<>();
    }

    public void loadRestaurantsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String address = data[2];
                Restaurant restaurant = new Restaurant(id, name, address);
                restaurants.add(restaurant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDishesFromCSV(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        bufferedReader.readLine(); // Skip the header line

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");

            int id = Integer.parseInt(data[0]);
            int restaurantId = Integer.parseInt(data[1]);
            String name = data[2];
            double price = Double.parseDouble(data[3]); // Parse as double

            Dish dish = new Dish(id, restaurantId, name, price);
            dishes.add(dish);

//            System.out.println("ID value from CSV: " + id);
//            System.out.println("Restaurant ID value from CSV: " + restaurantId);
        }

        bufferedReader.close();
    }



    public void printRestaurantAndDishes() {
        System.out.println("Welcome to Swiggy Sharda. Here is the list of restaurants and dishes:");
        for (Restaurant restaurant : restaurants) {
            System.out.println("Restaurant ID: " + restaurant.getId());
            System.out.println("Restaurant Name: " + restaurant.getName());
            System.out.println("Restaurant Address: " + restaurant.getAddress());
            System.out.println("Dishes:");

            for (Dish dish : dishes) {
                if (dish.getRestaurantId() == restaurant.getId()) {
                    System.out.println("- Dish ID: " + dish.getId());
                    System.out.println("  Dish Name: " + dish.getName());
                    System.out.println("  Dish Price: " + dish.getPrice());
                }
            }

            System.out.println();
        }
    }

    private void placeOrder() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the restaurant ID: ");
        String restaurantIdStr = reader.readLine();

        // Validate restaurant ID input
        int restaurantId;
        try {
            restaurantId = Integer.parseInt(restaurantIdStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid restaurant ID. Please enter a valid integer.");
            return;
        }

        // Find the restaurant
        Restaurant selectedRestaurant = null;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                selectedRestaurant = restaurant;
                break;
            }
        }

        if (selectedRestaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }

        System.out.println("Restaurant: " + selectedRestaurant.getName());

        // Display the dishes for the selected restaurant
        System.out.println("Dishes:");
        for (Dish dish : dishes) {
            if (dish.getRestaurantId() == restaurantId) {
                System.out.println("ID: " + dish.getId() + ", Name: " + dish.getName() + ", Price: " + dish.getPrice());
            }
        }

        System.out.print("Enter the dish ID: ");
        String dishIdStr = reader.readLine();

        // Validate dish ID input
        int dishId;
        try {
            dishId = Integer.parseInt(dishIdStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid dish ID. Please enter a valid integer.");
            return;
        }

        // Find the dish
        Dish selectedDish = null;
        for (Dish dish : dishes) {
            if (dish.getId() == dishId && dish.getRestaurantId() == restaurantId) {
                selectedDish = dish;
                break;
            }
        }

        if (selectedDish == null) {
            System.out.println("Dish not found.");
            return;
        }

        System.out.println("Your order for " + selectedDish.getName() + " is placed, and the cost is " + selectedDish.getPrice() + " rupees.");
    }


    public static void main(String[] args) throws IOException {
        FoodDeliveryApp app = new FoodDeliveryApp();
        app.loadRestaurantsFromCSV("D:\\Coding-Materials\\Java\\ShardaSwiggy\\src\\restaurants.csv");
        app.loadDishesFromCSV("D:\\Coding-Materials\\Java\\ShardaSwiggy\\src\\dishes.csv");
        app.printRestaurantAndDishes();
        app.placeOrder();
    }
}
