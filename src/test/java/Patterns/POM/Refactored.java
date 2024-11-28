/**
 * Page Object Pattern for Login Page
 * Encapsulates page structure and behavior
 */
class LoginPage {
    private final WebDriver driver;

    /**
     * Locators centralized in one place
     * Easy to update if UI changes
     */
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("loginButton");
    private final By welcomeMessage = By.id("welcomeMessage");
    private final By errorMessage = By.id("errorMessage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Low-level page interactions
     * Each method handles one specific element interaction
     */
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

    /**
     * High-level business operation
     * Combines multiple low-level actions
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}

/**
 * Test class using Page Object
 * Shows clean separation of test logic and page interactions
 */
public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://example.com/login");
        loginPage = new LoginPage(driver);
    }

    /**
     * Test methods focus on business logic
     * Page interaction details hidden in Page Object
     */
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

/* How Page Object Pattern Helps:
 * 1. Structure:
 *    - Page elements in one place
 *    - Easy to update locators
 *    - Reusable page methods
 *
 * 2. Maintenance:
 *    - UI changes only affect page object
 *    - Tests remain stable
 *    - Single point of update
 *
 * 3. Test Quality:
 *    - Clean, readable tests
 *    - Business-focused test methods
 *    - Reduced code duplication
 */
