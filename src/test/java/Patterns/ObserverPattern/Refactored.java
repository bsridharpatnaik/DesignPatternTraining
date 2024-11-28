/**
 * Observer Pattern in Test Automation
 * Separates test execution from notification logic
 */

/**
 * Observer interface defining notification contract
 * All notification handlers must implement this
 */
interface TestObserver {
    void update(String event, String message);  // Method called when test events occur
}

/**
 * Logs test events to console/file
 * Can be extended for different logging implementations
 */
class LoggerObserver implements TestObserver {
    @Override
    public void update(String event, String message) {
        System.out.println("Log: [" + event + "] " + message);
    }
}

/**
 * Sends test notifications via email
 * Useful for critical test failures
 */
class EmailObserver implements TestObserver {
    @Override
    public void update(String event, String message) {
        // Could implement actual email sending here
        System.out.println("Email: [" + event + "] " + message);
    }
}

/**
 * Sends test notifications to Slack
 * Good for team-wide test status updates
 */
class SlackObserver implements TestObserver {
    @Override
    public void update(String event, String message) {
        // Could implement actual Slack API call here
        System.out.println("Slack: [" + event + "] " + message);
    }
}

/**
 * Main test execution class (Subject)
 * Handles test execution and notifies observers of events
 */
class TestExecution {
    private List<TestObserver> observers = new ArrayList<>();

    // Observer management methods
    public void addObserver(TestObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TestObserver observer) {
        observers.remove(observer);
    }

    // Notify all observers of test events
    private void notifyObservers(String event, String message) {
        observers.forEach(observer -> observer.update(event, message));
    }

    /**
     * Main test execution method
     * Only focuses on test logic, notifications handled by observers
     */
    public void runTest() {
        notifyObservers("START", "Test started");

        try {
            // Core test logic
            executeTestSteps();
            notifyObservers("SUCCESS", "Test passed");

        } catch (Exception e) {
            notifyObservers("FAILURE", "Test failed: " + e.getMessage());
        }

        notifyObservers("COMPLETE", "Test completed");
    }
}

/**
 * Example usage showing how to configure and use observers
 */
public class TestRunner {
    public static void main(String[] args) {
        TestExecution test = new TestExecution();

        // Attach different types of observers
        test.addObserver(new LoggerObserver());    // For logging
        test.addObserver(new EmailObserver());     // For email notifications
        test.addObserver(new SlackObserver());     // For Slack updates

        // Run test - observers automatically notified
        test.runTest();
    }
}

/* How Observer Pattern Helps in Testing:
 * 1. Separation of Concerns:
 *    - Test logic separate from notifications
 *    - Each observer handles one type of notification
 *
 * 2. Flexibility:
 *    - Easy to add/remove notification methods
 *    - Can configure different observers for different tests
 *
 * Common Use Cases:
 * - Test reporting
 * - Real-time notifications
 * - Test monitoring
 * - Custom logging
 */
