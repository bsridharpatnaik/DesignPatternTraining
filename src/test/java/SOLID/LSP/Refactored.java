/**
 * Step 1: Define clear contract in base class
 */
abstract class Browser {
    abstract WebDriver launchBrowser();
    abstract void quitBrowser(WebDriver driver);
}

/**
 * Step 2: Chrome implementation following contract
 */
class ChromeBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching Chrome browser");
        return new ChromeDriver();
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
        System.out.println("Chrome browser quit");
    }
}

/**
 * Step 3: Firefox implementation following contract
 */
class FirefoxBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching Firefox browser");
        return new FirefoxDriver();
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
        System.out.println("Firefox browser quit");
    }
}

/**
 * Step 4: Headless implementation properly following contract
 */
class HeadlessBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching headless browser");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return new ChromeDriver(options);  // Returns valid WebDriver
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
        System.out.println("Headless browser quit");
    }
}

/**
 * Step 5: Clean client code that works with any browser
 */
public class BrowserTest {
    public void runTest(Browser browser) {
        // No extra checks needed - all browsers behave consistently
        WebDriver driver = browser.launchBrowser();
        driver.get("http://example.com");
        System.out.println("Running test...");
        browser.quitBrowser(driver);
    }

    public static void main(String[] args) {
        BrowserTest test = new BrowserTest();

        // All browsers can be used interchangeably
        test.runTest(new ChromeBrowser());
        test.runTest(new FirefoxBrowser());
        test.runTest(new HeadlessBrowser());
    }
}

/* Benefits of LSP:
 * 1. Consistent behavior across all subclasses
 * 2. No special case handling needed
 * 3. True polymorphism achieved
 * 4. Code is more reliable
 * 5. Easy to add new browser types
 * 6. Clean and maintainable code
 * 7. Better testing and debugging
 */
