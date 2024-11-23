import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    WebDriver driver = new ChromeDriver();

    public void testLogin(String loginType) {
        driver.get("http://example.com/login");

        // Problem: Complex conditional logic for different login types
        if (loginType.equalsIgnoreCase("google")) {
            System.out.println("Logging in with Google");
            driver.findElement(By.id("googleLoginButton")).click();
        }
        else if (loginType.equalsIgnoreCase("facebook")) {
            System.out.println("Logging in with Facebook");
            driver.findElement(By.id("facebookLoginButton")).click();
        }
        else if (loginType.equalsIgnoreCase("email")) {
            System.out.println("Logging in with Email");
            driver.findElement(By.id("username")).sendKeys("testUser");
            driver.findElement(By.id("password")).sendKeys("testPassword");
            driver.findElement(By.id("emailLoginButton")).click();
        }
        else {
            System.out.println("Invalid login type specified");
        }

        /* Problems:
         * 1. Large if-else blocks for different login types
         * 2. Need to modify this method for each new login type
         * 3. Login logic not reusable across tests
         * 4. Hard to maintain as number of login types grows
         * 5. No separation of concerns
         * 6. Violates Open-Closed Principle
         */
    }
}
