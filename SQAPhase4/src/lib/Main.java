
/* This program has been designed so the CurrentAccounts file and AvailableItemsFile is parsed through, 
   with the appropriate information is extracted from each file and is stored under a variable. This information
   is then pushed to the daily transaction file. 
   
   Made by: Shane Francis, Noor Alam and Aqib Alam
   
   
   */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static String username;
    private static String accType;
    private static double credits;
    private static String seller;
    private static String buyer;
    private static String itemName;

    public static void main(String[] args) {
        // make objects which will be used to extract information from input files
        CurrentUserAccounts userAccounts = new CurrentUserAccounts();
        AvailableItemFile availableItems = new AvailableItemFile();

        String inputFilePath = "availableitems.txt";
        parseFile(inputFilePath);

    }

    private static void parseFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // get Information from input file
                String[] parts = line.split(",");

                if (parts.length >= 6) {
                    username = parts[0].trim();
                    accType = parts[1].trim();
                    credits = Double.parseDouble(parts[2].trim());
                    seller = parts[3].trim();
                    buyer = parts[4].trim();
                    itemName = parts[5].trim();
                }

                // Store this in the daily Transaction file
                writeToFile(username, accType, credits, seller, buyer, itemName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This function creates and writes to daily transaction file
    private static void writeToFile(String username, String accType, double credits, String seller, String buyer,
            String itemName) {
        String outputFilePath = "dailytransactionfile.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            String output = String.format("%s, %s, %.2f, %s, %s, %s%n", username, accType, credits, seller, buyer,
                    itemName);
            writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
