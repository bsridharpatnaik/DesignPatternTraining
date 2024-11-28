/**
 * Test Data Pattern Example
 * Shows separation of test data creation from test logic
 */

/**
 * Basic user entity with common fields
 * Core class that will be used in tests
 */
public class User {
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDateTime lastLogin;
    // Getters/setters omitted
}

/**
 * Builder for flexible User object creation
 * Provides sensible defaults and fluent interface
 */
public class UserBuilder {
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDateTime lastLogin;

    private UserBuilder() {
        // Default values for all fields
        this.username = "defaultUser";
        this.password = "defaultPass123";
        this.role = "USER";
        this.active = true;
        this.lastLogin = LocalDateTime.now();
    }

    // Factory method for builder creation
    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    // Builder methods for customization
    public UserBuilder withUsername(String username) {
        this.username = username;
        this.email = username + "@test.com";  // Convention for test emails
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

    // Creates final User object
    public User build() {
        User user = new User();
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
 * Factory providing common test data scenarios
 * Central place for test data creation
 */
public class UserTestDataFactory {
    // Common scenario: Standard user
    public static User standardUser() {
        return UserBuilder.aUser().build();
    }

    // Common scenario: Admin user
    public static User adminUser() {
        return UserBuilder.aUser()
            .withUsername("admin")
            .withRole("ADMIN")
            .build();
    }

    // Common scenario: Inactive user
    public static User inactiveUser() {
        return UserBuilder.aUser()
            .withActive(false)
            .build();
    }

    // Utility method: Create multiple users
    public static List<User> multipleUsers(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> UserBuilder.aUser()
                .withUsername("user" + i)
                .build())
            .collect(Collectors.toList());
    }
}

/**
 * Example test class showing different ways to create test data
 */
public class UserTests {
    @Test
    public void testAdminLogin() {
        // Using factory for standard scenario
        User admin = UserTestDataFactory.adminUser();
        verifyAdminAccess(admin);
    }

    @Test
    public void testCustomUser() {
        // Using builder for specific requirements
        User manager = UserBuilder.aUser()
            .withUsername("manager")
            .withRole("MANAGER")
            .build();
        verifyManagerAccess(manager);
    }

    @Test
    public void testMultipleUsers() {
        // Using factory for bulk data
        List<User> users = UserTestDataFactory.multipleUsers(3);
        verifyBulkOperations(users);
    }
}

/* Pattern Benefits:
 * 1. Test Data Creation:
 *    - Centralized data creation
 *    - Consistent test data
 *    - Reusable scenarios
 *
 * 2. Test Clarity:
 *    - Clear test intent
 *    - Readable test setup
 *    - Maintainable test data
 *
 * 3. Test Flexibility:
 *    - Easy to create variations
 *    - Support for edge cases
 *    - Bulk data creation
 */
