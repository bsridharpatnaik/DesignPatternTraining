public class TestExecution {
    @Test
    public void runTest() {
        // Problem 1: All validations in one method
        if (!checkBrowser()) {
            throw new TestException("Browser not compatible");
        }

        // Problem 2: Fixed validation order
        if (!checkEnvironment()) {
            throw new TestException("Environment not ready");
        }

        // Problem 3: Hard to add new validations
        if (!checkTestData()) {
            throw new TestException("Test data invalid");
        }

        if (!checkPermissions()) {
            throw new TestException("Insufficient permissions");
        }

        // Finally, run the test
        runActualTest();
    }

    /* Major Issues:
     * 1. All validation logic mixed in single method
     * 2. Fixed, hardcoded validation sequence
     * 3. Can't reuse validations across tests
     * 4. Adding new validation requires modifying all test methods
     * 5. Can't change validation order dynamically
     * 6. Duplicate validation code in different test classes
     * 7. No separation of concerns
     */

    private boolean checkBrowser() { return true; }
    private boolean checkEnvironment() { return true; }
    private boolean checkTestData() { return true; }
    private boolean checkPermissions() { return true; }
}
