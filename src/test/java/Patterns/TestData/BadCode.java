// Bad Code
public class UserTests {
    @Test
    public void testAdminLogin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setRole("ADMIN");
        admin.setEmail("admin@company.com");
        admin.setLastLogin(LocalDateTime.now());
        admin.setStatus("ACTIVE");

        // Test admin login
    }

    @Test
    public void testCustomerOrder() {
        User customer = new User();
        customer.setUsername("john");
        customer.setPassword("pass123");
        customer.setRole("CUSTOMER");
        customer.setEmail("john@email.com");
        customer.setLastLogin(LocalDateTime.now());
        customer.setStatus("ACTIVE");

        // Test customer order
    }
}
