public class WebTest {
    private WebDriver driver;

    @Test
    public void testUserLogin() {
        // Problem 1: Direct WebDriver usage
        driver = new ChromeDriver();

        // Problem 2: No way to track or log actions
        driver.get("http://example.com/login");
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("loginButton")).click();

        /* Major Issues:
         * 1. No action logging or tracking
         * 2. No way to handle errors consistently
         * 3. Can't add behavior without changing code
         * 4. Hard to debug test failures
         * 5. No protection for sensitive data
         * 6. Tight coupling with WebDriver
         * 7. No centralized control
         */
    }
}
