// Page Object for Login page
public class LoginPage {

    private WebDriver driver;

    // Centralized locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginButton");
    private By welcomeMessage = By.id("welcomeMessage");
    private By errorMessage = By.id("errorMessage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

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
}

public class LoginTests {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get("http://example.com/login");
    }

    @Test
    public void testLoginWithValidCredentials() {
        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPassword");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isWelcomeMessageDisplayed());
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
