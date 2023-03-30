import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CurrentUserAccounts {
    private String mainUserAccount;

    public CurrentUserAccounts(String mainAcc){
        this.mainUserAccount = mainAcc;
    }

    public int addUser(String username, String accountType, float initialCredit){
        try {
            List<String[]> accountList = readAccountData();
            for (String[] account : accountList) {
                if (account[0].equals(username)) {
                    System.out.println("ERROR: User already exists");
                    return -1;
                }
            }

            String formattedCredit = String.format("%.2f", initialCredit);
            while (formattedCredit.length() < 9) {
                formattedCredit = "0" + formattedCredit;
            }
            accountList.add(new String[]{username, accountType, formattedCredit});
            writeAccountData(accountList);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int deleteUser(String username){
        try {
            List<String[]> accountList = readAccountData();
            List<String[]> updatedAccounts = new ArrayList<>();
            for (String[] account : accountList) {
                if (!account[0].equals(username)) {
                    updatedAccounts.add(account);
                }
            }
            writeAccountData(updatedAccounts);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int addCredit(){
        
        return 0;
    }

    public int refund(){
        return 0;
    }

    private List<String[]> readAccountData() throws IOException {
        List<String[]> accounts = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(mainUserAccount);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
    
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accData = new String[3];
                accData[0] = line.substring(0, 15);
                accData[1] = line.substring(16, 18);
                accData[2] = line.substring(19, 28);
                accounts.add(accData);
            }
        }
        return accounts;
    }
    
    private void writeAccountData(List<String[]> accountList) throws IOException {
        try (FileWriter fw = new FileWriter(mainUserAccount, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String[] account : accountList) {
                String accountLine = String.join(" ", account);
                bw.write(accountLine);
                bw.newLine();
            }
        }
    }
    
}