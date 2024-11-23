import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {
    public void testPageNavigation() {
        // Problem 1: Logging code mixed with test logic
        // This makes the test method do too many things (violates Single Responsibility Principle)
        System.out.println("Starting test: testPageNavigation");

        // Problem 2: Performance measurement code scattered in test
        // Timing logic is duplicated across all test methods
        long startTime = System.currentTimeMillis();

        // Problem 3: Direct WebDriver instantiation
        // No way to easily swap out or enhance WebDriver behavior
        WebDriver driver = new ChromeDriver();

        // Problem 4: Basic actions mixed with logging
        // Each action requires multiple lines of logging code
        driver.get("http://example.com");
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        // Problem 5: Manual time calculation
        // Duplicated across methods, prone to errors
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        driver.quit();
        System.out.println("Test completed: testPageNavigation");
    }

    public void testAnotherPage() {
        // Problem 6: Copy-pasted code
        // Same logging and timing code duplicated
        System.out.println("Starting test: testAnotherPage");
        long startTime = System.currentTimeMillis();

        // Problem 7: No way to enforce consistent logging
        // Each developer might log differently
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com/another");
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        driver.quit();
        System.out.println("Test completed: testAnotherPage");
    }

    /* Major Issues:
     * 1. Violation of DRY (Don't Repeat Yourself) principle
     *    - Same logging and timing code copy-pasted everywhere
     *    - Any change in logging format needs changes in all test methods
     *
     * 2. Violation of Single Responsibility Principle
     *    - Test methods handle testing, logging, and performance measurement
     *    - Makes methods longer and harder to maintain
     *
     * 3. Poor Maintainability
     *    - Adding new behavior (like screenshots on failure) requires modifying all test methods
     *    - Changing logging format means updating many places
     *
     * 4. Limited Reusability
     *    - Can't easily reuse logging or timing behavior in other tests
     *    - No way to selectively apply behaviors to different tests
     *
     * 5. Poor Flexibility
     *    - Can't enable/disable logging or timing without changing test code
     *    - Hard to add new cross-cutting concerns
     *
     * 6. Testing Complexity
     *    - Hard to unit test the actual test logic
     *    - Logging and timing code gets in the way
     */
}
