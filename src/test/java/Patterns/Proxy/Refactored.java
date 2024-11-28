/**
 * Proxy Pattern Implementation in Test Automation
 * Adds logging and error handling to browser operations
 */

/**
 * Common interface for browser interactions
 * Allows for real and proxy implementations
 */
interface Browser {
    void navigate(String url);
    void type(By locator, String text);
    void click(By locator);
}

/**
 * Real browser implementation using WebDriver
 * Does actual browser automation work
 */
class WebDriverBrowser implements Browser {
    private final WebDriver driver;

    public WebDriverBrowser() {
        this.driver = new ChromeDriver();
    }

    @Override
    public void navigate(String url) {
        driver.get(url);  // Direct WebDriver call
    }

    @Override
    public void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    @Override
    public void click(By locator) {
        driver.findElement(locator).click();
    }
}

/**
 * Proxy that adds logging and error handling
 * Wraps real browser without modifying it
 */
class BrowserProxy implements Browser {
    private final Browser browser;         // Wrapped browser
    private final List<String> actions;    // Action log

    public BrowserProxy(Browser browser) {
        this.browser = browser;
        this.actions = new ArrayList<>();
    }

    @Override
    public void navigate(String url) {
        try {
            actions.add("Navigating to: " + url);     // Pre-action logging
            browser.navigate(url);                     // Delegate to real browser
        } catch (Exception e) {
            actions.add("Navigation failed: " + e.getMessage());  // Error logging
            throw e;
        }
    }

    @Override
    public void type(By locator, String text) {
        try {
            // Mask sensitive data in logs
            String safeText = locator.toString().contains("password") ? "****" : text;
            actions.add("Typing '" + safeText + "' into: " + locator);
            browser.type(locator, text);
        } catch (Exception e) {
            actions.add("Type operation failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void click(By locator) {
        try {
            actions.add("Clicking: " + locator);
            browser.click(locator);
        } catch (Exception e) {
            actions.add("Click failed: " + e.getMessage());
            throw e;
        }
    }

    // Return copy of logs to prevent external modification
    public List<String> getActionLog() {
        return new ArrayList<>(actions);
    }
}

/**
 * Example showing proxy usage in tests
 */
class LoginTest {
    @Test
    public void testLogin() {
        // Wrap real browser with proxy
        Browser browser = new BrowserProxy(new WebDriverBrowser());

        // Perform login - proxy automatically handles logging
        browser.navigate("http://example.com/login");
        browser.type(By.id("username"), "testuser");
        browser.type(By.id("password"), "secretpass");
        browser.click(By.id("loginButton"));

        // Access logs for verification
        BrowserProxy proxy = (BrowserProxy) browser;
        proxy.getActionLog().forEach(System.out::println);
    }
}

/* How Proxy Pattern Helps Testing:
 * 1. Adds Behavior:
 *    - Automatic logging
 *    - Error handling
 *    - Performance tracking
 *
 * 2. Clean Code:
 *    - No logging in test code
 *    - Centralized error handling
 *    - Separation of concerns
 *
 * Common Use Cases:
 * - Test logging
 * - Error screenshots
 * - Retry mechanisms
 * - Performance monitoring
 */
