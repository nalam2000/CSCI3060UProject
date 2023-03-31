import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CurrentUserAccounts {
// Declare a private variable to hold the name of the main user account file
private String mainUserAccount;

// Constructor to initialize the main user account file name
public CurrentUserAccounts(String mainAcc){
    this.mainUserAccount = mainAcc;
}

// Method to add a new user account to the system
public int addUser(String username, String accountType, float initialCredit){
    try {
        // Read the current account data from the main user account file
        List<String[]> accountList = readAccountData();
        // Check if the user already exists in the system
        for (String[] account : accountList) {
            if (account[0].equals(username)) {
                System.out.println("ERROR: User already exists");
                return -1;
            }
        }
        // Format the initial credit value and add the new account data to the account list
        String formattedCredit = String.format("%.2f", initialCredit);
        while (formattedCredit.length() < 9) {
            formattedCredit = "0" + formattedCredit;
        }
        accountList.add(new String[]{username, accountType, formattedCredit});
        // Write the updated account data back to the main user account file
        writeAccountData(accountList);
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
    return 0;
}

// Method to delete a user account from the system
public int deleteUser(String username){
    try {
        // Read the current account data from the main user account file
        List<String[]> accountList = readAccountData();
        // Create a new account list without the account to be deleted
        List<String[]> updatedAccounts = new ArrayList<>();
        for (String[] account : accountList) {
            if (!account[0].equals(username)) {
                updatedAccounts.add(account);
            }
        }
        // Write the updated account data back to the main user account file
        writeAccountData(updatedAccounts);
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
    return 0;
}

// Method to add credit to a user account
public int addCredit(String username, float creditAmount){
    try {
        // Read the current account data from the main user account file
        List<String[]> accountList = readAccountData();
        // Create a new account list with the updated credit balance
        List<String[]> updatedAccounts = new ArrayList<>();
        for (String[] account : accountList) {
            if (account[0].equals(username)) {
                // Calculate the new credit balance and check if it exceeds the maximum limit
                float newCreditBalance = Float.parseFloat(account[2]) + creditAmount;
                if (newCreditBalance >= 1000000) {
                    System.out.println("ERROR: User cannot have over 999999.99 credits");
                    return -1;
                }
                // Format the new credit balance and update the account data
                account[2] = String.format("%09.2f", newCreditBalance);
            }
            updatedAccounts.add(account);
        }
        // Write the updated account data back to the main user account file
        writeAccountData(updatedAccounts);
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
    return 0;
}

// Method to process a refund transaction between two user accounts
public int refund(String sellerUsername, String buyerUsername, float refundAmount){
    try {
        // Read the current account
                // Read the current account data from the main user account file
        List<String[]> accountList = readAccountData();
        // Create a new account list with the updated credit balances
        List<String[]> updatedAccounts = new ArrayList<>();
        boolean sellerFound = false;
        boolean buyerFound = false;

        for (String[] account : accountList) {
            if (account[0].equals(buyerUsername)) {
                // Calculate the new credit balance for the buyer and check if it exceeds the maximum limit
                float newCreditBalance = Float.parseFloat(account[2]) + refundAmount;
                if (newCreditBalance >= 1000000) {
                    System.out.println("ERROR: Buyer cannot have over 999999.99 credits");
                    return -1;
                }
                // Format the new credit balance and update the account data
                account[2] = String.format("%09.2f", newCreditBalance);
                buyerFound = true;
            } else if (account[0].equals(sellerUsername)) {
                // Calculate the new credit balance for the seller and check if it goes negative
                float newCreditBalance = Float.parseFloat(account[2]) - refundAmount;
                if (newCreditBalance < 0) {
                    System.out.println("ERROR: Seller cannot have negative credits");
                    return -1;
                }
                // Format the new credit balance and update the account data
                account[2] = String.format("%09.2f", newCreditBalance);
                sellerFound = true;
            }
            updatedAccounts.add(account);
        }
        // Write the updated account data back to the main user account file if both users are found
        if (sellerFound && buyerFound) {
            writeAccountData(updatedAccounts);
        } else {
            System.out.println("ERROR: Buyer and/or Seller not found");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
    return 0;
}

// Private method to read the account data from the main user account file
private List<String[]> readAccountData() throws IOException {
    List<String[]> accounts = new ArrayList<>();
    try (FileInputStream fis = new FileInputStream(mainUserAccount);
         BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

        String line;
        while ((line = reader.readLine()) != null) {
            // Parse the account data from each line of the file
            String[] accData = new String[3];
            accData[0] = line.substring(0, 15);
            accData[1] = line.substring(16, 18);
            accData[2] = line.substring(19, 28);
            accounts.add(accData);
        }
    }
    return accounts;
}

// Private method to write the account data to the main user account file
private void writeAccountData(List<String[]> accountList) throws IOException {
    try (FileWriter fw = new FileWriter(mainUserAccount, false);
         BufferedWriter bw = new BufferedWriter(fw)) {
        // Convert each account data into a formatted string and write it to the file
        for (String[] account : accountList) {
            String accountLine = String.join(" ", account);
            bw.write(accountLine);
            bw.newLine();
        }
    }
}    
}
