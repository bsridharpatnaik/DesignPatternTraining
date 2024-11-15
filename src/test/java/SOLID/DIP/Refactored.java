// Abstractions
public interface WebDriver {
    void get(String url);
}

public interface Database {
    void query(String sql);
}

public interface Reporter {
    void generateReport(String title);
}

// High-level module depending on abstractions
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
        driver.get("https://example.com");
        database.query("SELECT * FROM test_data");
        reporter.generateReport("Test Results");
    }
}

// Low-level modules implementing abstractions
public class ChromeDriverAdapter implements WebDriver {
    private ChromeDriver driver = new ChromeDriver();

    public void get(String url) {
        driver.get(url);
    }
}

public class MySQLDatabaseAdapter implements Database {
    private MySQLDatabase db = new MySQLDatabase();

    public void query(String sql) {
        db.query(sql);
    }
}

// Usage
TestRunner runner = new TestRunner(
    new ChromeDriverAdapter(),
    new MySQLDatabaseAdapter(),
    new HtmlReporter()
);
