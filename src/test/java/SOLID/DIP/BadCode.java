/**
 * AutomatedTest class with tight coupling to concrete implementations
 * Violates Dependency Inversion Principle
 */
 public class AutomatedTest {
     private ChromeDriver driver;            // Directly using ChromeDriver
     private MySQLTestData testData;         // Directly using MySQL
     private ExtentReporter reporter;        // Directly using Extent Reports

     public AutomatedTest() {
         // Hard-coded dependencies
         this.driver = new ChromeDriver();
         this.testData = new MySQLTestData();
         this.reporter = new ExtentReporter();
     }

     public void runLoginTest() {
         // Test directly depends on specific implementations
         testData.loadTestData("login_data");
         driver.get("https://example.com");
         reporter.logStep("Navigated to website");
         // More test steps...
     }
    /* Problems:
     * 1. High-level module (AutomatedTest) depends on low-level modules
     * 2. Hard to switch implementations (e.g., Firefox, PostgreSQL)
     * 3. Difficult to mock for unit testing
     * 4. Tight coupling makes code rigid and hard to change
     * 5. Cannot reuse code with different implementations
     * 6. Hard to test in isolation
     * 7. Violates "Program to an interface, not implementation"
     */
}
