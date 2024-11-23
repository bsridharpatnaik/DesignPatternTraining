import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {
    // Problem: Each test creates a new WebDriver instance, consuming more memory and resources
    public void testLoginInChrome() {
        WebDriver driver = new ChromeDriver();  // New instance created
        driver.get("http://example.com");
        System.out.println("Running login test in Chrome");

        // Test steps for login...
        driver.quit();
    }

    public void testLoginInFirefox() {
        WebDriver driver = new FirefoxDriver();  // Another new instance
        driver.get("http://example.com");
        System.out.println("Running login test in Firefox");

        // Test steps for login...
        driver.quit();
    }

    /* Issues Summary:
     * 1. New WebDriver instance for each test method
     * 2. Resource wastage with multiple browser instances
     * 3. Increased test execution time due to repeated setup/teardown
     * 4. No control over WebDriver lifecycle
     * 5. Memory inefficient
     * 6. Inconsistent browser state management
     */
}
