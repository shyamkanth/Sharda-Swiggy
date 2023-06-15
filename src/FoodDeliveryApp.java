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

        bufferedReader.readLine();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");

            int id = Integer.parseInt(data[0]);
            int restaurantId = Integer.parseInt(data[1]);
            String name = data[2];
            double price = Double.parseDouble(data[3]);

            Dish dish = new Dish(id, restaurantId, name, price);
            dishes.add(dish);

        }

        bufferedReader.close();
    }



    public void printRestaurantAndDishes() {
        System.out.println();
        System.out.println();
        System.out.println("Foody : Your Favourite Dish At Your Doorstep Now.");
        System.out.println("Order Now. Please look at our different restaurants and variety of dishes.");
        System.out.println();
        System.out.println("*********************************************************");
        System.out.println();
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
            System.out.println("*********************************************************");
            System.out.println();
        }
    }

    private void placeOrder() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int restaurantId = -1;
        Restaurant selectedRestaurant = null;

        while (selectedRestaurant == null) {
            System.out.print("Enter the restaurant ID: ");
            String restaurantIdStr = reader.readLine();

            try {
                restaurantId = Integer.parseInt(restaurantIdStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid restaurant ID. Please enter a valid integer.");
                continue;
            }


            for (Restaurant restaurant : restaurants) {
                if (restaurant.getId() == restaurantId) {
                    selectedRestaurant = restaurant;
                    break;
                }
            }

            if (selectedRestaurant == null) {
                System.out.println("Restaurant not found. Please enter a valid restaurant ID.");
            }
        }
        System.out.println();
        System.out.println("*********************************************************");
        System.out.println("Restaurant: " + selectedRestaurant.getName());


        System.out.println("Dishes:");
        for (Dish dish : dishes) {
            if (dish.getRestaurantId() == restaurantId) {
                System.out.println("ID: " + dish.getId() + " | Name: " + dish.getName() + " | Price: " + dish.getPrice());
            }
        }
        System.out.println("*********************************************************");
        System.out.println();
        int dishId;
        Dish selectedDish = null;

        while (selectedDish == null) {
            System.out.print("Enter the dish ID: ");
            String dishIdStr = reader.readLine();


            try {
                dishId = Integer.parseInt(dishIdStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid dish ID. Please enter a valid integer.");
                continue;
            }

            for (Dish dish : dishes) {
                if (dish.getId() == dishId && dish.getRestaurantId() == restaurantId) {
                    selectedDish = dish;
                    break;
                }
            }

            if (selectedDish == null) {
                System.out.println("Dish not found. Please enter a valid dish ID.");
            }
        }
        System.out.println();
        System.out.println("Great choice! Your order for " + selectedDish.getName() + " is now ready to prepare.");
        System.out.println();
        System.out.print("Please enter your name: ");
        String userName = reader.readLine();
        System.out.println();
        System.out.println("Thanks, " + userName + ". Can you provide us your address for delivery?");
        System.out.print("Address: ");
        String address = reader.readLine();
        System.out.println();
        System.out.println("Thanks, " + userName + ". Can you provide us your phone number for the delivery partner to contact you when reached?");
        System.out.print("Phone Number: ");
        String phoneNumber = reader.readLine();
        System.out.println();
        // Generate a random delivery time between 30 to 45 minutes
        int deliveryTime = (int) (Math.random() * 16) + 30;
        System.out.println();
        System.out.println("Hey, " + userName + "! Thanks for ordering " + selectedDish.getName() +
                " from " + selectedRestaurant.getName() + ".");
        System.out.println("Your Subtotal is " + selectedDish.getPrice() + " rupees.");
        System.out.println("Your order will be delivered to " + address +
                " in approximately " + deliveryTime + " minutes.");
        System.out.println("Happy Eating!");
    }


    public static void main(String[] args) throws IOException {
        FoodDeliveryApp app = new FoodDeliveryApp();
        app.loadRestaurantsFromCSV("D:\\Coding-Materials\\Java\\ShardaSwiggy\\src\\restaurants.csv");
        app.loadDishesFromCSV("D:\\Coding-Materials\\Java\\ShardaSwiggy\\src\\dishes.csv");
        app.printRestaurantAndDishes();
        app.placeOrder();
    }
}
