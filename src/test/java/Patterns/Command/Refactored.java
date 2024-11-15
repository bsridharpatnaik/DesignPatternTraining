
/* In this refactored version, each test step (e.g., login, add to cart, logout) 
is encapsulated as a command. The test class dynamically
executes commands, improving reusability and flexibility.*/


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Command interface
interface TestCommand {
    void execute();
}

// Concrete command for login
class LoginCommand implements TestCommand {
    private WebDriver driver;
    private String username;
    private String password;

    public LoginCommand(WebDriver driver, String username, String password) {
        this.driver = driver;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {
        System.out.println("Executing LoginCommand...");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
    }
}

// Concrete command for adding a product to the cart
class AddToCartCommand implements TestCommand {
    private WebDriver driver;

    public AddToCartCommand(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void execute() {
        System.out.println("Executing AddToCartCommand...");
        driver.findElement(By.id("addToCartButton")).click();
    }
}

// Concrete command for logout
class LogoutCommand implements TestCommand {
    private WebDriver driver;

    public LogoutCommand(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void execute() {
        System.out.println("Executing LogoutCommand...");
        driver.findElement(By.id("logoutButton")).click();
    }
}

// Invoker class to manage and execute commands
class TestExecutor {
    public void execute(TestCommand command) {
        command.execute();
    }
}

// Test class using the Command Pattern
public class EcommerceTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com");

        // Create commands
        TestCommand login = new LoginCommand(driver, "testUser", "testPassword");
        TestCommand addToCart = new AddToCartCommand(driver);
        TestCommand logout = new LogoutCommand(driver);

        // Execute commands using TestExecutor
        TestExecutor executor = new TestExecutor();
        executor.execute(login);
        executor.execute(addToCart);
        executor.execute(logout);

        driver.quit();

        // Benefits of Command Pattern:
        // - Encapsulates each test step as a reusable command, improving modularity.
        // - Allows dynamic execution and sequencing of test steps.
        // - Simplifies adding, removing, or reordering steps without modifying test methods.
    }
}
