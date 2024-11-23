public class UserTests {
    @Test
    public void testAdminLogin() {
        // Problem 1: Manual creation of test data
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setRole("ADMIN");
        admin.setEmail("admin@company.com");
        admin.setLastLogin(LocalDateTime.now());
        admin.setStatus("ACTIVE");

        // Test admin login...
    }

    @Test
    public void testCustomerOrder() {
        // Problem 2: Duplicate test data setup
        User customer = new User();
        customer.setUsername("john");
        customer.setPassword("pass123");
        customer.setRole("CUSTOMER");
        customer.setEmail("john@email.com");
        customer.setLastLogin(LocalDateTime.now());
        customer.setStatus("ACTIVE");

        // Test customer order...
    }

    /* Problems:
     * 1. Repetitive test data creation
     * 2. Inconsistent test data across tests
     * 3. No default values for optional fields
     * 4. Hard to maintain when User class changes
     * 5. No reuse of common test data scenarios
     * 6. Brittle tests due to hardcoded values
     * 7. No clear separation of test data from test logic
     */
}
