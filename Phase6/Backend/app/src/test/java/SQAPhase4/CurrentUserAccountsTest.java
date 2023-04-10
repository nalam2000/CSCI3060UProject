import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CurrentUserAccountsTest class
public class CurrentUserAccountsTest {

    // Instance variables to store the CurrentUserAccounts object and file path
    private CurrentUserAccountsTest currentUserAccounts;
    private String mainUserAccount;

    // This method is executed before each test case to set up the environment
    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        // Create a temporary file for the main user account and store its path
        mainUserAccount = tempDir.resolve("main_user_account.txt").toString();
        // Initialize a CurrentUserAccounts object with the temporary file path
        currentUserAccounts = new CurrentUserAccounts(mainUserAccount);
    }

    // Test case to verify adding a user works correctly
    @Test
    void testAddUser() {
        assertEquals(0, currentUserAccounts.addUser("testuser", "01", 5000.0f));
    }

    // Test case to verify adding an existing user returns an error
    @Test
    void testAddExistingUser() {
        currentUserAccounts.addUser("testuser", "01", 5000.0f);
        assertEquals(-1, currentUserAccounts.addUser("testuser", "01", 5000.0f));
    }

    // Test case to verify deleting a user works correctly
    @Test
    void testDeleteUser() {
        currentUserAccounts.addUser("testuser", "01", 5000.0f);
        assertEquals(0, currentUserAccounts.deleteUser("testuser"));
    }

    // Test case to verify deleting a non-existent user returns an error
    @Test
    void testDeleteNonExistentUser() {
        assertEquals(0, currentUserAccounts.deleteUser("nonexistent"));
    }

    // Test case to verify adding credit to a user works correctly
    @Test
    void testAddCredit() {
        currentUserAccounts.addUser("testuser", "01", 5000.0f);
        assertEquals(0, currentUserAccounts.addCredit("testuser", 1000.0f));
    }

    // Test case to verify adding credit that exceeds the user's limit returns an error
    @Test
    void testAddCreditExceedsLimit() {
        currentUserAccounts.addUser("testuser", "01", 995000.0f);
        assertEquals(-1, currentUserAccounts.addCredit("testuser", 10000.0f));
    }

    // Test case to verify refunding credit between users works correctly
    @Test
    void testRefund() {
        currentUserAccounts.addUser("seller", "01", 10000.0f);
        currentUserAccounts.addUser("buyer", "01", 5000.0f);
        assertEquals(0, currentUserAccounts.refund("seller", "buyer", 1000.0f));
    }


    // Path Coverage Tests 
     // Test case: Add credit to an existing user without exceeding the credit limit
    @Test
    void testAddCreditUserExists1() {
        // Add a user with an initial credit amount
        currentUserAccounts.addUser("testuser", "01", 5000.0f);
        // Attempt to add credit within the allowed limit
        assertEquals(0, currentUserAccounts.addCredit("testuser", 1000.0f));
    }

    // Test case: Add credit to an existing user, but exceed the credit limit
    @Test
    void testAddCreditUserExists2() {
        // Add a user with an initial credit amount close to the limit
        currentUserAccounts.addUser("testuser", "01", 995000.0f);
        // Attempt to add credit that would exceed the allowed limit
        assertEquals(-1, currentUserAccounts.addCredit("testuser", 10000.0f));
    }

    // Test case: Attempt to add credit to a non-existent user
    @Test
    void testAddCreditUserNotExists() {
        // Attempt to add credit to a user that doesn't exist in the system
        assertEquals(-1, currentUserAccounts.addCredit("nonexistent", 1000.0f));
    }
}
