

/*   In this refactored version, the Adapter Pattern is applied to provide a
unified interface for interacting with both APIs.
This approach decouples the test logic from the API implementations   */

// Unified interface expected by the test framework
interface APIAdapter {
    void authenticate(String credentials);
    String fetchData(String query);
}

// Adapter for REST API
class RestAPIAdapter implements APIAdapter {
    private RestAPIClient restClient;

    public RestAPIAdapter(RestAPIClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void authenticate(String token) {
        restClient.authenticate(token);
    }

    @Override
    public String fetchData(String endpoint) {
        return restClient.fetchData(endpoint);
    }
}

// Adapter for SOAP API
class SoapAPIAdapter implements APIAdapter {
    private SoapAPIClient soapClient;

    public SoapAPIAdapter(SoapAPIClient soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    public void authenticate(String credentials) {
        // Splitting credentials for SOAP API login
        String[] creds = credentials.split(":");
        soapClient.login(creds[0], creds[1]);
    }

    @Override
    public String fetchData(String operation) {
        return soapClient.retrieveData(operation);
    }
}

// Test class using the Adapter Pattern
public class APITest {

    public void testAPI(APIAdapter adapter) {
        adapter.authenticate("testUser:testPassword"); // Unified authentication
        String data = adapter.fetchData("getDetails"); // Unified data fetch
        System.out.println("API Response: " + data);
    }

    public static void main(String[] args) {
        // Testing REST API
        APIAdapter restAdapter = new RestAPIAdapter(new RestAPIClient());
        new APITest().testAPI(restAdapter);

        // Testing SOAP API
        APIAdapter soapAdapter = new SoapAPIAdapter(new SoapAPIClient());
        new APITest().testAPI(soapAdapter);

        // Benefits:
        // - Unified interface for interacting with both APIs.
        // - Decoupled test logic: Test code is independent of API implementations.
        // - Reusability: The same test logic works for both REST and SOAP APIs.
        // - Easy to extend: Adding support for a new API requires creating a new adapter.
    }
}
