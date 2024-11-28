/**
 * WebDriverManager using Singleton Pattern
 * Ensures single WebDriver instance across all tests
 */
public class WebDriverManager {
    // Static instance for Singleton pattern
    private static WebDriver driver;
    private static String currentBrowser;

    // Private constructor prevents external instantiation
    private WebDriverManager() {}

    /**
     * Core singleton method to get/create WebDriver
     * Creates new instance only when needed
     */
    public static synchronized WebDriver getDriver(String browserType) {
        if (driver == null || !browserType.equals(currentBrowser)) {
            // Cleanup existing driver on browser change
            if (driver != null) {
                driver.quit();
            }

            // Create new driver instance
            driver = switch (browserType.toLowerCase()) {
                case "chrome" -> new ChromeDriver();
                case "firefox" -> new FirefoxDriver();
                default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browserType
                );
            };
            currentBrowser = browserType;
        }
        return driver;
    }

    /**
     * Cleanup method for test completion
     * Ensures proper resource management
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
 * Test class demonstrating WebDriverManager usage
 */
public class BrowserTest {
    @Before
    public void setup() {
        // Single driver instance created and reused
        WebDriver driver = WebDriverManager.getDriver("chrome");
        driver.get("http://example.com");
    }

    @Test
    public void testLoginInChrome() {
        // Reuses existing driver instance
        WebDriver driver = WebDriverManager.getDriver("chrome");
        System.out.println("Running Chrome test");
        // Test steps...
    }

    @Test
    public void testLoginInFirefox() {
        // Automatically handles browser switch
        WebDriver driver = WebDriverManager.getDriver("firefox");
        System.out.println("Running Firefox test");
        // Test steps...
    }

    @AfterClass
    public static void tearDown() {
        WebDriverManager.quitDriver();  // Cleanup happens once
    }
}

/* Singleton Benefits in Testing:
 * 1. Resource Management:
 *    - Single browser instance
 *    - Efficient memory usage
 *    - Controlled lifecycle
 *
 * 2. Test Execution:
 *    - Faster tests (no repeated setup)
 *    - Consistent browser state
 *    - Clean resource handling
 *
 * Key Features:
 * - Lazy initialization
 * - Automatic cleanup
 * - Browser switching support
 * - Thread-safety potential
 */
