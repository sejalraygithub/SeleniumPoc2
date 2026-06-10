package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountCreatedPage {

    WebDriver driver;

    WebElement accountCreatedHeading;
    WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
        accountCreatedHeading = driver.findElement(By.cssSelector("h2[data-qa='account-created']"));
        continueButton = driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
    }

    public boolean isAccountCreatedMessageDisplayed() {
        return accountCreatedHeading.isDisplayed();
    }

    public HomePage clickContinue() {
        continueButton.click();
        return new HomePage(driver);
    }
}
