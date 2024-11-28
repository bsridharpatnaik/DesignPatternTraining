/**
 * Single Responsibility Principle applied to Test Automation
 * Each class has exactly one reason to change
 */

/**
 * Handles only logging responsibility
 * Changes only when logging requirements change
 */
class TestLogger {
    private List<String> logs = new ArrayList<>();

    // Core logging functionality
    public void log(String message) {
        logs.add(message);
    }

    // Report generation - part of logging responsibility
    public void generateReport() {
        System.out.println("\nTest Report:");
        logs.forEach(System.out::println);
    }

    // Log management
    public void clearLogs() {
        logs.clear();
    }
}

/**
 * Manages only test data
 * Changes only when data handling requirements change
 */
class TestDataManager {
    // Data setup
    public void prepareData() {
        System.out.println("Preparing test data...");
    }

    // Data cleanup
    public void cleanUpData() {
        System.out.println("Cleaning up test data...");
    }

    // Database management
    public void resetDatabase() {
        System.out.println("Resetting test database...");
    }
}

/**
 * Handles only test execution
 * Changes only when test execution logic changes
 */
class TestExecutor {
    // Test execution
    public void executeTestSteps(String testName) {
        System.out.println("Executing test steps for: " + testName);
    }

    // Results validation
    public void validateResults() {
        System.out.println("Validating test results...");
    }
}

/**
 * Orchestrates the testing process
 * Each component handles its specific responsibility
 */
public class TestFramework {
    // Each field represents a distinct responsibility
    private final TestLogger logger;
    private final TestDataManager dataManager;
    private final TestExecutor executor;

    public TestFramework() {
        // Initialize each component
        this.logger = new TestLogger();
        this.dataManager = new TestDataManager();
        this.executor = new TestExecutor();
    }

    public void runTest(String testName) {
        try {
            // Each component does its specific job
            logger.log("Test " + testName + " started");
            dataManager.prepareData();           // Data management
            executor.executeTestSteps(testName);  // Test execution
            executor.validateResults();           // Validation
            logger.log("Test " + testName + " passed");
        } catch (Exception e) {
            logger.log("Test " + testName + " failed: " + e.getMessage());
        } finally {
            dataManager.cleanUpData();  // Cleanup
            logger.generateReport();     // Reporting
        }
    }
}

/**
 * Demonstrates how each component can be used independently
 * Shows the benefit of separate responsibilities
 */
public class TestExample {
    public static void main(String[] args) {
        // Using framework as a whole
        TestFramework framework = new TestFramework();
        framework.runTest("LoginTest");

        // Using components independently
        TestDataManager dataManager = new TestDataManager();
        dataManager.resetDatabase();  // Can use data management alone

        TestLogger logger = new TestLogger();
        logger.log("Custom log message");  // Can use logging alone
        logger.generateReport();
    }
}

/* SRP Benefits Demonstrated Here:
 * 1. Each class has a clear, single purpose:
 *    - TestLogger: Only handles logging
 *    - TestDataManager: Only manages test data
 *    - TestExecutor: Only executes tests
 *
 * 2. Independent Evolution:
 *    - Can change logging without affecting test execution
 *    - Can modify data management without touching logging
 *    - Can update test execution without impacting other parts
 *
 * 3. Practical Advantages:
 *    - Easy to understand each component's role
 *    - Can reuse components independently
 *    - Simpler maintenance and debugging
 *    - Clear where to make specific changes
 */
