/**
 * User entity with required fields
 */
public class User {
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDateTime lastLogin;
    // Getters and setters omitted
}

/**
 * Builder for creating User objects with sensible defaults
 */
public class UserBuilder {
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDateTime lastLogin;

    private UserBuilder() {
        // Set default values
        this.username = "defaultUser";
        this.password = "defaultPass123";
        this.role = "USER";
        this.active = true;
        this.lastLogin = LocalDateTime.now();
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        this.email = username + "@test.com"; // Auto-generate email
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
 * Factory for common test data scenarios
 */
public class UserTestDataFactory {
    public static User standardUser() {
        return UserBuilder.aUser().build();
    }

    public static User adminUser() {
        return UserBuilder.aUser()
            .withUsername("admin")
            .withRole("ADMIN")
            .build();
    }

    public static User inactiveUser() {
        return UserBuilder.aUser()
            .withActive(false)
            .build();
    }

    public static List<User> multipleUsers(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> UserBuilder.aUser()
                .withUsername("user" + i)
                .build())
            .collect(Collectors.toList());
    }
}

/**
 * Clean tests using test data patterns
 */
public class UserTests {
    @Test
    public void testAdminLogin() {
        // Using factory for common scenario
        User admin = UserTestDataFactory.adminUser();
        // Test admin login...
    }

    @Test
    public void testCustomScenario() {
        // Using builder for specific needs
        User custom = UserBuilder.aUser()
            .withUsername("custom")
            .withRole("MANAGER")
            .build();
        // Test with custom user...
    }

    @Test
    public void testMultipleUsers() {
        // Using factory for bulk creation
        List<User> users = UserTestDataFactory.multipleUsers(3);
        // Test with multiple users...
    }
}

/* Benefits:
 * 1. Centralized test data creation
 * 2. Consistent default values
 * 3. Fluent builder interface
 * 4. Reusable test data scenarios
 * 5. Easy maintenance
 * 6. Clean, readable tests
 * 7. Flexible test data creation
 */
