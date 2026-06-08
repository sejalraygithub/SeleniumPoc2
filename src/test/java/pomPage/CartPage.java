package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage extends BasePage {

    private final By cartTable = By.id("cart_info");
    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");
    private final By firstProductImage = By.cssSelector(".cart_product img");
    private final By firstProductName = By.cssSelector(".cart_description h4 a");
    private final By firstProductPrice = By.cssSelector(".cart_price p");
    private final By firstProductQuantity = By.cssSelector(".cart_quantity button");
    private final By firstProductTotal = By.cssSelector(".cart_total_price");
    private final By firstDeleteButton = By.cssSelector(".cart_quantity_delete");
    private final By proceedToCheckoutButton = By.xpath("//a[contains(.,'Proceed To Checkout')]");
    private final By registerLoginLinkOnCheckoutPopup = By.xpath("//u[contains(.,'Register / Login')]");
    private final By emptyCartMessage = By.xpath("//*[contains(.,'Cart is empty')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // ✅ ASSERTIONS ADDED
    // =========================

    public void verifyCartTableIsVisible() {
        Assert.assertTrue(isDisplayed(cartTable), "Cart table is not visible!");
    }

    public void verifyProductIsVisibleInCart() {
        Assert.assertTrue(isDisplayed(firstProductImage), "Product image not visible!");
        Assert.assertTrue(isDisplayed(firstProductName), "Product name not visible!");
        Assert.assertTrue(isDisplayed(firstProductPrice), "Product price not visible!");
        Assert.assertTrue(isDisplayed(firstProductQuantity), "Product quantity not visible!");
        Assert.assertTrue(isDisplayed(firstProductTotal), "Product total not visible!");
    }

    public void verifyCartHasProducts() {
        int count = visibleElements(cartRows).size();
        Assert.assertTrue(count > 0, "Cart is empty, no products found!");
    }

    public void verifyProductCount(int expectedCount) {
        int actualCount = visibleElements(cartRows).size();
        Assert.assertEquals(actualCount, expectedCount, "Cart product count mismatch!");
    }

    public void verifyFirstProductQuantity(String expectedQty) {
        String actualQty = textOf(firstProductQuantity);
        Assert.assertEquals(actualQty, expectedQty, "Product quantity mismatch!");
    }

    public void verifyEmptyCartMessageIsVisible() {
        Assert.assertTrue(isDisplayed(emptyCartMessage), "Empty cart message not visible!");
    }

    // =========================
    // ACTION METHODS (UNCHANGED)
    // =========================

    public int cartProductCount() {
        return visibleElements(cartRows).size();
    }

    public String firstProductQuantity() {
        return textOf(firstProductQuantity);
    }

    public void removeFirstProduct() {
        click(firstDeleteButton);
    }

    public CheckoutPage proceedToCheckout() {
        click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }

    public SignupLoginPage proceedToCheckoutAsGuest() {
        click(proceedToCheckoutButton);
        click(registerLoginLinkOnCheckoutPopup);
        return new SignupLoginPage(driver);
    }

    public boolean isEmptyCartMessageVisible() {
        return isDisplayed(emptyCartMessage);
    }
}