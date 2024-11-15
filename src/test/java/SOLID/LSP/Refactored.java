
// In this refactored version, all subclasses adhere to the expectations
// defined by the base class, ensuring consistent behavior.


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

// Subclass for Headless browser (Correctly Adhering to LSP)
class HeadlessBrowser extends Browser {
    @Override
    WebDriver launchBrowser() {
        System.out.println("Launching headless browser...");
        // Implement headless browser setup (e.g., Chrome in headless mode)
        ChromeDriver driver = new ChromeDriver(); // Example: Using ChromeDriver in headless mode
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
        return driver;
    }

    @Override
    void quitBrowser(WebDriver driver) {
        driver.quit();
        System.out.println("Headless browser quit.");
    }
}

public class BrowserTest {

    public void runTest(Browser browser) {
        WebDriver driver = browser.launchBrowser();
        driver.get("http://example.com");
        System.out.println("Running test...");
        browser.quitBrowser(driver);
    }

    public static void main(String[] args) {
        BrowserTest test = new BrowserTest();

        // Works consistently for all subclasses
        test.runTest(new ChromeBrowser());
        test.runTest(new FirefoxBrowser());
        test.runTest(new HeadlessBrowser());
    }

    // Benefits:
    // - All subclasses adhere to the expectations of the base class.
    // - No additional checks are required in the test logic.
    // - Ensures substitutability: Any subclass can replace the base class without breaking functionality.
}
