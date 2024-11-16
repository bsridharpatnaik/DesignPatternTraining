// Bad Code
public class TestExecution {
    @Test
    public void testUserLogin() {
        // Multiple validations mixed in test
        if (!checkBrowser()) {
            throw new TestException("Browser not compatible");
        }

        if (!checkEnvironment()) {
            throw new TestException("Environment not ready");
        }

        if (!checkTestData()) {
            throw new TestException("Test data not valid");
        }

        if (!checkPermissions()) {
            throw new TestException("Insufficient permissions");
        }

        // Actual test code
        loginUser();
    }
}
