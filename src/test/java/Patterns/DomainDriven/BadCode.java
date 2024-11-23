public class ECommerceTests {
    @Test
    public void testAddProductToCart() {
        // Problem 1: Raw strings instead of domain objects
        String username = "testUser";
        String productId = "123";
        System.out.println("Adding product to cart for user: " + username);
    }

    @Test
    public void testAdminViewAllOrders() {
        // Problem 2: No role validation or business rules
        String adminUsername = "adminUser";
        System.out.println("Admin viewing all orders for: " + adminUsername);
    }

    @Test
    public void testCustomerCheckOut() {
        // Problem 3: No domain boundaries or contexts
        String customerId = "customer123";
        String cartId = "cart567";
        System.out.println("Customer checking out with cart: " + cartId);
    }

    /* Major Issues:
     * 1. No domain objects or business rules
     * 2. Mixed responsibilities and contexts
     * 3. No validation of roles or permissions
     * 4. Hard-coded strings instead of proper types
     * 5. Business logic scattered across tests
     * 6. No separation of concerns
     * 7. Hard to maintain and extend
     */
}
