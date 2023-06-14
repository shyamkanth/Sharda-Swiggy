import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
        String csvFile = "food.csv";
        String line;
        String csvSplitBy = ",";
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter a food item: ");
            String searchItem = reader.readLine();

            try (BufferedReader fileReader = new BufferedReader(new FileReader(csvFile))) {
                // Skip the header row
                fileReader.readLine();

                while ((line = fileReader.readLine()) != null) {
                    String[] foodData = line.split(csvSplitBy);
                    String foodItem = foodData[0];

                    if (foodItem.equalsIgnoreCase(searchItem)) {
                        String price = foodData[1];
                        System.out.println("Your order for \"" + foodItem + "\" has been accepted, and the cost is " + price + " Rupees.");
                        found = true;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!found) {
                System.out.println("This item is not available currently.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
