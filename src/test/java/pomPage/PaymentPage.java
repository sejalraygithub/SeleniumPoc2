package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {

    WebDriver driver;

    WebElement nameOnCardTextbox;
    WebElement cardNumberTextbox;
    WebElement cvcTextbox;
    WebElement expiryMonthTextbox;
    WebElement expiryYearTextbox;
    WebElement payButton;
    WebElement continueButton;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        nameOnCardTextbox = driver.findElement(By.name("name_on_card"));
        cardNumberTextbox = driver.findElement(By.name("card_number"));
        cvcTextbox = driver.findElement(By.name("cvc"));
        expiryMonthTextbox = driver.findElement(By.name("expiry_month"));
        expiryYearTextbox = driver.findElement(By.name("expiry_year"));
        payButton = driver.findElement(By.id("submit"));
    }

    public boolean isPaymentFormDisplayed() {
        return nameOnCardTextbox.isDisplayed()
                && cardNumberTextbox.isDisplayed()
                && cvcTextbox.isDisplayed()
                && expiryMonthTextbox.isDisplayed()
                && expiryYearTextbox.isDisplayed()
                && payButton.isDisplayed();
    }

    public PaymentPage payByCard(String cardHolderName, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        nameOnCardTextbox.clear();
        nameOnCardTextbox.sendKeys(cardHolderName);

        cardNumberTextbox.clear();
        cardNumberTextbox.sendKeys(cardNumber);

        cvcTextbox.clear();
        cvcTextbox.sendKeys(cvc);

        expiryMonthTextbox.clear();
        expiryMonthTextbox.sendKeys(expiryMonth);

        expiryYearTextbox.clear();
        expiryYearTextbox.sendKeys(expiryYear);

        payButton.click();
        return this;
    }

    public boolean isOrderPlacedMessageDisplayed() {
        return driver.findElements(By.cssSelector("h2[data-qa='order-placed']")).size() > 0;
    }

    public boolean isInvoiceDownloadDisplayed() {
        return driver.findElements(By.xpath("//a[contains(text(),'Download Invoice')]")).size() > 0;
    }

    public HomePage clickContinue() {
        continueButton = driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
        continueButton.click();
        return new HomePage(driver);
    }
}
