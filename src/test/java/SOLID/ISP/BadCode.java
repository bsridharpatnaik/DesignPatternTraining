/**
 * Fat interface with too many unrelated responsibilities
 * Forces classes to implement methods they don't need
 */
public interface TestAutomation {
    // UI Testing methods
    void click(String element);
    void type(String element, String text);
    void dragAndDrop(String source, String target);

    // API Testing methods
    String sendGetRequest(String url);
    String sendPostRequest(String url, String body);
    void validateJsonResponse(String response);

    // Database Testing methods
    void executeQuery(String sql);
    void updateRecord(String table, String data);

    // Reporting methods
    void generateHtmlReport();
    void generatePdfReport();
    void takeScreenshot();
}

/**
 * Class forced to implement unnecessary methods
 * Violates Interface Segregation Principle
 */
public class ApiTestAutomation implements TestAutomation {
    // Problem: Must implement unused UI methods
    @Override
    public void click(String element) {
        throw new UnsupportedOperationException("Not needed for API testing");
    }

    @Override
    public void type(String element, String text) {
        throw new UnsupportedOperationException("Not needed for API testing");
    }

    @Override
    public void dragAndDrop(String source, String target) {
        throw new UnsupportedOperationException("Not needed for API testing");
    }

    // Actual API methods needed
    @Override
    public String sendGetRequest(String url) {
        return "API Response";
    }

    /* Problems:
     * 1. Classes forced to implement unnecessary methods
     * 2. Interface too large and unfocused
     * 3. Changes to interface affect unrelated classes
     * 4. Violates Single Responsibility Principle
     * 5. Hard to maintain and understand
     * 6. Poor separation of concerns
     * 7. Tight coupling between different types of testing
     */
}
