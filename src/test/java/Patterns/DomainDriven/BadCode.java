public class ECommerceTests {

    @Test
    public void testAddProductToCart() {
        // Hard-coded data and no business context
        String username = "testUser";
        String productId = "123";
        System.out.println("Adding product to cart for user: " + username);
        // Logic for adding product to cart...
    }

    @Test
    public void testAdminViewAllOrders() {
        // Hard-coded admin data with no separation of contexts
        String adminUsername = "adminUser";
        System.out.println("Admin viewing all orders for: " + adminUsername);
        // Logic for viewing orders...
    }

    @Test
    public void testCustomerCheckOut() {
        // Mixed roles, scattered test data, and no domain structure
        String customerId = "customer123";
        String cartId = "cart567";
        System.out.println("Customer checking out with cart: " + cartId);
        // Logic for checkout...
    }
}
