// Step 1: Create a base interface - all decorators will implement this
interface WebDriverDecorator extends WebDriver {}

// Step 2: First decorator - Adds logging without changing WebDriver
class LoggingWebDriver implements WebDriverDecorator {
    private WebDriver driver;  // Wrapped WebDriver object

    public LoggingWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        // Adds logging before and after navigation
        System.out.println("Navigating to: " + url);
        driver.get(url);
        System.out.println("Navigation completed");
    }

    @Override
    public void quit() {
        System.out.println("Quitting WebDriver");
        driver.quit();
    }

    @Override
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        return url;
    }

    // Other WebDriver methods simply delegate to wrapped driver
}

// Step 3: Second decorator - Adds timing without changing WebDriver or LoggingWebDriver
class TimingWebDriver implements WebDriverDecorator {
    private WebDriver driver;  // Can wrap any WebDriver (plain or decorated)

    public TimingWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        // Adds timing around navigation
        long startTime = System.currentTimeMillis();
        driver.get(url);
        long endTime = System.currentTimeMillis();
        System.out.println("Navigation took " + (endTime - startTime) + "ms");
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

// Step 4: Clean test class - no logging/timing code in test methods
public class BrowserTest {
    public void testPageNavigation() {
        // Stack decorators to add both behaviors:
        // ChromeDriver -> wrapped by LoggingWebDriver -> wrapped by TimingWebDriver
        WebDriver driver = new TimingWebDriver(
            new LoggingWebDriver(
                new ChromeDriver()
            )
        );

        // Test method now only contains test logic
        driver.get("http://example.com");
        driver.quit();
    }
}

/*
Key Changes:
1. Separated behaviors (logging, timing) into decorator classes
2. Each decorator adds one behavior without changing WebDriver
3. Can combine decorators in any order
4. Test methods contain only test logic
5. Easy to add new behaviors by creating new decorators
*/
