/**
 * Step 1: Separate logging responsibility
 */
class TestLogger {
    private List<String> logs = new ArrayList<>();

    public void log(String message) {
        logs.add(message);
    }

    public void generateReport() {
        System.out.println("\nTest Report:");
        logs.forEach(System.out::println);
    }

    public void clearLogs() {
        logs.clear();
    }
}

/**
 * Step 2: Separate data management responsibility
 */
class TestDataManager {
    public void prepareData() {
        System.out.println("Preparing test data...");
    }

    public void cleanUpData() {
        System.out.println("Cleaning up test data...");
    }

    public void resetDatabase() {
        System.out.println("Resetting test database...");
    }
}

/**
 * Step 3: Separate test execution responsibility
 */
class TestExecutor {
    public void executeTestSteps(String testName) {
        System.out.println("Executing test steps for: " + testName);
        // Actual test execution logic
    }

    public void validateResults() {
        System.out.println("Validating test results...");
    }
}

/**
 * Step 4: Main framework class orchestrating the components
 */
public class TestFramework {
    private final TestLogger logger;
    private final TestDataManager dataManager;
    private final TestExecutor executor;

    public TestFramework() {
        this.logger = new TestLogger();
        this.dataManager = new TestDataManager();
        this.executor = new TestExecutor();
    }

    public void runTest(String testName) {
        try {
            // Setup
            logger.log("Test " + testName + " started");
            dataManager.prepareData();

            // Execution
            executor.executeTestSteps(testName);
            executor.validateResults();
            logger.log("Test " + testName + " passed");

        } catch (Exception e) {
            logger.log("Test " + testName + " failed: " + e.getMessage());
        } finally {
            // Cleanup
            dataManager.cleanUpData();
            logger.generateReport();
        }
    }
}

/**
 * Step 5: Example usage showing clean separation
 */
public class TestExample {
    public static void main(String[] args) {
        TestFramework framework = new TestFramework();

        // Running simple test
        framework.runTest("LoginTest");

        // Each component can also be used independently
        TestDataManager dataManager = new TestDataManager();
        dataManager.resetDatabase();

        TestLogger logger = new TestLogger();
        logger.log("Custom log message");
        logger.generateReport();
    }
}

/* Benefits of SRP:
 * 1. Each class has one reason to change
 * 2. Easy to modify individual components
 * 3. Better code organization
 * 4. Improved reusability
 * 5. Easier to test
 * 6. Better maintainability
 * 7. Clear separation of concerns
 */
