
/* In this refactored version, the Decorator Pattern is used to wrap the 
WebDriver object, adding logging and timing behaviors dynamically
while keeping the test code clean and focused.  */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Base interface for WebDriver
interface WebDriverDecorator extends WebDriver {}

// Concrete decorator for logging behavior
class LoggingWebDriver implements WebDriverDecorator {
    private WebDriver driver;

    public LoggingWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        System.out.println("Navigating to: " + url);
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        return url;
    }

    @Override
    public void quit() {
        System.out.println("Quitting WebDriver");
        driver.quit();
    }

    // Other WebDriver methods delegated to the wrapped driver
    @Override
    public String getTitle() {
        return driver.getTitle();
    }
    // Implement other WebDriver methods as needed...
}

// Concrete decorator for timing behavior
class TimingWebDriver implements WebDriverDecorator {
    private WebDriver driver;

    public TimingWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        long startTime = System.currentTimeMillis();
        driver.get(url);
        long endTime = System.currentTimeMillis();
        System.out.println("Navigation to " + url + " took " + (endTime - startTime) + "ms");
    }

    @Override
    public void quit() {
        driver.quit();
    }

    // Other WebDriver methods delegated to the wrapped driver
    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }
    // Implement other WebDriver methods as needed...
}

// Test class using decorated WebDriver
public class BrowserTest {

    public void testPageNavigation() {
        WebDriver driver = new TimingWebDriver(new LoggingWebDriver(new ChromeDriver()));

        driver.get("http://example.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();

        System.out.println("Test completed: testPageNavigation");
    }

    public static void main(String[] args) {
        BrowserTest test = new BrowserTest();
        test.testPageNavigation();
    }

    // Benefits of Decorator Pattern:
    // - Logging and timing behaviors are dynamically added to WebDriver without modifying its core logic.
    // - Test methods remain clean and focused on business logic.
    // - Additional behaviors (e.g., reporting, screenshot capture) can be added as new decorators.
}
