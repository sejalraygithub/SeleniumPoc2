package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductDetailsPage extends BasePage {

    private final By productName = By.cssSelector(".product-information h2");
    private final By productCategory = By.xpath("//div[@class='product-information']/p[contains(.,'Category')]");
    private final By productPrice = By.cssSelector(".product-information span span");
    private final By productAvailability = By.xpath("//b[contains(.,'Availability')]");
    private final By quantityInput = By.id("quantity");
    private final By addToCartButton = By.cssSelector("button.cart");
    private final By viewCartLinkOnPopup = By.xpath("//u[contains(.,'View Cart')]");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // ✅ ASSERTIONS ADDED
    // =========================

    public void verifyProductDetailsAreVisible() {
        Assert.assertTrue(isDisplayed(productName), "Product name is not visible!");
        Assert.assertTrue(isDisplayed(productCategory), "Product category is not visible!");
        Assert.assertTrue(isDisplayed(productPrice), "Product price is not visible!");
        Assert.assertTrue(isDisplayed(productAvailability), "Product availability is not visible!");
    }

    public void verifyProductName(String expectedName) {
        String actualName = textOf(productName);
        Assert.assertEquals(actualName, expectedName, "Product name mismatch!");
    }

    public void verifyProductPrice(String expectedPrice) {
        String actualPrice = textOf(productPrice);
        Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch!");
    }

    public void verifyQuantityFieldIsVisible() {
        Assert.assertTrue(isDisplayed(quantityInput), "Quantity input field is not visible!");
    }

    public void verifyDefaultQuantity(String expectedQty) {
        String actualQty = driver.findElement(quantityInput).getAttribute("value");
        Assert.assertEquals(actualQty, expectedQty, "Default quantity mismatch!");
    }

    // =========================
    // ACTION METHOD (UNCHANGED)
    // =========================

    public CartPage addProductWithQuantityAndOpenCart(String quantity) {
        type(quantityInput, quantity);
        click(addToCartButton);
        click(viewCartLinkOnPopup);
        return new CartPage(driver);
    }
}