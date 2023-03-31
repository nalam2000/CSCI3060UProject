import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

//Main Test class
class MainTest {

    //Setup variables
    private CurrentUserAccounts cu;
    private String transactions;

    @TempDir
    Path temporaryFolder;

    //Setup method
    @BeforeEach
    void setUp() {
        cu = new CurrentUserAccounts("currentusers.txt");
        transactions = "01 testuser1  FS 00000500.00\n"
                + "01 testuser2  SS 00000750.00\n"
                + "02 testuser2\n"
                + "03 item1       testuser1  007 05000\n"
                + "04 item1       testuser1  testuser2 05100\n"
                + "05 testuser2  testuser1  0000100.00\n"
                + "06 testuser1  00000050.00\n";
    }
    //Main Test class
    @Test
    void testMain() throws IOException {
        // Prepare input file
        File inputFile = temporaryFolder.resolve("input.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write(transactions);
        }

        // Run the main method
        String[] args = new String[]{inputFile.getAbsolutePath()};
        Main.main(args);
    }
}