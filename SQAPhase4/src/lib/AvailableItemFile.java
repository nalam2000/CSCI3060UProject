import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class AvailableItemFile {
    // Add attributes for managing available items
    private static String filePath = "availableitems.txt";

    public static void bid(String seller, String itemName, String buyer, String bidAmount) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            String[] splitString = line.split("\\s+");
            String currentItemName = splitString[0];
            String currentSellerName = splitString[1];
            String currentDaysLeft = splitString[3];

            // if both seller name and item name match, append updated line to string builder
            if (seller.equals(currentSellerName) && itemName.equals(currentItemName)) {
                sb.append(addSpaces(itemName, 20))
                        .append(addSpaces(seller, 16))
                        .append(addSpaces(buyer, 16))
                        .append(formatDaysLeft(currentDaysLeft))
                        .append(formatAmount(bidAmount))
                        .append("\n");
            } else {
                sb.append(line).append("\n");
            }
        }

        Files.write(Paths.get(filePath), sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);

        // Add bid placing logic here
    }

    public static void advertise(String itemName, String sellerName, String buyerName, String daysLeft, String startingBid) throws Exception {
        String str = addSpaces(itemName, 20)
                + addSpaces(sellerName, 16)
                + addSpaces(buyerName, 16)
                + formatDaysLeft(daysLeft)
                + formatAmount(startingBid)
                + "\n";

        Files.write(Paths.get(filePath), str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        // Add advertisement creation logic here
    }

    public static String addSpaces(String str, int numChars) {
        return String.format("%-" + numChars + "s", str);
        // Add space adding logic here
    }

    public static String formatAmount(String str) {
        return String.format("%06d", Integer.parseInt(str));
    }

    public static String formatDaysLeft(String str) {
        return String.format("%03d", Integer.parseInt(str));
    }
}
