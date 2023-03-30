
/* This is the testing file that is used to create a test class containing all the methods from CurrentUserAccounts.java.
   Here is where we specify specific parameters for our methods to ensure that they are doing the tasks we expect them to do.

   Made by: Ethan Jacalan
*/

// Importing some dependencies for testing
import java.beans.Transient;
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
        assertNotNull(expected: "Username", addedUser.addUser());            // Not sure about parameters yet, we're missing logic.
    }

    // A "testing" method to test the delete user function from CurrentUserAccounts.java
    @Test
    void deletingUser()
    {
        var deletedUser = new CurrentUserAccounts();

        // Specify expected results when running the test, and a comparison will be made when this runs.
        assertNotNull(expected: "Username", deletedUser.deleteUser());
    }

    // A "testing" method to test the add credit function from CurrentUserAccounts.java
    @Test
    void addingCredit()
    {
        var addedCredit = new CurrentUserAccounts();
        assertEquals(expected: int, addedCredit.addCredit());   // want to make sure the credit added is an integer

        // Specify expected results when running the test, and a comparison will be made when this runs.


    }

    // A "testing" method to test the refund function from CurrentUserAccounts.java
    @Test
    void refundedTransaction()
    {
        var refundedAmount = new CurrentUserAccounts();
        assertEquals(expected: int, refundedAmount.refund());   // want to make sure the refund amount is an integer

        // Specify expected results when running the test, and a comparison is made when this runs.

    }

}