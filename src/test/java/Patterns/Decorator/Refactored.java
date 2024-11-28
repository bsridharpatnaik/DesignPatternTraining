/**
 * We need to add screenshot capture and logging capabilities to our test execution without modifying the existing test code.
 * Basic interface for test actions
 */
interface TestRunner {
    void runTest();
}

/**
 * Basic test implementation without any extras
 */
class BasicTestRunner implements TestRunner {
    @Override
    public void runTest() {
        System.out.println("Running basic test");
    }
}

/**
 * Base decorator class - all test enhancements extend this
 */
class TestDecorator implements TestRunner {
    protected TestRunner testRunner;

    public TestDecorator(TestRunner testRunner) {
        this.testRunner = testRunner;
    }

    @Override
    public void runTest() {
        testRunner.runTest();
    }
}

/**
 * Decorator that adds screenshot capability
 */
class ScreenshotDecorator extends TestDecorator {
    public ScreenshotDecorator(TestRunner testRunner) {
        super(testRunner);
    }

    @Override
    public void runTest() {
        takeScreenshotBefore();
        testRunner.runTest();
        takeScreenshotAfter();
    }

    private void takeScreenshotBefore() {
        System.out.println("Taking screenshot before test");
    }

    private void takeScreenshotAfter() {
        System.out.println("Taking screenshot after test");
    }
}

/**
 * Decorator that adds logging capability
 */
class LoggingDecorator extends TestDecorator {
    public LoggingDecorator(TestRunner testRunner) {
        super(testRunner);
    }

    @Override
    public void runTest() {
        log("Starting test");
        testRunner.runTest();
        log("Test completed");
    }

    private void log(String message) {
        System.out.println("LOG: " + message);
    }
}

/**
 * Example usage showing how to use decorators in test automation
 */
public class AutomatedTest {
    public static void main(String[] args) {
        // Basic test without any decoration
        System.out.println("Running basic test:");
        TestRunner basic = new BasicTestRunner();
        basic.runTest();

        System.out.println("\nRunning test with screenshots:");
        // Test with screenshots
        TestRunner withScreenshots = new ScreenshotDecorator(new BasicTestRunner());
        withScreenshots.runTest();

        System.out.println("\nRunning test with screenshots and logging:");
        // Test with both screenshots and logging
        TestRunner withScreenshotsAndLogs = new LoggingDecorator(
            new ScreenshotDecorator(
                new BasicTestRunner()
            )
        );
        withScreenshotsAndLogs.runTest();
    }
}

/* How Decorator Pattern helps in Test Automation:
 * 1. Start with basic test runner
 * 2. Add screenshots when needed
 * 3. Add logging when needed
 * 4. Can combine features as required
 *
 * Benefits:
 * 1. Add features without changing existing test code
 * 2. Mix and match features as needed for different tests
 * 3. Keep test logic clean and focused
 * 4. Easy to add new features (retry, reporting, timing, etc.)
 */
