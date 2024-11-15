import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {

    // WebDriver Factory class to centralize browser setup
    static class WebDriverFactory {
        public static WebDriver getDriver(String browserType) {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    return new ChromeDriver();
                case "firefox":
                    return new FirefoxDriver();
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }
        }
    }

    public void testInChrome() {
        // Using factory to get WebDriver instance - no hardcoding
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("http://example.com");
        System.out.println("Running test in Chrome");

        // Test steps here...

        driver.quit(); // Consistent cleanup
    }

    public void testInFirefox() {
        // Using factory to get WebDriver instance - no hardcoding
        WebDriver driver = WebDriverFactory.getDriver("firefox");
        driver.get("http://example.com");
        System.out.println("Running test in Firefox");

        // Test steps here...

        driver.quit(); // Consistent cleanup
    }

    // Benefits Summary:
    // - Centralized WebDriver setup in WebDriverFactory class.
    // - New browsers can be added in one place (WebDriverFactory) without modifying test methods.
    // - Improved maintainability and scalability.
    // - Reduced code duplication by using a consistent factory method for WebDriver instantiation.
}
