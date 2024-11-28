/** We need to add screenshot capture and logging capabilities to our test execution without modifying the existing test code.
 * Bad implementation with mixed responsibilities
 * and hard-coded features
 */
public class TestRunner {
    private boolean takeScreenshots;
    private boolean enableLogging;

    // Problem 1: Constructor with multiple flags
    public TestRunner(boolean takeScreenshots, boolean enableLogging) {
        this.takeScreenshots = takeScreenshots;
        this.enableLogging = enableLogging;
    }

    public void runTest() {
        // Problem 2: Mixed responsibilities and messy conditional logic
        if (enableLogging) {
            System.out.println("LOG: Starting test");
        }

        if (takeScreenshots) {
            System.out.println("Taking screenshot before test");
        }

        // Actual test logic mixed with other concerns
        System.out.println("Running basic test");

        if (takeScreenshots) {
            System.out.println("Taking screenshot after test");
        }

        if (enableLogging) {
            System.out.println("LOG: Test completed");
        }
    }
}

/**
 * Example of problematic usage
 */
public class BadTestExample {
    public static void main(String[] args) {
        // Problem 3: Have to create different instances for different combinations
        TestRunner basicTest = new TestRunner(false, false);
        basicTest.runTest();

        TestRunner testWithScreenshots = new TestRunner(true, false);
        testWithScreenshots.runTest();

        TestRunner testWithLogs = new TestRunner(false, true);
        testWithLogs.runTest();

        TestRunner testWithBoth = new TestRunner(true, true);
        testWithBoth.runTest();
    }
}

/* Problems with this approach:
 * 1. Features are tightly coupled within TestRunner
 * 2. Adding new features requires modifying TestRunner class
 * 3. Messy conditional logic
 * 4. Hard to maintain and extend
 * 5. Can't add features dynamically
 * 6. Need different instances for different feature combinations
 * 7. No separation of concerns
 */
