

// In this refactored code, the Strategy Pattern is used to encapsulate different login
// strategies in separate classes, making the login logic modular and maintainable.


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Interface representing the login strategy
interface LoginStrategy {
    void login(WebDriver driver);
}

// Strategy for Google login
class GoogleLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Google");
        // Logic for Google login
        driver.findElement(By.id("googleLoginButton")).click();
    }
}

// Strategy for Facebook login
class FacebookLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Facebook");
        // Logic for Facebook login
        driver.findElement(By.id("facebookLoginButton")).click();
    }
}

// Strategy for Email login
class EmailLoginStrategy implements LoginStrategy {
    @Override
    public void login(WebDriver driver) {
        System.out.println("Logging in with Email");
        // Logic for Email login
        driver.findElement(By.id("username")).sendKeys("testUser");
        driver.findElement(By.id("password")).sendKeys("testPassword");
        driver.findElement(By.id("emailLoginButton")).click();
    }
}

// Context class that uses the chosen login strategy
public class LoginTest {

    WebDriver driver = new ChromeDriver();
    private LoginStrategy loginStrategy;

    // Method to set the login strategy at runtime
    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public void performLogin() {
        driver.get("http://example.com/login");

        if (loginStrategy != null) {
            // Using the chosen login strategy
            loginStrategy.login(driver);
        } else {
            System.out.println("No login strategy selected");
        }
    }

    public static void main(String[] args) {
        LoginTest loginTest = new LoginTest();

        // Example 1: Using Google login strategy
        loginTest.setLoginStrategy(new GoogleLoginStrategy());
        loginTest.performLogin();

        // Example 2: Using Facebook login strategy
        loginTest.setLoginStrategy(new FacebookLoginStrategy());
        loginTest.performLogin();

        // Example 3: Using Email login strategy
        loginTest.setLoginStrategy(new EmailLoginStrategy());
        loginTest.performLogin();

        loginTest.driver.quit();
    }

    // Benefits of Strategy Pattern:
    // - Each login method is isolated in its own class, improving modularity and reusability.
    // - Adding a new login method requires creating a new strategy class without modifying the test class.
    // - The login behavior can be chosen dynamically at runtime, making tests flexible.
}
