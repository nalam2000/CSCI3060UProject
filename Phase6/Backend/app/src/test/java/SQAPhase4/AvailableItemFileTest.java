package test;

// Imports
import lib.AvailableItemFile;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Test Class for testing the Available Item Class. Uses J-unit tests to check statement coverage.
public class AvailableItemFileTest {

    @Test
    public void testBid() throws Exception {
        // Test case with an existing item
        AvailableItemFile.bid("Seller1", "Item1", "Buyer1", "100");
        assertEquals("Item1          Seller1        Buyer1        010100", getLastLine());

        // Test case with a non-existing item
        AvailableItemFile.bid("Seller2", "Item2", "Buyer2", "200");
        assertEquals(Arrays.asList("Item1          Seller1        Buyer1        010100",
                "Item3          Seller3        Buyer3        020200",
                "Item4          Seller4        Buyer4        030300",
                "Item5          Seller5        Buyer5        040400",
                "Item6          Seller6        Buyer6        050500",
                "Item7          Seller7        Buyer7        060600",
                "Item8          Seller8        Buyer8        070700",
                "Item9          Seller9        Buyer9        080800",
                "Item10         Seller10       Buyer10       090900",
                "Item11         Seller11       Buyer11       101000"), getAllLines());
    }

    @Test
    public void testAdvertise() throws Exception {
        // Test case with a new advertisement
        AvailableItemFile.advertise("New Item", "New Seller", "New Buyer", "999", "999");
        assertEquals(Arrays.asList("Item1          Seller1        Buyer1        010100",
                "Item3          Seller3        Buyer3        020200",
                "Item4          Seller4        Buyer4        030300",
                "Item5          Seller5        Buyer5        040400",
                "Item6          Seller6        Buyer6        050500",
                "Item7          Seller7        Buyer7        060600",
                "Item8          Seller8        Buyer8        070700",
                "Item9          Seller9        Buyer9        080800",
                "Item10         Seller10       Buyer10       090900",
                "Item11         Seller11       Buyer11       101000",
                "New Item       New Seller    New Buyer    999999"), getAllLines());
    }

    @Test
    // Test Helper function Add Spaces and assert whether it works
    public void testAddSpaces() {
        assertEquals("abc             ", AvailableItemFile.addSpaces("abc", 16));
        assertEquals("def             ", AvailableItemFile.addSpaces("def", 16));
        assertEquals("ghijklm         ", AvailableItemFile.addSpaces("ghijklm", 16));
    }

    @Test
    // Test Helper function Format amount and assert whether the functions it works
    // the way it is intended.
    public void testFormatAmount() {
        assertEquals("000100", AvailableItemFile.formatAmount("100"));
        assertEquals("012345", AvailableItemFile.formatAmount("12345"));
        assertEquals("100000", AvailableItemFile.formatAmount("100000"));
    }

    @Test
    // Test Helper function FormatDaysLeft and assert whether it works the way it is
    // intended.
    public void testFormatDaysLeft() {
        assertEquals("001", AvailableItemFile.formatDaysLeft("1"));
        assertEquals("050", AvailableItemFile.formatDaysLeft("50"));
        assertEquals("365", AvailableItemFile.formatDaysLeft("365"));
    }

}
