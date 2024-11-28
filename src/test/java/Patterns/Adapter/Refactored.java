/**
 * Problem: Different APIs have different interfaces
 * Solution: Create a common interface and adapt each API to it
 */

/**
 * Common interface that all APIs will adapt to
 * Defines standard methods for authentication and data fetching
 */
interface APIAdapter {
    void authenticate(String credentials);  // Common auth method
    String fetchData(String query);        // Common data fetch method
}

/**
 * Original REST API client we need to adapt
 * Has its own specific interface
 */
class RestAPIClient {
    void authenticate(String token) {
        System.out.println("REST Auth with token: " + token);
    }

    String fetchData(String endpoint) {
        return "REST Data from: " + endpoint;
    }
}

/**
 * Original SOAP API client we need to adapt
 * Has different method names and signatures
 */
class SoapAPIClient {
    void login(String username, String password) {
        System.out.println("SOAP Login: " + username);
    }

    String retrieveData(String operation) {
        return "SOAP Data from: " + operation;
    }
}

/**
 * Adapter for REST API
 * Implements common interface while using REST client
 */
class RestAPIAdapter implements APIAdapter {
    private RestAPIClient restClient;

    public RestAPIAdapter(RestAPIClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void authenticate(String credentials) {
        // Simply pass through as REST already uses tokens
        restClient.authenticate(credentials);
    }

    @Override
    public String fetchData(String query) {
        // Direct mapping as REST interface is similar
        return restClient.fetchData(query);
    }
}

/**
 * Adapter for SOAP API
 * Shows more complex adaptation when interfaces differ significantly
 */
class SoapAPIAdapter implements APIAdapter {
    private SoapAPIClient soapClient;

    public SoapAPIAdapter(SoapAPIClient soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    public void authenticate(String credentials) {
        // Transform single credential string into username/password
        String[] parts = credentials.split(":");
        soapClient.login(parts[0], parts[1]);
    }

    @Override
    public String fetchData(String query) {
        // Adapt query format for SOAP
        return soapClient.retrieveData(query);
    }
}

/**
 * Test class showing how adapters enable uniform API usage
 */
public class APITest {
    public void testAPI(APIAdapter api) {
        // Same code works with any API implementation
        api.authenticate("test:password");
        String data = api.fetchData("getUserData");
        System.out.println(data);
    }

    public static void main(String[] args) {
        APITest test = new APITest();

        // Using REST API
        RestAPIAdapter restAdapter = new RestAPIAdapter(new RestAPIClient());
        test.testAPI(restAdapter);

        // Using SOAP API with same test code
        SoapAPIAdapter soapAdapter = new SoapAPIAdapter(new SoapAPIClient());
        test.testAPI(soapAdapter);
    }
}

/* How Adapter Pattern Helps:
 * 1. Creates uniform interface for different APIs
 * 2. Test code doesn't need to know API specifics
 * 3. Easy to add support for new API types:
 *    - Create new adapter class
 *    - Implement common interface
 *    - Handle conversions inside adapter
 *
 * Real-World Benefits:
 * 1. Can switch APIs without changing test code
 * 2. Simpler test maintenance
 * 3. Support multiple API versions
 * 4. Clean separation of concerns
 */
