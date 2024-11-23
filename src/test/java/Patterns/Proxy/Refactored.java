/**
 * Common interface for browser operations
 * This abstraction allows us to swap implementations or add proxies
 */
public interface Browser {
    void navigate(String url);
    void type(By locator, String text);
    void click(By locator);
}

/**
 * Real implementation that wraps WebDriver
 * This is the actual object that does the work
 */
public class WebDriverBrowser implements Browser {
    private WebDriver driver;

    public WebDriverBrowser() {
        this.driver = new ChromeDriver();
    }

    @Override
    public void navigate(String url) {
        driver.get(url);
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
 * Proxy that adds action logging around browser operations
 * This demonstrates the Proxy Pattern by wrapping the real browser
 * and adding behavior without modifying the original implementation
 */
public class BrowserProxy implements Browser {
    private final Browser browser;        // Real browser being wrapped
    private final List<String> actions;   // Log of all actions performed

    public BrowserProxy(Browser browser) {
        this.browser = browser;
        this.actions = new ArrayList<>();
    }

    @Override
    public void navigate(String url) {
        // Log the action before performing it
        actions.add("Navigating to: " + url);
        try {
            // Delegate to real browser
            browser.navigate(url);
        } catch (Exception e) {
            // Could add error logging, screenshots, or retry logic here
            actions.add("Error during navigation: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void type(By locator, String text) {
        // Log the action, hiding sensitive data like passwords
        String safeText = locator.toString().contains("password") ? "****" : text;
        actions.add("Typing '" + safeText + "' into: " + locator);
        try {
            browser.type(locator, text);
        } catch (Exception e) {
            actions.add("Error while typing: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void click(By locator) {
        actions.add("Clicking on: " + locator);
        try {
            browser.click(locator);
        } catch (Exception e) {
            actions.add("Error while clicking: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Provides access to the action log for debugging
     */
    public List<String> getActions() {
        return new ArrayList<>(actions); // Return copy to prevent modification
    }
}

/**
 * Example test class showing how to use the proxy
 * Benefits:
 * 1. All actions are logged automatically
 * 2. Sensitive data is masked
 * 3. Error handling is centralized
 * 4. Real browser implementation can be swapped easily
 */
public class WebTest {
    @Test
    public void testUserLogin() {
        // Create real browser wrapped in proxy
        Browser browser = new BrowserProxy(new WebDriverBrowser());

        // Perform test actions - notice how clean this is
        browser.navigate("http://example.com/login");
        browser.type(By.id("username"), "testuser");
        browser.type(By.id("password"), "password");
        browser.click(By.id("loginButton"));

        // Get action log for debugging
        BrowserProxy proxy = (BrowserProxy) browser;
        List<String> actions = proxy.getActions();

        // Print actions - useful for debugging test failures
        actions.forEach(System.out::println);

        // Example output:
        // Navigating to: http://example.com/login
        // Typing 'testuser' into: By.id: username
        // Typing '****' into: By.id: password
        // Clicking on: By.id: loginButton
    }
}
