import java.util.ArrayList;
import java.util.List;

// Class for managing test logs (Responsibility: Reporting)
class TestLogger {
    private List<String> logs = new ArrayList<>();

    public void log(String message) {
        logs.add(message);
    }

    public void generateReport() {
        System.out.println("Test Report:");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

// Class for handling test data (Responsibility: Data Management)
class TestDataManager {
    public void prepareData() {
        System.out.println("Preparing test data...");
    }

    public void cleanUpData() {
        System.out.println("Cleaning up test data...");
    }
}

// Class for running tests (Responsibility: Test Execution)
public class TestFramework {

    private TestLogger logger = new TestLogger();
    private TestDataManager dataManager = new TestDataManager();

    public void runTest(String testName) {
        logger.log("Test " + testName + " started.");
        dataManager.prepareData();

        try {
            // Simulating test steps
            System.out.println("Executing test: " + testName);
            logger.log("Test " + testName + " passed.");
        } catch (Exception e) {
            logger.log("Test " + testName + " failed.");
        }

        dataManager.cleanUpData();
        logger.generateReport();
    }

    public static void main(String[] args) {
        TestFramework framework = new TestFramework();
        framework.runTest("SampleTest");

        // Benefits:
        // - Test execution, reporting, and data management are separated into distinct classes.
        // - Each class has a single responsibility, making the code easier to maintain.
        // - Changes to one responsibility (e.g., improving reporting) don’t affect other parts of the framework.
    }
}
