// REST API client with its own interface
class RestAPIClient {
    public void authenticate(String token) {
        System.out.println("Authenticating REST API with token: " + token);
    }

    public String fetchData(String endpoint) {
        return "REST API Data";
    }
}

// SOAP API client with different interface
class SoapAPIClient {
    public void login(String username, String password) {
        System.out.println("Logging into SOAP API with username: " + username);
    }

    public String retrieveData(String operation) {
        return "SOAP API Data";
    }
}

public class APITest {
    public void testRestAPI() {
        // Problem 1: Direct dependency on REST API interface
        RestAPIClient restClient = new RestAPIClient();
        restClient.authenticate("token123");
        restClient.fetchData("/data");
    }

    public void testSoapAPI() {
        // Problem 2: Separate method needed for SOAP API
        // because interfaces don't match
        SoapAPIClient soapClient = new SoapAPIClient();
        soapClient.login("user", "pass");
        soapClient.retrieveData("getData");
    }

    /* Major Issues:
     * 1. Duplicate test code for each API type
     * 2. Different method names for same operations
     * 3. Can't reuse test logic across API types
     * 4. Adding new API requires new test methods
     * 5. No unified way to handle different APIs
     */
}
