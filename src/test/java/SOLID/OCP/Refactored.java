/**
 * Open/Closed Principle Example in Test Automation
 * Shows how to add new browser support without modifying existing code
 */

/**
 * Browser interface - the key to extensibility
 * New browser types only need to implement this interface
 * This is what makes our code "Open for extension"
 */
interface Browser {
    WebDriver createDriver();  // Single responsibility: create a WebDriver instance
}

/**
 * Concrete browser implementations
 * Each class handles its specific browser setup
 * This is where we extend functionality without modifying existing code
 */
class ChromeBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Chrome browser");
        // Chrome-specific setup can be added here
        return new ChromeDriver();
    }
}

class FirefoxBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Firefox browser");
        // Firefox-specific setup can be added here
        return new FirefoxDriver();
    }
}

class SafariBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Safari browser");
        // Safari-specific setup can be added here
        return new SafariDriver();
    }
}

/**
 * BrowserFactory - manages browser creation
 * This class is "Closed for modification"
 * New browsers can be added without changing this code
 */
class BrowserFactory {
    // Registry of all supported browsers
    private final Map<String, Browser> browsers = new HashMap<>();

    public BrowserFactory() {
        // Register default browsers
        browsers.put("chrome", new ChromeBrowser());
        browsers.put("firefox", new FirefoxBrowser());
        browsers.put("safari", new SafariBrowser());
    }

    /**
     * Key method that enables extensibility
     * New browsers can be added at runtime
     */
    public void registerBrowser(String name, Browser browser) {
        browsers.put(name.toLowerCase(), browser);
    }

    public Browser getBrowser(String browserType) {
        Browser browser = browsers.get(browserType.toLowerCase());
        if (browser == null) {
            throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
        return browser;
    }
}

/**
 * Test class showing how to use the framework
 * Demonstrates how easy it is to add new browsers
 */
public class BrowserTest {
    private final BrowserFactory factory = new BrowserFactory();

    @Test
    public void testWithDifferentBrowsers() {
        // Use existing browsers without modification
        runTest("chrome");
        runTest("firefox");

        // Add new browser support without changing existing code
        factory.registerBrowser("edge", new EdgeBrowser());
        runTest("edge");  // Works seamlessly with new browser
    }

    private void runTest(String browserType) {
        Browser browser = factory.getBrowser(browserType);
        WebDriver driver = browser.createDriver();

        try {
            driver.get("http://example.com");
            System.out.println("Running test on: " + browserType);
        } finally {
            driver.quit();
        }
    }
}

/* How OCP is Achieved Here:
 * 1. Browser interface is the extension point
 * 2. BrowserFactory doesn't need modification for new browsers
 * 3. Each new browser is a new class, not a code change
 * 4. Runtime registration allows dynamic browser addition
 *
 * Practical Benefits:
 * 1. Add new browsers without risk to existing code
 * 2. No need to modify test code for new browsers
 * 3. Easy to maintain and extend
 * 4. Supports test automation framework growth
 *
 * Example of Adding New Browser:
 * 1. Create new class implementing Browser interface
 * 2. Register it with BrowserFactory
 * 3. Existing code continues working unchanged
 */
