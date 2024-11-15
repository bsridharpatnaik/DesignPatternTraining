import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {

    public void testPageNavigation() {
        System.out.println("Starting test: testPageNavigation");

        long startTime = System.currentTimeMillis();

        // Direct WebDriver instantiation and usage
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com");

        // Duplicated logging logic
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        driver.quit();

        System.out.println("Test completed: testPageNavigation");
    }

    public void testAnotherPage() {
        System.out.println("Starting test: testAnotherPage");

        long startTime = System.currentTimeMillis();

        // Repeated WebDriver setup
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com/another");

        // Repeated logging logic
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        driver.quit();

        System.out.println("Test completed: testAnotherPage");
    }

    // Issues Summary:
    // - Logging and timing logic is repeated in every test method.
    // - No way to reuse or extend logging and timing behavior without modifying the test methods.
    // - Tests are tightly coupled with these additional behaviors, reducing flexibility and maintainability.
}
