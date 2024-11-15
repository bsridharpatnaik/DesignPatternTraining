import java.util.ArrayList;
import java.util.List;

public class TestExecution {

    public void runTest() {
        System.out.println("Running test...");

        // Problem 1: Directly handling logging
        System.out.println("Test started.");

        // Test steps...
        try {
            // Simulate test logic
            System.out.println("Executing test steps...");
            // Assume test passes here

            // Problem 2: Directly handling notifications
            System.out.println("Sending success notification...");
        } catch (Exception e) {
            // Problem 3: Duplicated error handling and notifications
            System.out.println("Test failed.");
            System.out.println("Sending failure notification...");
        }

        System.out.println("Test completed.");
    }

    // Issues Summary:
    // - Logging and notification logic are directly embedded in the test method, leading to duplication.
    // - No flexibility to add or remove logging/notification logic without modifying the test itself.
    // - Tight coupling between test execution and notification/logging mechanisms.
}
