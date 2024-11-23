public class EcommerceTest {
    private WebDriver driver;

    public void testCheckout() {
        // Problem 1: Test steps directly in method
        driver.get("http://shop.com");

        // Problem 2: Hard to reuse steps
        login("user", "pass");

        // Problem 3: Fixed sequence of actions
        addToCart("product1");
        updateQuantity(2);
        proceedToCheckout();
        enterShippingDetails();
        submitOrder();

        verifyOrderSuccess();
    }

    /* Major Issues:
     * 1. Test steps are hardcoded in method
     * 2. Can't reuse steps in different sequences
     * 3. Duplicate code across similar tests
     * 4. Hard to maintain common steps
     * 5. No way to undo/redo operations
     * 6. Can't queue or batch operations
     * 7. Difficult to add new operations
     */

    private void login(String user, String pass) {
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("loginButton")).click();
    }

    private void addToCart(String product) {
        // Add to cart logic
    }

    // Other helper methods...
}
