package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    WebDriver driver;

    WebElement cartTable;
    WebElement productName;
    WebElement productPrice;
    WebElement productQuantity;
    WebElement productTotalPrice;
    WebElement removeProductButton;
    WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartPageDisplayed() {
        cartTable = driver.findElement(By.id("cart_info"));
        return cartTable.isDisplayed();
    }

    public boolean isProductDisplayedInCart() {
        productName = driver.findElement(By.cssSelector(".cart_description h4 a"));
        productPrice = driver.findElement(By.cssSelector(".cart_price p"));
        productQuantity = driver.findElement(By.cssSelector(".cart_quantity button"));
        productTotalPrice = driver.findElement(By.cssSelector(".cart_total_price"));

        return productName.isDisplayed()
                && productPrice.isDisplayed()
                && productQuantity.isDisplayed()
                && productTotalPrice.isDisplayed();
    }

    public String getProductQuantity() {
        productQuantity = driver.findElement(By.cssSelector(".cart_quantity button"));
        return productQuantity.getText();
    }

    public void removeProductFromCart() throws InterruptedException {
        removeProductButton = driver.findElement(By.cssSelector(".cart_quantity_delete"));
        removeProductButton.click();
        Thread.sleep(1000);
    }

    public CheckoutPage clickProceedToCheckout() {
        proceedToCheckoutButton = driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]"));
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }

    public boolean isCartEmptyMessageDisplayed() {
        return driver.findElements(By.xpath("//*[contains(text(),'Cart is empty')]")).size() > 0;
    }
}
