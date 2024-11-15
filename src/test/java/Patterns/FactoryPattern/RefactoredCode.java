//This refactored code example uses the Factory Pattern to centralize WebDriver instantiation,
//reducing code duplication and improving maintainability.

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

/*Explanation of Changes
Centralized WebDriver Instantiation: The WebDriverFactory class handles WebDriver creation, making it easier to add new browsers by updating only the factory method.
Reduced Duplication: Each test method now uses the WebDriverFactory.getDriver method, reducing code duplication and making the tests cleaner.
Scalability: If a new browser needs to be added (e.g., Safari), it can be done in the factory class without modifying each test method.
Consistent Cleanup: All tests now follow a consistent pattern for starting and quitting WebDriver sessions.*/
