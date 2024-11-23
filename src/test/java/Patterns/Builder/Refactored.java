public class User {
    // Step 1: Make fields private final
    private final String username;
    private final String password;
    private final String email;
    private final String phone;
    private final String address;

    // Step 2: Private constructor, only Builder can create User
    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // Step 3: Static Builder class
    public static class UserBuilder {
        // Required parameters in fields
        private final String username;
        private final String password;

        // Optional parameters - initialized to default values
        private String email = "";
        private String phone = "";
        private String address = "";

        // Step 4: Builder constructor with required parameters
        public UserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // Step 5: Methods for optional parameters
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

    public static void main(String[] args) {
        // Clean, readable object construction
        User user = new User.UserBuilder("john", "pass123")
            .withEmail("john@email.com")
            .withPhone("1234567890")
            .build();
    }
}

/*
Key Changes:
1. Separated object construction into Builder class
2. Required vs optional parameters clearly defined
3. Fluent interface with method chaining
4. No more null parameters
5. Can't create invalid objects
6. Easy to add new optional fields
7. Self-documenting code
*/
