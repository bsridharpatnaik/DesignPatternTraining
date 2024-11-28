/**
 * Factory Pattern in Test Automation
 * Centralizes browser creation and configuration
 */

/**
 * Factory class that handles WebDriver creation
 * Single point of responsibility for browser instantiation
 */
class WebDriverFactory {
    /**
     * Creates WebDriver instance based on browser type
     * @param browserType Name of browser ("chrome", "firefox")
     * @return Configured WebDriver instance
     */
    public static WebDriver getDriver(String browserType) {
        // Switch expression for browser selection
        return switch (browserType.toLowerCase()) {
            case "chrome" -> {
                // Can add Chrome-specific configuration here
                yield new ChromeDriver();
            }
            case "firefox" -> {
                // Can add Firefox-specific configuration here
                yield new FirefoxDriver();
            }
            default -> throw new IllegalArgumentException(
                "Unsupported browser: " + browserType
            );
        };
    }
}

/**
 * Test class using factory pattern
 * Shows how factory simplifies test code
 */
public class BrowserTest {
    // Helper method to get WebDriver instance
    private WebDriver getDriver(String browser) {
        return WebDriverFactory.getDriver(browser);
    }

    /**
     * Generic test method that works with any browser
     * Shows how factory enables flexible browser selection
     */
    public void runTest(String browser) {
        WebDriver driver = getDriver(browser);
        try {
            // Test steps are browser-agnostic
            driver.get("http://example.com");
            System.out.println("Running test in " + browser);
            // More test steps...
        } finally {
            driver.quit();  // Ensures cleanup
        }
    }

    /**
     * Specific test methods for different browsers
     * Shows how same test can run on multiple browsers
     */
    @Test
    public void testInChrome() {
        runTest("chrome");
    }

    @Test
    public void testInFirefox() {
        runTest("firefox");
    }

    // Easy to add new browser tests:
    @Test
    public void testInEdge() {
        runTest("edge");  // Just add case in factory
    }
}

/* How Factory Pattern Helps:
 * 1. Encapsulation:
 *    - Browser creation logic in one place
 *    - Configuration details hidden from tests
 *
 * 2. Maintainability:
 *    - Single point for browser setup changes
 *    - Easy to add new browser support
 *
 * 3. Test Simplification:
 *    - Tests don't handle browser details
 *    - Clean, focused test code
 *
 * Common Extensions:
 * - Add browser capabilities configuration
 * - Handle remote WebDriver setup
 * - Include logging/monitoring
 */
