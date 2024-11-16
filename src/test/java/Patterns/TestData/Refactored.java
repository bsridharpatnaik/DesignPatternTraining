/**
 * User entity representing system users
 */
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDateTime lastLogin;
    // Standard getters/setters omitted for brevity
}

/**
 * Builder for creating User objects with a fluent interface
 * Benefits:
 * - Default values for all fields
 * - Method chaining for easy customization
 * - Encapsulates user creation logic
 */
public class UserBuilder {
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDateTime lastLogin;

    // Private constructor to force using static factory method
    private UserBuilder() {
        // Set default values
        this.id = UUID.randomUUID().toString();
        this.username = "defaultUser";
        this.email = "default@test.com";
        this.password = "defaultPass123";
        this.role = "USER";
        this.active = true;
        this.lastLogin = LocalDateTime.now();
    }

    // Static factory method to start building
    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    // Builder methods - each returns 'this' for method chaining
    public UserBuilder withUsername(String username) {
        this.username = username;
        this.email = username + "@test.com"; // Auto-generate matching email
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    // Build method creates and returns the final User object
    public User build() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setActive(active);
        user.setLastLogin(lastLogin);
        return user;
    }
}

/**
 * Factory for common User test data scenarios
 * Benefits:
 * - Centralized place for common test data
 * - Meaningful names for different scenarios
 * - Reusable across test classes
 */
public class UserTestDataFactory {

    /**
     * Creates a standard active user with default settings
     */
    public static User standardUser() {
        return UserBuilder.aUser().build();
    }

    /**
     * Creates an admin user with elevated privileges
     */
    public static User adminUser() {
        return UserBuilder.aUser()
            .withUsername("admin")
            .withRole("ADMIN")
            .build();
    }

    /**
     * Creates an inactive user for testing disability scenarios
     */
    public static User inactiveUser() {
        return UserBuilder.aUser()
            .withActive(false)
            .build();
    }

    /**
     * Creates multiple users with sequential usernames
     */
    public static List<User> multipleUsers(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> UserBuilder.aUser()
                .withUsername("user" + i)
                .build())
            .collect(Collectors.toList());
    }
}

/**
 * Example test class showing different ways to use the test data patterns
 */
public class UserTests {

    @Test
    public void testStandardUserLogin() {
        // Using factory for standard case
        User standardUser = UserTestDataFactory.standardUser();
        // Test login with standard user
    }

    @Test
    public void testAdminAccess() {
        // Using factory for admin case
        User adminUser = UserTestDataFactory.adminUser();
        // Test admin access
    }

    @Test
    public void testCustomUserScenario() {
        // Using builder directly for custom case
        User customUser = UserBuilder.aUser()
            .withUsername("customUser")
            .withEmail("custom@special.com")
            .withRole("MANAGER")
            .build();
        // Test with custom user
    }

    @Test
    public void testMultipleUsers() {
        // Using factory to create multiple users
        List<User> users = UserTestDataFactory.multipleUsers(3);
        // Test with multiple users
    }

    @Test
    public void testUserDeactivation() {
        // Using factory for inactive user
        User inactiveUser = UserTestDataFactory.inactiveUser();
        // Test user deactivation
    }
}

/**
 * Example of using the test data in test steps
 */
public class UserTestSteps {

    public void givenIHaveAnAdminUser() {
        User admin = UserTestDataFactory.adminUser();
        // Use admin user in test
    }

    public void whenICreateMultipleUsers() {
        List<User> users = UserTestDataFactory.multipleUsers(5);
        // Use multiple users in test
    }

    public void thenICanCreateCustomUser() {
        User custom = UserBuilder.aUser()
            .withUsername("custom")
            .withRole("CUSTOM_ROLE")
            .withActive(true)
            .build();
        // Use custom user in test
    }
}
