import java.util.ArrayList;
import java.util.List;

// Observer interface
interface TestObserver {
    void update(String event);
}

// Concrete observer for logging events
class LoggerObserver implements TestObserver {
    @Override
    public void update(String event) {
        System.out.println("Log: " + event);
    }
}

// Concrete observer for sending notifications
class NotificationObserver implements TestObserver {
    @Override
    public void update(String event) {
        System.out.println("Notification: " + event);
    }
}

// Subject class that manages test execution and notifies observers
class TestExecution {

    private List<TestObserver> observers = new ArrayList<>();

    // Method to register observers
    public void addObserver(TestObserver observer) {
        observers.add(observer);
    }

    // Method to remove observers
    public void removeObserver(TestObserver observer) {
        observers.remove(observer);
    }

    // Method to notify all registered observers of an event
    private void notifyObservers(String event) {
        for (TestObserver observer : observers) {
            observer.update(event);
        }
    }

    // Method that simulates test execution and notifies observers of test events
    public void runTest() {
        notifyObservers("Test started.");

        try {
            // Simulate test logic
            System.out.println("Executing test steps...");
            // Assume test passes

            notifyObservers("Test succeeded.");
        } catch (Exception e) {
            notifyObservers("Test failed.");
        }

        notifyObservers("Test completed.");
    }
}

public class Main {
    public static void main(String[] args) {
        TestExecution testExecution = new TestExecution();

        // Register observers
        testExecution.addObserver(new LoggerObserver());
        testExecution.addObserver(new NotificationObserver());

        // Run the test (observers will be notified of events)
        testExecution.runTest();
    }

    // Benefits of Observer Pattern:
    // - Decouples test execution from logging and notification logic, improving modularity.
    // - Allows for easy addition or removal of observers without modifying the core test logic.
    // - Reusable observers that can be attached to multiple tests or subjects as needed.
}
