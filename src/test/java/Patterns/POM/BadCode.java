public class LoginTests {

    @Test
    public void testLoginWithValidCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com/login");

        // Locating elements directly in the test
        driver.findElement(By.id("username")).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPassword");
        driver.findElement(By.id("loginButton")).click();

        // Assertions
        Assert.assertTrue(driver.findElement(By.id("welcomeMessage")).isDisplayed());
        driver.quit();
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://example.com/login");

        // Repeated locator code
        driver.findElement(By.id("username")).sendKeys("invalidUser");
        driver.findElement(By.id("password")).sendKeys("wrongPassword");
        driver.findElement(By.id("loginButton")).click();

        // Assertions
        Assert.assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed());
        driver.quit();
    }
}
