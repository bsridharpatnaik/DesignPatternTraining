// Fat interface with too many responsibilities
public interface TestAutomation {
    // UI Testing
    void click(String element);
    void type(String element, String text);
    void dragAndDrop(String source, String target);

    // API Testing
    String sendGetRequest(String url);
    String sendPostRequest(String url, String body);
    void validateJsonResponse(String response);

    // Database Testing
    void executeQuery(String sql);
    void updateRecord(String table, String data);

    // Reporting
    void generateHtmlReport();
    void generatePdfReport();
    void takeScreenshot();
}

// Class forced to implement unnecessary methods
public class ApiTestAutomation implements TestAutomation {
    // Must implement UI methods even though not needed
    public void click(String element) {
        throw new UnsupportedOperationException();
    }
    public void type(String element, String text) {
        throw new UnsupportedOperationException();
    }
    public void dragAndDrop(String source, String target) {
        throw new UnsupportedOperationException();
    }

    // Actually needed API methods
    public String sendGetRequest(String url) {
        // Implementation
    }
    public String sendPostRequest(String url, String body) {
        // Implementation
    }
    // ... must implement all other methods
}
