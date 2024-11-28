/**
 * Interface Segregation Principle Example in Test Automation
 * Instead of one large interface, we split functionality into focused interfaces
 */

/**
 * UI Actions Interface
 * Contains only UI-related operations
 * Used by classes that need to perform UI interactions
 */
public interface UiActions {
    void click(String element);
    void type(String element, String text);
    void dragAndDrop(String source, String target);
}

/**
 * API Actions Interface
 * Focused solely on API testing operations
 * Classes doing API testing implement only this interface
 */
public interface ApiActions {
    String sendGetRequest(String url);
    String sendPostRequest(String url, String body);
    void validateJsonResponse(String response);
}

/**
 * Database Actions Interface
 * Specific to database operations
 * Keeps database testing concerns separate
 */
public interface DatabaseActions {
    void executeQuery(String sql);
    void updateRecord(String table, String data);
}

/**
 * Reporting Interface
 * Handles only reporting-related methods
 * Can be implemented by any test class needing reporting
 */
public interface ReportingActions {
    void generateHtmlReport();
    void generatePdfReport();
    void takeScreenshot();
}

/**
 * API Test Class
 * Only implements ApiActions - doesn't need UI or DB methods
 * Shows how classes aren't forced to implement unnecessary methods
 */
public class ApiTestAutomation implements ApiActions {
    @Override
    public String sendGetRequest(String url) {
        System.out.println("Sending GET request to: " + url);
        return performGetRequest(url);
    }

    @Override
    public String sendPostRequest(String url, String body) {
        System.out.println("Sending POST request to: " + url);
        return performPostRequest(url, body);
    }

    @Override
    public void validateJsonResponse(String response) {
        System.out.println("Validating JSON response");
        validateJson(response);
    }
}

/**
 * UI Test Class
 * Implements both UI and Reporting interfaces
 * Shows how interfaces can be combined when needed
 */
public class UITestAutomation implements UiActions, ReportingActions {
    @Override
    public void click(String element) {
        System.out.println("Clicking: " + element);
    }

    @Override
    public void generateHtmlReport() {
        System.out.println("Generating HTML report");
    }

    // Other implementations...
}

/**
 * Full Stack Test Class
 * Uses composition to combine all functionalities
 * Shows how to use multiple interfaces without implementing them all
 */
public class FullStackTest {
    // Dependencies for different types of actions
    private final UiActions ui;
    private final ApiActions api;
    private final DatabaseActions db;
    private final ReportingActions reporter;

    // Constructor injection for all required components
    public FullStackTest(UiActions ui, ApiActions api,
                        DatabaseActions db, ReportingActions reporter) {
        this.ui = ui;
        this.api = api;
        this.db = db;
        this.reporter = reporter;
    }

    // Uses each component for its specific purpose
    public void runCompleteTest() {
        ui.click("loginButton");                    // UI action
        api.sendGetRequest("api/data");             // API action
        db.executeQuery("SELECT * FROM users");      // DB action
        reporter.generateHtmlReport();              // Reporting action
    }
}

/* Why This Design Works:
 * 1. Each interface has a single purpose
 * 2. No class is forced to implement methods it doesn't need
 * 3. Easy to add new interfaces without affecting existing code
 * 4. Clear separation of different test automation concerns
 *
 * Common Use Cases:
 * 1. API Testing: Use only ApiActions
 * 2. UI Testing: Combine UiActions with ReportingActions
 * 3. Database Testing: Use DatabaseActions alone
 * 4. Full Integration: Compose multiple interfaces as needed
 *
 * Practical Benefits:
 * 1. Smaller, focused interfaces are easier to implement
 * 2. Changes to one interface don't affect others
 * 3. Better code organization and maintainability
 * 4. More flexible and reusable components
 */
