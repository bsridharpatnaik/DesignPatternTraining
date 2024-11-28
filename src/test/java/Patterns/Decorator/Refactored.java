/**
 * Example of Decorator Pattern in Test Automation
 * Goal: Add test features (screenshots, logging) without modifying core test code
 */

/**
 * Base interface - defines core test behavior
 * All decorators must implement this interface
 */
interface TestRunner {
    void runTest();  // Core test method
}

/**
 * Basic implementation without any extra features
 * This is what we'll decorate with additional behaviors
 */
class BasicTestRunner implements TestRunner {
    @Override
    public void runTest() {
        System.out.println("Running basic test");
    }
}

/**
 * Base decorator class that all feature decorators extend
 * Implements TestRunner and holds reference to decorated object
 */
class TestDecorator implements TestRunner {
    protected TestRunner testRunner;  // Holds the decorated TestRunner

    public TestDecorator(TestRunner testRunner) {
        this.testRunner = testRunner;
    }

    @Override
    public void runTest() {
        // Default behavior: just run the decorated test
        testRunner.runTest();
    }
}

/**
 * Screenshot capability decorator
 * Adds screenshot taking before and after test execution
 */
class ScreenshotDecorator extends TestDecorator {
    public ScreenshotDecorator(TestRunner testRunner) {
        super(testRunner);
    }

    @Override
    public void runTest() {
        takeScreenshotBefore();     // Added behavior before
        testRunner.runTest();       // Original test execution
        takeScreenshotAfter();      // Added behavior after
    }

    private void takeScreenshotBefore() {
        System.out.println("Taking screenshot before test");
    }

    private void takeScreenshotAfter() {
        System.out.println("Taking screenshot after test");
    }
}

/**
 * Logging capability decorator
 * Adds log messages before and after test execution
 */
class LoggingDecorator extends TestDecorator {
    public LoggingDecorator(TestRunner testRunner) {
        super(testRunner);
    }

    @Override
    public void runTest() {
        log("Starting test");      // Added behavior before
        testRunner.runTest();      // Original test execution
        log("Test completed");     // Added behavior after
    }

    private void log(String message) {
        System.out.println("LOG: " + message);
    }
}

/**
 * Example showing different ways to use decorators
 * Demonstrates how features can be combined flexibly
 */
public class AutomatedTest {
    public static void main(String[] args) {
        // 1. Basic test - no decorations
        TestRunner basic = new BasicTestRunner();
        basic.runTest();

        // 2. Test with screenshots only
        TestRunner withScreenshots = new ScreenshotDecorator(new BasicTestRunner());
        withScreenshots.runTest();

        // 3. Test with both screenshots and logging
        // Note how decorators are stacked
        TestRunner withScreenshotsAndLogs = new LoggingDecorator(
            new ScreenshotDecorator(
                new BasicTestRunner()
            )
        );
        withScreenshotsAndLogs.runTest();
    }
}

/* How It Works:
 * 1. Each decorator wraps a TestRunner and adds its behavior
 * 2. Decorators can be stacked in any order
 * 3. Core test logic remains unchanged
 * 4. Each feature is isolated in its own decorator
 *
 * Common Use Cases:
 * 1. Adding retry logic for flaky tests
 * 2. Performance monitoring
 * 3. Error screenshots
 * 4. Test logging
 * 5. Report generation
 */
