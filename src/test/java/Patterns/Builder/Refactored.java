public class User {
    private String name;
    private String email;
    private int age;
    private String country;

    // Simple builder class
    public static class UserBuilder {
        private User user = new User();

        public UserBuilder name(String name) {
            user.name = name;
            return this;
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

        public User create() {
            return user;
        }
    }

    public static void main(String[] args) {
        // Clear and readable user creation
        User user1 = new UserBuilder()
            .name("John")
            .email("john@email.com")
            .age(25)
            .country("USA")
            .create();

        // Only set required fields
        User user2 = new UserBuilder()
            .name("Jane")
            .country("UK")
            .create();
    }
}
