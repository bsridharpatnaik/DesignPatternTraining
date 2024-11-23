// Step 1: Create Observer interface
interface TestObserver {
    void update(String event, String message);
}

// Step 2: Create concrete observers
class LoggerObserver implements TestObserver {
    @Override
    public void update(String event, String message) {
        System.out.println("Log: [" + event + "] " + message);
    }
}

class EmailObserver implements TestObserver {
    @Override
    public void update(String event, String message) {
        System.out.println("Email: [" + event + "] " + message);
    }
}

class SlackObserver implements TestObserver {
    @Override
    public void update(String event, String message) {
        System.out.println("Slack: [" + event + "] " + message);
    }
}

// Step 3: Create Subject class
class TestExecution {
    private List<TestObserver> observers = new ArrayList<>();

    public void addObserver(TestObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TestObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String event, String message) {
        observers.forEach(observer -> observer.update(event, message));
    }

    public void runTest() {
        notifyObservers("START", "Test started");

        try {
            // Test logic only - no notification code here
            executeTestSteps();
            notifyObservers("SUCCESS", "Test passed");

        } catch (Exception e) {
            notifyObservers("FAILURE", "Test failed: " + e.getMessage());
        }

        notifyObservers("COMPLETE", "Test completed");
    }
}

// Step 4: Usage
public class TestRunner {
    public static void main(String[] args) {
        TestExecution test = new TestExecution();

        // Add observers as needed
        test.addObserver(new LoggerObserver());
        test.addObserver(new EmailObserver());
        test.addObserver(new SlackObserver());

        test.runTest();
    }
}

/*
Key Changes:
1. Separated notifications into observers
2. Clean test execution logic
3. Easy to add/remove observers
4. No notification code in test
5. Flexible notification system
6. Single point of notification
7. Configurable observers
*/
