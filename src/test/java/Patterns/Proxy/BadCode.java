// Bad Code - Direct WebDriver usage without any control or tracking
public class WebTest {
    private WebDriver driver;

    @Test
    public void testUserLogin() {
        driver = new ChromeDriver();
        driver.get("http://example.com/login");
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("loginButton")).click();

        // Problems:
        // 1. No way to track actions
        // 2. No way to add custom behavior
        // 3. Direct dependency on WebDriver
        // 4. Hard to debug when test fails
    }
}
