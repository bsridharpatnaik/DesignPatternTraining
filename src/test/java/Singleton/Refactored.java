import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {

    // Singleton WebDriverManager to manage a single WebDriver instance
    static class WebDriverManager {
        private static WebDriver driver;

        // Private constructor prevents instantiation
        private WebDriverManager() {}

        // Public method to get the single WebDriver instance
        public static WebDriver getDriver(String browserType) {
            if (driver == null) {
                // Initialize WebDriver only once
                switch (browserType.toLowerCase()) {
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browserType);
                }
            }
            return driver;
        }

        // Method to quit WebDriver
        public static void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }

    public void testLoginInChrome() {
        // Using Singleton WebDriver instance
        WebDriver driver = WebDriverManager.getDriver("chrome");
        driver.get("http://example.com");
        System.out.println("Running login test in Chrome");

        // Test steps for login...
    }

    public void testLoginInFirefox() {
        WebDriver driver = WebDriverManager.getDriver("firefox");
        driver.get("http://example.com");
        System.out.println("Running login test in Firefox");

        // Test steps for login...
    }

    // After all tests, WebDriverManager.quitDriver() can be called once to close the driver.

    // Benefits Summary:
    // - Ensures a single instance of WebDriver across multiple tests, saving memory and resources.
    // - Centralized control over the WebDriver lifecycle with a consistent setup and teardown process.
    // - Easy to maintain and extend without modifying test code.
}
