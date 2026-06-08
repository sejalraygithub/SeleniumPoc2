package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

    private final By deliveryAddressBox = By.id("address_delivery");
    private final By billingAddressBox = By.id("address_invoice");
    private final By orderReviewTable = By.cssSelector(".cart_info");
    private final By commentBox = By.name("message");
    private final By placeOrderButton = By.xpath("//a[contains(.,'Place Order')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // ✅ ASSERTIONS ADDED
    // =========================

    public void verifyCheckoutPageIsVisible() {
        Assert.assertTrue(
            isDisplayed(deliveryAddressBox),
            "Delivery address box is not visible!"
        );

        Assert.assertTrue(
            isDisplayed(billingAddressBox),
            "Billing address box is not visible!"
        );

        Assert.assertTrue(
            isDisplayed(orderReviewTable),
            "Order review table is not visible!"
        );
    }

    public void verifyDeliveryAndBillingAddressesVisible() {
        Assert.assertTrue(
            isDisplayed(deliveryAddressBox),
            "Delivery address is not visible!"
        );

        Assert.assertTrue(
            isDisplayed(billingAddressBox),
            "Billing address is not visible!"
        );
    }

    public void verifyPlaceOrderButtonIsVisible() {
        Assert.assertTrue(
            isDisplayed(placeOrderButton),
            "Place Order button is not visible!"
        );
    }

    // =========================
    // ACTION METHOD (UNCHANGED)
    // =========================

    public PaymentPage placeOrder(String orderComment) {
        type(commentBox, orderComment);
        click(placeOrderButton);
        return new PaymentPage(driver);
    }
}