import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    WebDriver driver = new ChromeDriver();

    public void testLogin(String loginType) {
        driver.get("http://example.com/login");

        if (loginType.equalsIgnoreCase("google")) {
            // Problem 1: Duplicated login logic for Google login
            System.out.println("Logging in with Google");
            // Logic for Google login (e.g., click "Sign in with Google" button)
            driver.findElement(By.id("googleLoginButton")).click();
        } else if (loginType.equalsIgnoreCase("facebook")) {
            // Problem 2: Duplicated login logic for Facebook login
            System.out.println("Logging in with Facebook");
            // Logic for Facebook login (e.g., click "Sign in with Facebook" button)
            driver.findElement(By.id("facebookLoginButton")).click();
        } else if (loginType.equalsIgnoreCase("email")) {
            // Problem 3: Duplicated login logic for Email login
            System.out.println("Logging in with Email");
            // Logic for Email login (e.g., enter username and password)
            driver.findElement(By.id("username")).sendKeys("testUser");
            driver.findElement(By.id("password")).sendKeys("testPassword");
            driver.findElement(By.id("emailLoginButton")).click();
        } else {
            System.out.println("Invalid login type specified");
        }

        // Problem Summary:
        // - This approach requires modifying the test method every time a new login method is added.
        // - Duplicated code for each login method makes maintenance difficult and error-prone.
        // - The login logic is not modular, making it hard to reuse or update independently.
    }
}
