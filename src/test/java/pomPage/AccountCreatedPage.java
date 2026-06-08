package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountCreatedPage extends BasePage {

    private final By accountCreatedHeading = By.cssSelector("h2[data-qa='account-created']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Check message is visible
    public void verifyAccountCreatedMessageIsVisible() {
        Assert.assertTrue(
            isDisplayed(accountCreatedHeading),
            "Account Created message is NOT visible!"
        );
    }

    // ✅ Validate actual text of heading
    public void verifyAccountCreatedText() {
        String actualText = driver.findElement(accountCreatedHeading).getText();
        Assert.assertEquals(
            actualText,
            "ACCOUNT CREATED!",
            "Account created text mismatch!"
        );
    }

    // ✅ Validate page title
    public void verifyPageTitle() {
        Assert.assertEquals(
            driver.getTitle(),
            "Account Created",
            "Page title does not match!"
        );
    }

    // ✅ Validate URL contains expected keyword
    public void verifyPageURL() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("account_created"),
            "URL does not contain expected keyword!"
        );
    }

    // ✅ Check continue button is visible
    public void verifyContinueButtonIsVisible() {
        Assert.assertTrue(
            isDisplayed(continueButton),
            "Continue button is NOT visible!"
        );
    }

    public HomePage continueToHomePage() {
        click(continueButton);
        return new HomePage(driver);
    }

	public boolean isAccountCreatedMessageVisible() {
		return isDisplayed(accountCreatedHeading);
	}
}