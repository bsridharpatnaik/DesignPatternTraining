/**
 * Strategy Pattern Implementation for Login Automation
 * Handles different login methods (Google, Facebook, Email) flexibly
 */

/**
 * Common interface for all login strategies
 * Each login type will implement this
 */
interface LoginStrategy {
    void login(WebDriver driver);  // Single method all strategies must implement
}

/**
 * Google Login Implementation
 * Handles Google-specific login flow
 */
class GoogleLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Google");
        // Google-specific login steps
        driver.findElement(By.id("googleLoginButton")).click();
    }
}

/**
 * Facebook Login Implementation
 * Handles Facebook-specific login flow
 */
class FacebookLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Facebook");
        // Facebook-specific login steps
        driver.findElement(By.id("facebookLoginButton")).click();
    }
}

/**
 * Email Login Implementation
 * Handles traditional username/password login
 */
class EmailLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Email");
        // Traditional login steps
        driver.findElement(By.id("username")).sendKeys("testUser");
        driver.findElement(By.id("password")).sendKeys("testPassword");
        driver.findElement(By.id("emailLoginButton")).click();
    }
}

/**
 * Main test class that uses login strategies
 * Can switch between different login methods at runtime
 */
public class LoginTest {
    WebDriver driver;
    private LoginStrategy loginStrategy;  // Current login strategy

    public LoginTest() {
        driver = new ChromeDriver();
    }

    /**
     * Sets which login strategy to use
     * Can be changed at any time during test execution
     */
    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    /**
     * Executes the login process using selected strategy
     * Strategy can be switched between calls
     */
    public void performLogin() {
        driver.get("http://example.com/login");

        if (loginStrategy != null) {
            loginStrategy.login(driver);
        } else {
            System.out.println("No login strategy selected");
        }
    }

    public static void main(String[] args) {
        LoginTest loginTest = new LoginTest();

        // Example 1: Google Login
        loginTest.setLoginStrategy(new GoogleLoginStrategy());
        loginTest.performLogin();

        // Example 2: Facebook Login
        loginTest.setLoginStrategy(new FacebookLoginStrategy());
        loginTest.performLogin();

        // Example 3: Email Login
        loginTest.setLoginStrategy(new EmailLoginStrategy());
        loginTest.performLogin();

        loginTest.driver.quit();
    }
}

/* How Strategy Pattern Helps:
 * 1. Login Methods:
 *    - Each login type in separate class
 *    - Easy to maintain different login flows
 *    - Clear where to add new login methods
 *
 * 2. Test Flexibility:
 *    - Switch login methods at runtime
 *    - Same test works with any login type
 *    - Easy to add new login strategies
 *
 * Common Extensions:
 * - Add login validation
 * - Handle different credentials
 * - Add error handling
 */
