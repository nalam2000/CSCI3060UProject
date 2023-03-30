import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    static String filePath = "dailytransactions.txt";
    static CurrentUserAccounts cu = new CurrentUserAccounts("currentusers.txt");

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String st;
        while ((st = br.readLine()) != null) {
            Transaction transaction = TransactionFactory.createTransaction(st, cu);
            if (transaction != null) {
                transaction.process();
            }
        }
    }
}

abstract class Transaction {
    String line;
    CurrentUserAccounts cu;

    public Transaction(String line, CurrentUserAccounts cu) {
        this.line = line;
        this.cu = cu;
    }

    abstract void process() throws Exception;
}

class AddTransaction extends Transaction {
    public AddTransaction(String line, CurrentUserAccounts cu) {
        super(line, cu);
    }

    @Override
    void process() {
        String username = line.substring(3, 18).trim();
        String accType = line.substring(19, 21).trim();
        float credits = Float.valueOf(line.substring(22, 31).trim());

        cu.addUser(username, accType, credits);
    }
}

class DeleteTransaction extends Transaction {
    public DeleteTransaction(String line, CurrentUserAccounts cu) {
        super(line, cu);
    }

    @Override
    void process() {
        String username = line.substring(3, 18).trim();

        cu.deleteUser(username);
    }
}

class Advertise extends Transaction {
    public Advertise(String line, CurrentUserAccounts cu) {
        super(line, cu);
    }

    @Override
    void process() {
        String itemName = line.substring(3, 22).trim();
        String seller = line.substring(23, 36).trim();
        String daysLeft = line.substring(37, 40).trim();
        String startingBid = line.substring(42, 47).trim();

        AvailableItemFile.advertise(itemName, seller, "", daysLeft, startingBid);
    }
}

class Bid extends Transaction {
    public Bid(String line, CurrentUserAccounts cu) {
        super(line, cu);
    }

    @Override
    void process() {
        String itemName = line.substring(3, 22).trim();
        String seller = line.substring(23, 38).trim();
        String buyer = line.substring(39, 54).trim();
        String cred = line.substring(54, 60).trim();

        AvailableItemFile.bid(seller, itemName, buyer, cred);
    }
}

class Refund extends Transaction {
    public Refund(String line, CurrentUserAccounts cu) {
        super(line, cu);
    }

    @Override
    void process() {
        String buyer = line.substring(3, 18).trim();
        String seller = line.substring(19, 34).trim();
        float credits = Float.valueOf(line.substring(34, 44).trim());

        cu.refund(seller, buyer, credits);
    }
}

class AddCredit extends Transaction {
    public AddCredit(String line, CurrentUserAccounts cu) {
        super(line, cu);
    }

    @Override
    void process() {
        String username = line.substring(3, 18).trim();
        float credits = Float.valueOf(line.substring(22, 31).trim());

        cu.addCredit(username, credits);
    }
}


class TransactionFactory {
    public static Transaction createTransaction(String line, CurrentUserAccounts cu) {
        String transactionType = line.substring(0, 2);
        switch (transactionType) {
            case "01":
                return new AddTransaction(line, cu);
            case "02":
                return new DeleteTransaction(line, cu);
            case "03":
                return new AdvertiseTransaction(line, cu);
            case "04":
                return new BidTransaction(line, cu);
            case "05":
                return new RefundTransaction(line, cu);
            case "06":
                return new AddCreditTransaction(line, cu);
            default:
                return null;
        }
    }
}
