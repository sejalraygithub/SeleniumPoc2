package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    WebDriver driver;

    WebElement deliveryAddress;
    WebElement billingAddress;
    WebElement orderSummary;
    WebElement commentTextbox;
    WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        deliveryAddress = driver.findElement(By.id("address_delivery"));
        billingAddress = driver.findElement(By.id("address_invoice"));
        orderSummary = driver.findElement(By.cssSelector(".cart_info"));
        commentTextbox = driver.findElement(By.name("message"));
        placeOrderButton = driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));
    }

    public boolean isCheckoutPageDisplayed() {
        return deliveryAddress.isDisplayed()
                && billingAddress.isDisplayed()
                && orderSummary.isDisplayed();
    }

    public PaymentPage placeOrder(String orderComment) {
        commentTextbox.clear();
        commentTextbox.sendKeys(orderComment);
        placeOrderButton.click();
        return new PaymentPage(driver);
    }
}
