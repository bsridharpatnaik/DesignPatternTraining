public class TestExecution {
    public void runTest() {
        // Problem 1: Direct logging in test method
        System.out.println("Running test...");
        System.out.println("Test started.");

        try {
            // Test logic
            System.out.println("Executing test steps...");

            // Problem 2: Direct notification handling
            System.out.println("Sending success notification...");
            emailNotification("Test passed");
            slackNotification("Test passed");

        } catch (Exception e) {
            // Problem 3: Duplicate notification code
            System.out.println("Test failed.");
            emailNotification("Test failed");
            slackNotification("Test failed");
        }

        System.out.println("Test completed.");
    }

    private void emailNotification(String message) {
        System.out.println("Email: " + message);
    }

    private void slackNotification(String message) {
        System.out.println("Slack: " + message);
    }

    /* Major Issues:
     * 1. Test logic mixed with logging/notifications
     * 2. Hard-coded notification methods
     * 3. Duplicate notification code
     * 4. Can't add new notifications without changing test
     * 5. Tight coupling between test and notifications
     * 6. Hard to maintain or modify notifications
     * 7. No way to disable specific notifications
     */
}
