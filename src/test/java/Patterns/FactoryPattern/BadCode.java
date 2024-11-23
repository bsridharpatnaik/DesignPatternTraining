import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {
    public void testInChrome() {
        // Problem 1: Direct instantiation of WebDriver
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com");
        System.out.println("Running test in Chrome");

        // Test steps...
        driver.quit();
    }

    public void testInFirefox() {
        // Problem 2: Duplicate code for different browser
        WebDriver driver = new FirefoxDriver();
        driver.get("http://example.com");
        System.out.println("Running test in Firefox");

        // Test steps...
        driver.quit();
    }

    /* Major Issues:
     * 1. Hard-coded WebDriver creation
     * 2. Duplicate browser setup code
     * 3. Adding new browser requires changing all test methods
     * 4. No central place to manage driver configuration
     * 5. Inconsistent driver initialization
     * 6. Hard to switch browsers dynamically
     * 7. Hard to maintain driver setup logic
     */
}
