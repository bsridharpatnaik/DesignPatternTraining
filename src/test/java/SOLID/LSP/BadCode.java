/**
 * Base browser class defining contract
 */
abstract class Browser {
    abstract WebDriver launchBrowser();
    abstract void quitBrowser(WebDriver driver);
}

/**
 * Chrome implementation - follows contract
 */
class ChromeBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        return new ChromeDriver();
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
    }
}

/**
 * Firefox implementation - follows contract
 */
class FirefoxBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        return new FirefoxDriver();
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
    }
}

/**
 * Headless browser - VIOLATES LSP
 * Does not fulfill the contract of base class
 */
class HeadlessBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        // Problem 1: Returns null instead of WebDriver
        return null;
    }

    @Override
    void quitBrowser(WebDriver driver) {
        // Problem 2: Doesn't actually quit the browser
        System.out.println("Cannot quit headless browser");
    }
}

public class BrowserTest {
    public void runTest(Browser browser) {
        // Problem 3: Need extra checks due to LSP violation
        WebDriver driver = browser.launchBrowser();
        if (driver != null) {  // Shouldn't need this check
            driver.get("http://example.com");
            browser.quitBrowser(driver);
        } else {
            System.out.println("Browser launch failed");
        }
    }

    /* Problems:
     * 1. Subclass breaks parent's contract
     * 2. Client needs extra null checks
     * 3. Can't substitute HeadlessBrowser for Browser
     * 4. Unexpected behavior in subclass
     * 5. Breaks polymorphism
     * 6. Makes code unreliable
     * 7. Hard to maintain and debug
     */
}
