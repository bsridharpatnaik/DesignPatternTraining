/**
 * TestRunner class with tight coupling to concrete implementations
 * Violates Dependency Inversion Principle
 */
public class TestRunner {
    // Problem 1: Direct dependencies on concrete classes
    private ChromeDriver driver;
    private MySQLDatabase database;
    private HtmlReporter reporter;

    public TestRunner() {
        // Problem 2: Direct instantiation of concrete classes
        this.driver = new ChromeDriver();
        this.database = new MySQLDatabase();
        this.reporter = new HtmlReporter();
    }

    public void runTest() {
        // Problem 3: Direct usage of concrete implementations
        driver.get("https://example.com");
        database.query("SELECT * FROM test_data");
        reporter.generateReport("Test Results");
    }

    /* Problems:
     * 1. High-level module (TestRunner) depends on low-level modules
     * 2. Hard to switch implementations (e.g., Firefox, PostgreSQL)
     * 3. Difficult to mock for unit testing
     * 4. Tight coupling makes code rigid and hard to change
     * 5. Cannot reuse code with different implementations
     * 6. Hard to test in isolation
     * 7. Violates "Program to an interface, not implementation"
     */
}
