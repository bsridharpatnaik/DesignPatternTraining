/**
 * User class with Builder Pattern implementation
 * Shows how to create objects step by step
 */
public class User {
    // Private fields - can't be modified directly
    private String name;
    private String email;
    private int age;
    private String country;

    /**
     * Static Builder class
     * Provides flexible, readable way to construct User objects
     */
    public static class UserBuilder {
        // Instance being built
        private User user = new User();

        // Builder methods - each returns 'this' for method chaining
        public UserBuilder name(String name) {
            user.name = name;
            return this;  // Returns builder for chaining
        }

        public UserBuilder email(String email) {
            user.email = email;
            return this;
        }

        public UserBuilder age(int age) {
            user.age = age;
            return this;
        }

        public UserBuilder country(String country) {
            user.country = country;
            return this;
        }

        // Final method to get the built User object
        public User create() {
            return user;
        }
    }

    public static void main(String[] args) {
        // Example 1: Building user with all fields
        User user1 = new UserBuilder()
            .name("John")          // Start with name
            .email("john@email.com")  // Add email
            .age(25)              // Add age
            .country("USA")       // Add country
            .create();           // Create final object

        // Example 2: Building user with only required fields
        User user2 = new UserBuilder()
            .name("Jane")         // Required field
            .country("UK")        // Another field
            .create();           // Skip optional fields

        /* Benefits shown here:
         * 1. Clear what each setter does
         * 2. Can set fields in any order
         * 3. Optional fields can be skipped
         * 4. Method chaining makes code readable
         * 5. Object creation is controlled and clear
         */
    }
}

/* Why Builder Pattern is Useful in Testing:
 * 1. Test Data Creation:
 *    - Easy to create test users with different combinations
 *    - Clear which fields are being set
 *    - Reduces test setup code
 *
 * 2. Test Readability:
 *    - Builder methods clearly show intent
 *    - Chain of method calls reads like sentences
 *    - Easy to see what test data contains
 *
 * 3. Test Maintenance:
 *    - Easy to add new fields
 *    - Can modify validation in one place
 *    - Clear separation of object creation from testing
 */
