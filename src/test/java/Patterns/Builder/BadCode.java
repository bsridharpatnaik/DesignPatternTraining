public class User {
    private String name;
    private String email;
    private int age;
    private String country;

    // Problem: Multiple constructors for different combinations
    public User(String name) {
        this(name, null, 0, null);
    }

    public User(String name, String email) {
        this(name, email, 0, null);
    }

    public User(String name, String email, int age) {
        this(name, email, age, null);
    }

    // Problem: Constructor with too many parameters
    public User(String name, String email, int age, String country) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
    }
}

// Usage - unclear which parameter is which
User user1 = new User("John", "john@email.com", 25, "USA");
User user2 = new User("Jane", null, 0, "UK");
