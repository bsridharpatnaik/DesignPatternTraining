// REST API client
class RestAPIClient {
    public void authenticate(String token) {
        System.out.println("Authenticating REST API with token: " + token);
    }

    public String fetchData(String endpoint) {
        System.out.println("Fetching data from REST API endpoint: " + endpoint);
        return "REST API Data";
    }
}

// SOAP API client
class SoapAPIClient {
    public void login(String username, String password) {
        System.out.println("Logging into SOAP API with username: " + username);
    }

    public String retrieveData(String operation) {
        System.out.println("Retrieving data from SOAP API operation: " + operation);
        return "SOAP API Data";
    }
}

// Test class interacting directly with APIs
public class APITest {

    public void testRestAPI() {
        RestAPIClient restClient = new RestAPIClient();
        restClient.authenticate("testToken"); // REST API authentication
        String data = restClient.fetchData("/testEndpoint"); // REST API data fetch
        System.out.println("REST API Response: " + data);
    }

    public void testSoapAPI() {
        SoapAPIClient soapClient = new SoapAPIClient();
        soapClient.login("testUser", "testPassword"); // SOAP API login
        String data = soapClient.retrieveData("getDetails"); // SOAP API data fetch
        System.out.println("SOAP API Response: " + data);
    }

    // Problems:
    // - Test logic is tightly coupled with the REST and SOAP API interfaces.
    // - Code duplication: Separate logic for handling each API.
    // - High maintenance: Changes in API interfaces require updates in all test cases.
}
