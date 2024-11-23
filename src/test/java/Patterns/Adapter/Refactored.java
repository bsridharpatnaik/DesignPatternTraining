// Step 1: Create common interface for all APIs
interface APIAdapter {
    void authenticate(String credentials);
    String fetchData(String query);
}

// Step 2: Adapt REST API to common interface
class RestAPIAdapter implements APIAdapter {
    private RestAPIClient restClient;

    public RestAPIAdapter(RestAPIClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void authenticate(String credentials) {
        // Adapt authentication method
        restClient.authenticate(credentials);
    }

    @Override
    public String fetchData(String query) {
        // Adapt data fetching method
        return restClient.fetchData(query);
    }
}

// Step 3: Adapt SOAP API to same interface
class SoapAPIAdapter implements APIAdapter {
    private SoapAPIClient soapClient;

    public SoapAPIAdapter(SoapAPIClient soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    public void authenticate(String credentials) {
        // Split credentials and adapt to login method
        String[] parts = credentials.split(":");
        soapClient.login(parts[0], parts[1]);
    }

    @Override
    public String fetchData(String query) {
        // Adapt to retrieveData method
        return soapClient.retrieveData(query);
    }
}

// Step 4: Clean test code using unified interface
public class APITest {
    public void testAPI(APIAdapter api) {
        // Single test method works for all API types
        api.authenticate("credentials");
        api.fetchData("query");
    }
}

/*
Key Changes:
1. Created common interface (APIAdapter)
2. Each adapter converts specific API to common interface
3. Test code works with any API type
4. Easy to add new APIs by creating new adapters
5. Single test method instead of multiple
*/
