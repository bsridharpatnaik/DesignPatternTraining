/**
 * Base Browser class defining the contract that all browser implementations must follow
 * This is crucial for LSP - the contract established here must be honored by all subclasses
 */
abstract class Browser {
    // All browsers must implement these methods consistently
    abstract WebDriver launchBrowser();   // Must return a valid WebDriver
    abstract void quitBrowser(WebDriver driver);  // Must properly clean up resources
}

/**
 * Chrome browser implementation
 * Follows the LSP principle by:
 * 1. Returning valid WebDriver from launchBrowser
 * 2. Properly implementing quit behavior
 * 3. Not adding any extra preconditions
 */
class ChromeBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching Chrome browser");
        return new ChromeDriver();  // Returns valid WebDriver as promised
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();  // Fulfills the cleanup contract
        System.out.println("Chrome browser quit");
    }
}

/**
 * Firefox browser implementation
 * Follows same contract as ChromeBrowser
 * Can be substituted anywhere ChromeBrowser is used
 */
class FirefoxBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching Firefox browser");
        return new FirefoxDriver();  // Behaves same as Chrome's WebDriver
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();  // Same cleanup behavior as Chrome
        System.out.println("Firefox browser quit");
    }
}

/**
 * Headless browser implementation
 * Shows how to extend functionality while maintaining the contract
 * Even though it's headless, it still returns a valid WebDriver
 */
class HeadlessBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching headless browser");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        // Still returns valid WebDriver despite being headless
        return new ChromeDriver(options);
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();  // Same cleanup behavior as other browsers
        System.out.println("Headless browser quit");
    }
}

/**
 * Test class demonstrating LSP in action
 * This class works with ANY browser type without modification
 * This is the power of LSP - true substitutability
 */
public class BrowserTest {
    public void runTest(Browser browser) {
        // Notice: No instanceOf checks or special cases needed
        // Any Browser subclass will work here without modification
        WebDriver driver = browser.launchBrowser();
        driver.get("http://example.com");
        System.out.println("Running test...");
        browser.quitBrowser(driver);
    }

    public static void main(String[] args) {
        BrowserTest test = new BrowserTest();

        // Demonstrates substitutability - all browser types work the same way
        test.runTest(new ChromeBrowser());     // Works with Chrome
        test.runTest(new FirefoxBrowser());    // Works with Firefox
        test.runTest(new HeadlessBrowser());   // Works with Headless
    }
}

/* Why This Code Follows LSP:
 * 1. All browser implementations fulfill the base class contract
 * 2. Subclasses don't require special handling or checks
 * 3. Client code (BrowserTest) works with any browser type
 * 4. No unexpected behaviors in subclasses
 * 5. Each subclass can be used wherever base class is expected
 *
 * Real-World Benefits:
 * 1. Can add new browser types without changing test code
 * 2. No browser-specific logic needed in test code
 * 3. Tests are more maintainable and reliable
 * 4. Easy to switch between browser types
 * 5. Reduced risk of bugs when adding new browsers
 */
