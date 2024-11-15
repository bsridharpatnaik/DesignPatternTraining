import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {

    // Problem: Each test creates a new WebDriver instance, consuming more memory and resources
    public void testLoginInChrome() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com");
        System.out.println("Running login test in Chrome");

        // Test steps for login...

        driver.quit();
    }

    public void testLoginInFirefox() {
        WebDriver driver = new FirefoxDriver();  // New WebDriver instance
        driver.get("http://example.com");
        System.out.println("Running login test in Firefox");

        // Test steps for login...

        driver.quit();
    }

    // Issues Summary:
    // - A new WebDriver instance is created for each test method, leading to resource wastage.
    // - Test execution time increases as WebDriver setup and teardown are repeated for every test.
    // - Harder to manage and control WebDriver lifecycle across tests.
}
