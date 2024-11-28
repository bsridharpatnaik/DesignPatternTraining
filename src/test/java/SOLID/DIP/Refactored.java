/**
 * Step 1: Define interfaces (abstractions)
 * These interfaces define WHAT needs to be done, not HOW
 * This allows us to easily swap implementations
 */
interface WebBrowser {
    void navigate(String url);
    void quit();
}

interface TestData {
    void loadTestData(String dataSet);
}

interface TestReporter {
    void logStep(String step);
}

/**
 * Step 2: Create concrete implementations
 * Each class implements HOW to do something
 * We can have multiple implementations of each interface
 */
class ChromeBrowser implements WebBrowser {
    // Concrete implementation for Chrome
    private ChromeDriver driver = new ChromeDriver();

    @Override
    public void navigate(String url) {
        driver.get(url);  // Actual Chrome-specific navigation
    }

    @Override
    public void quit() {
        driver.quit();    // Chrome-specific cleanup
    }
}

class FirefoxBrowser implements WebBrowser {
    // Another implementation - shows how easy it is to add new browsers
    private FirefoxDriver driver = new FirefoxDriver();

    @Override
    public void navigate(String url) {
        driver.get(url);  // Firefox-specific navigation
    }

    @Override
    public void quit() {
        driver.quit();    // Firefox-specific cleanup
    }
}

/**
 * Step 3: Main test class that uses abstractions
 * This class doesn't know or care about specific implementations
 * It works with interfaces, not concrete classes
 */
public class AutomatedTest {
    // Declaring dependencies using interfaces, not concrete classes
    private final WebBrowser browser;
    private final TestData testData;
    private final TestReporter reporter;

    /**
     * Constructor injection - dependencies are passed in
     * This is better than creating them inside the class
     * Makes the class flexible and testable
     */
    public AutomatedTest(WebBrowser browser, TestData testData, TestReporter reporter) {
        this.browser = browser;
        this.testData = testData;
        this.reporter = reporter;
    }

    public void runLoginTest() {
        // Notice how we're using interface methods
        // We don't know or care which specific implementations we're using
        testData.loadTestData("login_data");
        browser.navigate("https://example.com");
        reporter.logStep("Navigated to website");
    }
}

/**
 * Step 4: Example showing how to use the framework
 * Demonstrates the flexibility of our design
 */
public class TestExecution {
    @Test
    public void executeTest() {
        // We can easily switch implementations
        AutomatedTest testWithChrome = new AutomatedTest(
            new ChromeBrowser(),                    // Using Chrome
            new MySQLData(),                        // Using MySQL
            step -> System.out.println(step)        // Simple console logger
        );

        // Same test with different implementations
        AutomatedTest testWithFirefox = new AutomatedTest(
            new FirefoxBrowser(),                   // Using Firefox
            new ExcelData(),                        // Using Excel
            new ExtentReporter()                    // Using Extent Reports
        );

        testWithChrome.runLoginTest();
        testWithFirefox.runLoginTest();
    }

    @Test
    public void testWithMocks() {
        // Easy to mock for unit testing
        WebBrowser mockBrowser = mock(WebBrowser.class);
        TestData mockData = mock(TestData.class);
        TestReporter mockReporter = mock(TestReporter.class);

        AutomatedTest test = new AutomatedTest(mockBrowser, mockData, mockReporter);
        test.runLoginTest();

        // Can verify interactions
        verify(mockBrowser).navigate(anyString());
    }
}

/* Key Benefits of this Design:
 * 1. FLEXIBILITY: Easy to switch between different browsers, data sources, or reporters
 * 2. TESTABILITY: Can easily mock dependencies for unit testing
 * 3. MAINTAINABILITY: Changes to one implementation don't affect others
 * 4. EXTENSIBILITY: New implementations can be added without changing existing code
 * 5. SEPARATION OF CONCERNS: Each class has a single, clear responsibility
 */
