import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Singleton WebDriverManager to ensure single browser instance
 * Controls the lifecycle of WebDriver across all tests
 */
public class WebDriverManager {
    private static WebDriver driver;  // Single shared instance
    private static String currentBrowser;  // Track current browser type

    // Private constructor prevents direct instantiation
    private WebDriverManager() {}

    /**
     * Get the single WebDriver instance
     * Creates new instance only if none exists or browser type changes
     */
    public static WebDriver getDriver(String browserType) {
        if (driver == null || !browserType.equals(currentBrowser)) {
            // Quit existing driver if browser type changes
            if (driver != null) {
                driver.quit();
            }

            // Initialize new driver based on browser type
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
            currentBrowser = browserType;
        }
        return driver;
    }

    /**
     * Clean up WebDriver instance
     * Should be called after all tests are complete
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            currentBrowser = null;
        }
    }
}

/**
 * Test class using Singleton WebDriverManager
 * Benefits from shared WebDriver instance
 */
public class BrowserTest {
    @Before
    public void setup() {
        // WebDriver setup happens only once
        WebDriver driver = WebDriverManager.getDriver("chrome");
        driver.get("http://example.com");
    }

    @Test
    public void testLoginInChrome() {
        // Reuses existing Chrome WebDriver
        WebDriver driver = WebDriverManager.getDriver("chrome");
        System.out.println("Running login test in Chrome");
        // Test steps...
    }

    @Test
    public void testLoginInFirefox() {
        // Automatically handles browser switch
        WebDriver driver = WebDriverManager.getDriver("firefox");
        System.out.println("Running login test in Firefox");
        // Test steps...
    }

    @AfterClass
    public static void tearDown() {
        // Single cleanup for all tests
        WebDriverManager.quitDriver();
    }
}

/* Benefits:
 * 1. Single WebDriver instance shared across tests
 * 2. Efficient resource usage
 * 3. Faster test execution
 * 4. Centralized browser management
 * 5. Automatic cleanup
 * 6. Easy to switch browsers
 * 7. Thread-safe (can be enhanced with synchronization)
 */
