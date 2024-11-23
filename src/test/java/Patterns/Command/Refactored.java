// Step 1: Create command interface
interface TestCommand {
    void execute();
    void undo();
}

// Step 2: Create concrete commands
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
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
    }

    @Override
    public void undo() {
        driver.findElement(By.id("logoutButton")).click();
    }
}

// Step 3: Create command executor
class TestExecutor {
    private List<TestCommand> commands = new ArrayList<>();

    public void addCommand(TestCommand command) {
        commands.add(command);
    }

    public void executeAll() {
        commands.forEach(TestCommand::execute);
    }
}

// Step 4: Clean test using commands
public class EcommerceTest {
    public void testCheckout() {
        WebDriver driver = new ChromeDriver();
        TestExecutor executor = new TestExecutor();

        // Add commands in desired sequence
        executor.addCommand(new LoginCommand(driver, "user", "pass"));
        executor.addCommand(new AddToCartCommand(driver, "product1"));
        executor.addCommand(new CheckoutCommand(driver));

        // Execute all commands
        executor.executeAll();
    }
}

/*
Key Changes:
1. Each action is a separate command
2. Commands can be reused across tests
3. Easy to change command sequence
4. Support for undo operations
5. Can batch commands
6. Easy to add new commands
7. Clean, maintainable tests
*/
