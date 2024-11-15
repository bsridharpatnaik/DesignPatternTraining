

/*  In this refactored example, the Builder Pattern is used to construct User objects,
improving readability and maintainability by making it clear
which parameters are set and which are optional.   */

// User class with Builder
public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    // Private constructor
    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    // Static nested Builder class
    public static class UserBuilder {
        private String username;
        private String password;
        private String email;
        private String phone;
        private String address;

        public UserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

// Test class using Builder Pattern
public class UserTest {

    public void createUserTest() {
        // Building a user with minimal required fields
        User user = new User.UserBuilder("testUser", "securePassword123")
                .withEmail("user@example.com") // Optional field
                .build();

        // Test logic
        System.out.println("User created: " + user.getUsername());
    }

    public void createUserWithPhoneTest() {
        // Building a user with additional optional fields
        User user = new User.UserBuilder("testUser2", "anotherPassword")
                .withPhone("1234567890") // Optional field
                .build();

        // Test logic
        System.out.println("User created with phone: " + user.getPhone());
    }

    public static void main(String[] args) {
        UserTest test = new UserTest();
        test.createUserTest();
        test.createUserWithPhoneTest();

        // Benefits of Builder Pattern:
        // - Improves readability by clearly indicating which fields are set.
        // - Avoids null values for optional parameters.
        // - Makes it easy to add new optional fields without breaking existing code.
    }
}
