// Step 1: Create Page Object
class LoginPage {
    private final WebDriver driver;

    // Centralized locators
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("loginButton");
    private final By welcomeMessage = By.id("welcomeMessage");
    private final By errorMessage = By.id("errorMessage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Step 2: Page interactions as methods
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    // Step 3: Business-level operations
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}

// Step 4: Clean test class
public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://example.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("validUser", "validPassword");
        Assert.assertTrue(loginPage.isWelcomeMessageDisplayed());
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("invalidUser", "wrongPassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

/*
Key Changes:
1. Centralized locators in page object
2. Separated page interactions from tests
3. Reusable page methods
4. Clean test methods
5. Consistent element handling
6. Easy maintenance
7. Better test organization
*/
