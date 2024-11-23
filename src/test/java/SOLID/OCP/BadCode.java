/**
 * Browser factory that violates Open/Closed Principle
 * Must modify this class to add new browsers
 */
public class BrowserFactory {
    public WebDriver getDriver(String browserType) {
        // Problem 1: Conditional logic that needs modification for each new browser
        if (browserType.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        }
        else if (browserType.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        }
        // Problem 2: Adding safari requires modifying existing code
        else if (browserType.equalsIgnoreCase("safari")) {
            System.out.println("Safari is not yet supported.");
            return null;
        }
        // Problem 3: Adding edge requires modifying existing code
        else if (browserType.equalsIgnoreCase("edge")) {
            System.out.println("Edge is not yet supported.");
            return null;
        }
        else {
            throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }

    /* Problems:
     * 1. Must modify class for each new browser
     * 2. Risk of breaking existing code
     * 3. Long if-else chain
     * 4. Not scalable
     * 5. Hard to maintain
     * 6. Violates Single Responsibility
     * 7. Testing becomes complicated
     */
}
