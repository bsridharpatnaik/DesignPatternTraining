public class LoginTests {
    @Test
    public void testLoginWithValidCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com/login");

        // Problem 1: Locators scattered in test methods
        driver.findElement(By.id("username")).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPassword");
        driver.findElement(By.id("loginButton")).click();

        // Problem 2: Direct element interactions in test
        Assert.assertTrue(driver.findElement(By.id("welcomeMessage")).isDisplayed());
        driver.quit();
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com/login");

        // Problem 3: Duplicate locator code
        driver.findElement(By.id("username")).sendKeys("invalidUser");
        driver.findElement(By.id("password")).sendKeys("wrongPassword");
        driver.findElement(By.id("loginButton")).click();

        Assert.assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed());
        driver.quit();
    }

    /* Major Issues:
     * 1. Duplicate locators across tests
     * 2. No separation between test logic and page interactions
     * 3. Hard to maintain when UI changes
     * 4. No reuse of common operations
     * 5. Brittle tests tied to page structure
     * 6. Inconsistent element handling
     * 7. Driver management in each test
     */
}
