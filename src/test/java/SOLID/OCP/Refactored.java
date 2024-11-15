
/* In this refactored version, the Open/Closed Principle is applied.
Each browser setup is encapsulated in its own class,
and the framework is extended by adding
new classes rather than modifying existing code. */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Base interface for browser setup
interface Browser {
    WebDriver createDriver();
}

// Concrete class for Chrome browser
class ChromeBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Chrome browser...");
        return new ChromeDriver();
    }
}

// Concrete class for Firefox browser
class FirefoxBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Firefox browser...");
        return new FirefoxDriver();
    }
}

// Concrete class for Safari browser
class SafariBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Safari browser...");
        // Safari setup logic here
        return null; // Replace with actual SafariDriver setup
    }
}

// Factory class to get the appropriate browser
class BrowserFactory {
    public Browser getBrowser(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return new ChromeBrowser();
            case "firefox":
                return new FirefoxBrowser();
            case "safari":
                return new SafariBrowser();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }
}

// Test class using the factory
public class BrowserTest {

    public void testOnBrowser(String browserType) {
        BrowserFactory factory = new BrowserFactory();
        Browser browser = factory.getBrowser(browserType);

        WebDriver driver = browser.createDriver();
        if (driver != null) {
            driver.get("http://example.com");
            System.out.println("Running test on: " + browserType);
            driver.quit();
        }
    }

    public static void main(String[] args) {
        BrowserTest test = new BrowserTest();

        // Test on Chrome
        test.testOnBrowser("chrome");

        // Test on Firefox
        test.testOnBrowser("firefox");

        // Test on Safari
        test.testOnBrowser("safari");
    }

    // Benefits:
    // - Adding a new browser (e.g., Edge) requires creating a new class without modifying existing code.
    // - Framework is open for extension but closed for modification.
    // - Improved scalability and reduced risk of introducing bugs.
}
