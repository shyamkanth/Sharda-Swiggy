import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        try {
            System.out.print("Enter a line: ");
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("You entered: " + line);
    }
}
