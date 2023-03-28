// Importing some dependencies for testing
import org.junit.jupiter.api.Test;


// Creating a new testing class

class CurrentUserAccountsTest
{
    // A "testing" method to test the add user function from CurrentUserAccounts.java
    @Test
    void addingUser()
    {
        var addedUser = new CurrentUserAccounts();

        // Specify expected results when running the test, and a comparison will be made when this runs.
        assertEquals(expected: "Username", addedUser.addUser());            // Not sure about parameters yet, we're missing logic.
    }

}