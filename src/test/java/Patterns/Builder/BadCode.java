public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    // Problem 1: Constructor with too many parameters
    public User(String username, String password, String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters omitted for brevity

    public static void main(String[] args) {
        // Problem 2: Hard to read constructor calls
        User user1 = new User("john", "pass123", "john@email.com", null, null);

        // Problem 3: Easy to mix up parameter order
        User user2 = new User("jane", null, null, "1234567890", "123 Street");

        /* Major Issues:
         * 1. Telescoping constructor with many parameters
         * 2. Unclear which parameters are optional
         * 3. Must pass null for unused parameters
         * 4. Parameter order is error-prone
         * 5. Hard to read and maintain
         * 6. Can't build object step by step
         * 7. No validation during construction
         */
    }
}
