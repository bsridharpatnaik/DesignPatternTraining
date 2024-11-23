/**
 * Step 1: Segregate interfaces by responsibility
 * Each interface handles one specific aspect of testing
 */
public interface UiActions {
    void click(String element);
    void type(String element, String text);
    void dragAndDrop(String source, String target);
}

public interface ApiActions {
    String sendGetRequest(String url);
    String sendPostRequest(String url, String body);
    void validateJsonResponse(String response);
}

public interface DatabaseActions {
    void executeQuery(String sql);
    void updateRecord(String table, String data);
}

public interface ReportingActions {
    void generateHtmlReport();
    void generatePdfReport();
    void takeScreenshot();
}

/**
 * Step 2: Implement only needed interfaces
 * Classes can pick and choose what they need
 */
public class ApiTestAutomation implements ApiActions {
    @Override
    public String sendGetRequest(String url) {
        return performGetRequest(url);
    }

    @Override
    public String sendPostRequest(String url, String body) {
        return performPostRequest(url, body);
    }

    @Override
    public void validateJsonResponse(String response) {
        validateJson(response);
    }
}

/**
 * Step 3: Combine interfaces when needed
 * Classes can implement multiple focused interfaces
 */
public class UITestAutomation implements UiActions, ReportingActions {
    @Override
    public void click(String element) {
        // UI click implementation
    }

    @Override
    public void generateHtmlReport() {
        // Report generation implementation
    }
    // Other method implementations...
}

/**
 * Step 4: Create composite test classes when needed
 */
public class FullStackTest {
    private final UiActions ui;
    private final ApiActions api;
    private final DatabaseActions db;
    private final ReportingActions reporter;

    public FullStackTest(UiActions ui, ApiActions api,
                        DatabaseActions db, ReportingActions reporter) {
        this.ui = ui;
        this.api = api;
        this.db = db;
        this.reporter = reporter;
    }

    public void runCompleteTest() {
        ui.click("loginButton");
        api.sendGetRequest("api/data");
        db.executeQuery("SELECT * FROM users");
        reporter.generateHtmlReport();
    }
}

/* Benefits of ISP:
 * 1. Focused interfaces with single responsibility
 * 2. Classes only implement what they need
 * 3. Better separation of concerns
 * 4. Easier to maintain and modify
 * 5. More flexible and reusable code
 * 6. Clearer code organization
 * 7. Better adherence to SOLID principles
 */
