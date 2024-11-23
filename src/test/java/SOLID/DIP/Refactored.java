/**
 * Step 1: Define abstractions (interfaces)
 * High-level and low-level modules depend on abstractions
 */
public interface WebDriver {
    void get(String url);
    void quit();
}

public interface Database {
    void query(String sql);
    void close();
}

public interface Reporter {
    void generateReport(String title);
}

/**
 * Step 2: High-level module depending on abstractions
 * TestRunner now works with interfaces instead of concrete classes
 */
public class TestRunner {
    private final WebDriver driver;
    private final Database database;
    private final Reporter reporter;

    // Dependencies injected through constructor
    public TestRunner(WebDriver driver, Database database, Reporter reporter) {
        this.driver = driver;
        this.database = database;
        this.reporter = reporter;
    }

    public void runTest() {
        // Working with abstractions
        driver.get("https://example.com");
        database.query("SELECT * FROM test_data");
        reporter.generateReport("Test Results");
    }
}

/**
 * Step 3: Implement low-level modules
 * Each adapter implements the corresponding interface
 */
public class ChromeDriverAdapter implements WebDriver {
    private final ChromeDriver driver;

    public ChromeDriverAdapter() {
        this.driver = new ChromeDriver();
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public void quit() {
        driver.quit();
    }
}

public class FirefoxDriverAdapter implements WebDriver {
    private final FirefoxDriver driver;

    public FirefoxDriverAdapter() {
        this.driver = new FirefoxDriver();
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public void quit() {
        driver.quit();
    }
}

/**
 * Step 4: Usage example showing flexibility
 */
public class TestExample {
    @Test
    public void demonstrateFlexibility() {
        // Can easily switch implementations
        WebDriver driver = new ChromeDriverAdapter();
        Database database = new MySQLDatabaseAdapter();
        Reporter reporter = new HtmlReporter();

        TestRunner runner = new TestRunner(driver, database, reporter);
        runner.runTest();
    }

    @Test
    public void demonstrateMocking() {
        // Easy to mock for testing
        WebDriver mockDriver = mock(WebDriver.class);
        Database mockDatabase = mock(Database.class);
        Reporter mockReporter = mock(Reporter.class);

        TestRunner runner = new TestRunner(mockDriver, mockDatabase, mockReporter);
        runner.runTest();

        // Can verify interactions
        verify(mockDriver).get(anyString());
    }
}

/* Benefits of DIP:
 * 1. Loose coupling between modules
 * 2. Easy to switch implementations
 * 3. Better testability with mocking
 * 4. Flexible and extensible design
 * 5. Reusable components
 * 6. Clear separation of concerns
 * 7. Better adherence to SOLID principles
 */
