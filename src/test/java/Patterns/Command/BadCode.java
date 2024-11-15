import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EcommerceTest {

    WebDriver driver = new ChromeDriver();

    public void testAddToCart() {
        driver.get("http://example.com");

        // Step 1: Login
        System.out.println("Logging in as user...");
        driver.findElement(By.id("username")).sendKeys("testUser");
        driver.findElement(By.id("password")).sendKeys("testPassword");
        driver.findElement(By.id("loginButton")).click();

        // Step 2: Add product to cart
        System.out.println("Adding product to cart...");
        driver.findElement(By.id("addToCartButton")).click();

        // Step 3: Logout
        System.out.println("Logging out...");
        driver.findElement(By.id("logoutButton")).click();

        driver.quit();
    }

    // Issues Summary:
    // - Steps are hard-coded into the test method, making it difficult to reuse them in other workflows.
    // - Adding or removing steps requires modifying the test method directly.
    // - No way to dynamically execute steps or queue them for different test cases.
}
