// Segregated interfaces for specific functionalities
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

// Classes only implement what they need
public class ApiTestAutomation implements ApiActions {
    public String sendGetRequest(String url) {
        // Implementation
    }

    public String sendPostRequest(String url, String body) {
        // Implementation
    }

    public void validateJsonResponse(String response) {
        // Implementation
    }
}

// Can combine interfaces if needed
public class FullUITest implements UiActions, ReportingActions {
    // Only implements UI and reporting methods
}
