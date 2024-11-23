import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Strategy interface defining the login behavior
 * Each login type will implement this interface
 */
interface LoginStrategy {
    void login(WebDriver driver);
}

/**
 * Concrete strategy for Google login
 * Encapsulates all Google-specific login logic
 */
class GoogleLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Google");
        driver.findElement(By.id("googleLoginButton")).click();
    }
}

/**
 * Concrete strategy for Facebook login
 * Encapsulates all Facebook-specific login logic
 */
class FacebookLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Facebook");
        driver.findElement(By.id("facebookLoginButton")).click();
    }
}

/**
 * Concrete strategy for Email login
 * Encapsulates all Email-specific login logic
 */
class EmailLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Email");
        driver.findElement(By.id("username")).sendKeys("testUser");
        driver.findElement(By.id("password")).sendKeys("testPassword");
        driver.findElement(By.id("emailLoginButton")).click();
    }
}

/**
 * Context class that uses the login strategies
 * Delegates the login behavior to the selected strategy
 */
public class LoginTest {
    WebDriver driver = new ChromeDriver();
    private LoginStrategy loginStrategy;

    // Set the login strategy at runtime
    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    // Execute the selected login strategy
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

        // Example usage of different strategies
        loginTest.setLoginStrategy(new GoogleLoginStrategy());
        loginTest.performLogin();

        loginTest.setLoginStrategy(new FacebookLoginStrategy());
        loginTest.performLogin();

        loginTest.setLoginStrategy(new EmailLoginStrategy());
        loginTest.performLogin();

        loginTest.driver.quit();
    }
}

/* Benefits of Strategy Pattern:
 * 1. Each login type is isolated in its own class
 * 2. Easy to add new login methods
 * 3. Login behavior can be changed at runtime
 * 4. Follows Open-Closed Principle
 * 5. Improved code organization
 * 6. Better testability
 * 7. Reusable login strategies
 */
