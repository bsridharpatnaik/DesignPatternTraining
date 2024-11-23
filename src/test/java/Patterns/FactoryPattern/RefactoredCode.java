// Step 1: Create WebDriver Factory
class WebDriverFactory {
    public static WebDriver getDriver(String browserType) {
        return switch (browserType.toLowerCase()) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserType);
        };
    }
}

// Step 2: Use factory in test class
public class BrowserTest {
    private WebDriver getDriver(String browser) {
        return WebDriverFactory.getDriver(browser);
    }

    public void runTest(String browser) {
        // Get driver from factory
        WebDriver driver = getDriver(browser);

        try {
            driver.get("http://example.com");
            System.out.println("Running test in " + browser);
            // Test steps...
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testInChrome() {
        runTest("chrome");
    }

    @Test
    public void testInFirefox() {
        runTest("firefox");
    }
}

/*
Key Changes:
1. Centralized WebDriver creation in factory
2. Single point for driver configuration
3. Easy to add new browsers
4. Consistent driver initialization
5. Dynamic browser selection
6. Clean test methods
7. Better error handling
*/
