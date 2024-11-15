// High-level module directly depending on low-level module
public class TestRunner {
    private ChromeDriver driver;  // Concrete dependency
    private MySQLDatabase database;
    private HtmlReporter reporter;

    public TestRunner() {
        this.driver = new ChromeDriver();  // Direct instantiation
        this.database = new MySQLDatabase();
        this.reporter = new HtmlReporter();
    }

    public void runTest() {
        driver.get("https://example.com");
        database.query("SELECT * FROM test_data");
        reporter.generateReport("Test Results");
    }
}
