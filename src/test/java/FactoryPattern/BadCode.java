import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {

    public void testInChrome() {
        // Issue: Hard-coded WebDriver instantiation for Chrome
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com");
        System.out.println("Running test in Chrome");

        // Test steps here...

        // Issue: Manual driver quit - needs repetition in each test
        driver.quit();
    }

    public void testInFirefox() {
        // Issue: Hard-coded WebDriver instantiation for Firefox
        WebDriver driver = new FirefoxDriver();
        driver.get("http://example.com");
        System.out.println("Running test in Firefox");

        // Test steps here...

        driver.quit(); // Repeated quit method for every test
    }

    // Issues Summary:
    // - Code duplication for WebDriver setup in each test method.
    // - Adding a new browser requires modifying each test method.
    // - Hard-coded WebDriver instances lead to high maintenance costs.
    // - Lack of flexibility and scalability.
}
