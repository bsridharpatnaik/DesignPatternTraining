// Domain model for Users
public class User {
    private String username;
    private String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}

// Domain-specific repository for user actions
public class UserDomain {
    public void addProductToCart(User user, String productId) {
        System.out.println("Adding product to cart for user: " + user.getUsername());
        // Logic for adding product to cart
    }
}

// Separate context for Orders
public class OrderDomain {
    public void viewAllOrders(User adminUser) {
        if (adminUser.getRole().equals("Admin")) {
            System.out.println("Admin viewing all orders for: " + adminUser.getUsername());
            // Logic for admin to view orders
        }
    }

    public void customerCheckout(User customer, String cartId) {
        if (customer.getRole().equals("Customer")) {
            System.out.println("Customer checking out with cart: " + cartId);
            // Logic for checkout
        }
    }
}

// Test cases with DDD principles
public class ECommerceTests {

    private UserDomain userDomain = new UserDomain();
    private OrderDomain orderDomain = new OrderDomain();

    @Test
    public void testAddProductToCart() {
        User user = new User("testUser", "Customer");
        userDomain.addProductToCart(user, "123");
    }

    @Test
    public void testAdminViewAllOrders() {
        User admin = new User("adminUser", "Admin");
        orderDomain.viewAllOrders(admin);
    }

    @Test
    public void testCustomerCheckout() {
        User customer = new User("customer123", "Customer");
        orderDomain.customerCheckout(customer, "cart567");
    }
}
