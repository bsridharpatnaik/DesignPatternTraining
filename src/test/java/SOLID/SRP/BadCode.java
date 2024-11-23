/**
 * Class that violates Single Responsibility Principle
 * Handles multiple responsibilities in one class
 */
public class TestFramework {
    private List<String> logs = new ArrayList<>();

    public void runTest(String testName) {
        // Responsibility 1: Test Execution
        System.out.println("Executing test: " + testName);
        logs.add("Test " + testName + " started.");

        try {
            // Test execution logic
            System.out.println("Performing test steps...");
            logs.add("Test " + testName + " passed.");
        } catch (Exception e) {
            logs.add("Test " + testName + " failed.");
        }

        // Responsibility 2: Test Reporting
        System.out.println("Generating report...");
        for (String log : logs) {
            System.out.println(log);
        }

        // Responsibility 3: Test Data Management
        System.out.println("Cleaning up test data...");
    }

    /* Problems:
     * 1. Multiple responsibilities in one class
     * 2. Hard to modify one aspect without affecting others
     * 3. Changes to reporting require modifying test class
     * 4. Test data management not reusable
     * 5. Difficult to test individual responsibilities
     * 6. Poor separation of concerns
     * 7. Hard to maintain and extend
     */
}
