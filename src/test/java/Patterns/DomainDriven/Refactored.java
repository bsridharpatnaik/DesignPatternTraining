// Step 1: Define Domain Objects
class User {
    private final String username;
    private final String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
}

// Step 2: Create Bounded Contexts
class UserDomain {
    public void addProductToCart(User user, String productId) {
        validateCustomerRole(user);
        System.out.println("Adding product to cart for user: " + user.getUsername());
    }

    private void validateCustomerRole(User user) {
        if (!"Customer".equals(user.getRole())) {
            throw new IllegalStateException("Only customers can add products to cart");
        }
    }
}

class OrderDomain {
    public void viewAllOrders(User admin) {
        validateAdminRole(admin);
        System.out.println("Admin viewing all orders for: " + admin.getUsername());
    }

    public void customerCheckout(User customer, String cartId) {
        validateCustomerRole(customer);
        System.out.println("Customer checking out with cart: " + cartId);
    }

    private void validateAdminRole(User user) {
        if (!"Admin".equals(user.getRole())) {
            throw new IllegalStateException("Only admins can view all orders");
        }
    }

    private void validateCustomerRole(User user) {
        if (!"Customer".equals(user.getRole())) {
            throw new IllegalStateException("Only customers can checkout");
        }
    }
}

// Step 3: Clean tests using domain objects and contexts
public class ECommerceTests {
    private UserDomain userDomain = new UserDomain();
    private OrderDomain orderDomain = new OrderDomain();

    @Test
    public void testAddProductToCart() {
        User customer = new User("testUser", "Customer");
        userDomain.addProductToCart(customer, "123");
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

/*
Key Changes:
1. Created proper domain objects (User)
2. Separated business contexts (UserDomain, OrderDomain)
3. Added role validation
4. Encapsulated business rules
5. Clear domain boundaries
6. Type safety instead of raw strings
7. Easy to extend with new rules
*/
