import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Base class for browsers
abstract class Browser {
    abstract WebDriver launchBrowser();
    abstract void quitBrowser(WebDriver driver);
}

// Subclass for Chrome browser
class ChromeBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching Chrome browser...");
        return new ChromeDriver();
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
        System.out.println("Chrome browser quit.");
    }
}

// Subclass for Firefox browser
class FirefoxBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching Firefox browser...");
        return new FirefoxDriver();
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
        System.out.println("Firefox browser quit.");
    }
}

// Subclass for Headless browser (LSP Violation)
class HeadlessBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching headless browser...");
        return null; // Violation: Does not return a valid WebDriver
    }

    @Override
    void quitBrowser(WebDriver driver) {
        System.out.println("Cannot quit a headless browser."); // Unexpected behavior
    }
}

public class BrowserTest {

    public void runTest(Browser browser) {
        WebDriver driver = browser.launchBrowser();
        if (driver != null) {
            driver.get("http://example.com");
            System.out.println("Running test...");
            browser.quitBrowser(driver);
        } else {
            System.out.println("Browser launch failed."); // Additional check due to LSP violation
        }
    }

    public static void main(String[] args) {
        BrowserTest test = new BrowserTest();

        // Works as expected
        test.runTest(new ChromeBrowser());
        test.runTest(new FirefoxBrowser());

        // Fails due to LSP violation
        test.runTest(new HeadlessBrowser());
    }

    // Problems:
    // - HeadlessBrowser does not behave as expected (launchBrowser returns null).
    // - Test logic requires additional checks to handle specific subclasses.
    // - Violates the promise of the base class, breaking substitutability.
}
