public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    // Constructor with all parameters
    public User(String username, String password, String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}

public class UserTest {

    public void createUserTest() {
        // Problem 1: Constructor with too many parameters, making it hard to read
        User user = new User("testUser", "securePassword123", "user@example.com", null, null);

        // Test logic
        System.out.println("User created: " + user.getUsername());
    }

    public void createUserWithPhoneTest() {
        // Problem 2: Passing null for unused parameters is error-prone and reduces readability
        User user = new User("testUser2", "anotherPassword", null, "1234567890", null);

        // Test logic
        System.out.println("User created with phone: " + user.getPhone());
    }

    // Issues Summary:
    // - Hard to understand which parameters are being set and which are optional.
    // - Passing null or default values is error-prone and makes the code less readable.
    // - Adding new optional fields requires modifying the constructor and all calling code.
}
