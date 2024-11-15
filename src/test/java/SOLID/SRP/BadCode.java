import java.util.ArrayList;
import java.util.List;

public class TestFramework {

    private List<String> logs = new ArrayList<>();

    public void runTest(String testName) {
        // Responsibility 1: Test Execution
        System.out.println("Executing test: " + testName);
        logs.add("Test " + testName + " started.");

        // Simulating test steps
        try {
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

    // Problems:
    // - The test class is handling three responsibilities: execution, reporting, and data management.
    // - Adding or modifying reporting logic requires changing the test class.
    // - Test data management logic is not reusable in other tests.
    // - The class is tightly coupled, making it harder to maintain and extend.
}
