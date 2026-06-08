package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PaymentPage extends BasePage {

    private final By nameOnCardInput = By.name("name_on_card");
    private final By cardNumberInput = By.name("card_number");
    private final By cvcInput = By.name("cvc");
    private final By expiryMonthInput = By.name("expiry_month");
    private final By expiryYearInput = By.name("expiry_year");
    private final By payAndConfirmButton = By.id("submit");
    private final By orderPlacedHeading = By.cssSelector("h2[data-qa='order-placed']");
    private final By downloadInvoiceButton = By.xpath("//a[contains(.,'Download Invoice')]");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // ✅ ASSERTIONS ADDED
    // =========================

    public void verifyPaymentFormIsVisible() {
        Assert.assertTrue(isDisplayed(nameOnCardInput), "Name on card field not visible!");
        Assert.assertTrue(isDisplayed(cardNumberInput), "Card number field not visible!");
        Assert.assertTrue(isDisplayed(cvcInput), "CVC field not visible!");
        Assert.assertTrue(isDisplayed(expiryMonthInput), "Expiry month field not visible!");
        Assert.assertTrue(isDisplayed(expiryYearInput), "Expiry year field not visible!");
        Assert.assertTrue(isDisplayed(payAndConfirmButton), "Pay & Confirm button not visible!");
    }

    public void verifyOrderPlacedMessageIsVisible() {
        Assert.assertTrue(
            isDisplayed(orderPlacedHeading),
            "Order placed message is not visible!"
        );
    }

    public void verifyInvoiceDownloadIsAvailable() {
        Assert.assertTrue(
            isDisplayed(downloadInvoiceButton),
            "Invoice download button is not visible!"
        );
    }

    public void verifyPaymentPageTitle(String expectedTitle) {
        Assert.assertEquals(
            driver.getTitle(),
            expectedTitle,
            "Payment page title mismatch!"
        );
    }

    public void verifyPaymentPageURLContains(String keyword) {
        Assert.assertTrue(
            driver.getCurrentUrl().contains(keyword),
            "Payment page URL mismatch!"
        );
    }

    // =========================
    // ACTION METHODS (UNCHANGED)
    // =========================

    public PaymentPage payWithCard(String cardHolderName, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        type(nameOnCardInput, cardHolderName);
        type(cardNumberInput, cardNumber);
        type(cvcInput, cvc);
        type(expiryMonthInput, expiryMonth);
        type(expiryYearInput, expiryYear);
        click(payAndConfirmButton);
        return this;
    }

    public HomePage continueToHomePage() {
        click(continueButton);
        return new HomePage(driver);
    }
}