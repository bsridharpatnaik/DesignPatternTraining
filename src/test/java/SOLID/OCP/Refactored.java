/**
 * Step 1: Define browser interface
 * New browsers will implement this interface
 */
interface Browser {
    WebDriver createDriver();
}

/**
 * Step 2: Create concrete browser classes
 * Each browser has its own implementation
 */
class ChromeBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Chrome browser");
        return new ChromeDriver();
    }
}

class FirefoxBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Firefox browser");
        return new FirefoxDriver();
    }
}

class SafariBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        System.out.println("Setting up Safari browser");
        return new SafariDriver();  // Safari implementation
    }
}

/**
 * Step 3: Create factory using browser registry
 * No modification needed for new browsers
 */
class BrowserFactory {
    private final Map<String, Browser> browsers = new HashMap<>();

    public BrowserFactory() {
        // Register known browsers
        browsers.put("chrome", new ChromeBrowser());
        browsers.put("firefox", new FirefoxBrowser());
        browsers.put("safari", new SafariBrowser());
    }

    // Method to register new browsers without modifying code
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
 * Step 4: Usage example showing extensibility
 */
public class BrowserTest {
    private final BrowserFactory factory = new BrowserFactory();

    @Test
    public void testWithDifferentBrowsers() {
        // Using existing browsers
        runTest("chrome");
        runTest("firefox");

        // Adding new browser without modifying existing code
        factory.registerBrowser("edge", new EdgeBrowser());
        runTest("edge");
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

/* Benefits of OCP:
 * 1. Easy to add new browsers
 * 2. No modification of existing code
 * 3. Better separation of concerns
 * 4. More maintainable
 * 5. Reduced risk of bugs
 * 6. Better testability
 * 7. More flexible architecture
 */
