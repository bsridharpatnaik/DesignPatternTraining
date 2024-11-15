import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    public WebDriver getDriver(String browserType) {
        if (browserType.equalsIgnoreCase("chrome")) {
            return new ChromeDriver(); // Chrome setup
        } else if (browserType.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver(); // Firefox setup
        } else if (browserType.equalsIgnoreCase("safari")) {
            // Problem: Adding Safari requires modifying the method
            System.out.println("Safari is not yet supported.");
            return null;
        } else if (browserType.equalsIgnoreCase("edge")) {
            // Problem: Adding Edge requires modifying the method
            System.out.println("Edge is not yet supported.");
            return null;
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }

    // Problems:
    // - The method needs modification whenever a new browser is added.
    // - Risk of breaking existing functionality when making changes.
    // - Violates the Open/Closed Principle.
}
