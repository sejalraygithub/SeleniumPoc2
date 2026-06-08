package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountInformationPage extends BasePage {

    private final By accountInfoHeading = By.xpath("//b[contains(.,'Enter Account Information')]");
    private final By titleMrRadioButton = By.id("id_gender1");
    private final By titleMrsRadioButton = By.id("id_gender2");
    private final By passwordInput = By.id("password");
    private final By dayDropdown = By.id("days");
    private final By monthDropdown = By.id("months");
    private final By yearDropdown = By.id("years");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By addressInput = By.id("address1");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipcodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    public AccountInformationPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Assertion: Form visibility
    public void verifyAccountInformationFormIsVisible() {
        Assert.assertTrue(isDisplayed(accountInfoHeading), "Account Info heading not visible!");
        Assert.assertTrue(isDisplayed(titleMrRadioButton), "Mr radio button not visible!");
        Assert.assertTrue(isDisplayed(titleMrsRadioButton), "Mrs radio button not visible!");
        Assert.assertTrue(isDisplayed(passwordInput), "Password field not visible!");
        Assert.assertTrue(isDisplayed(dayDropdown), "Day dropdown not visible!");
        Assert.assertTrue(isDisplayed(monthDropdown), "Month dropdown not visible!");
        Assert.assertTrue(isDisplayed(yearDropdown), "Year dropdown not visible!");
        Assert.assertTrue(isDisplayed(firstNameInput), "First name field not visible!");
        Assert.assertTrue(isDisplayed(lastNameInput), "Last name field not visible!");
        Assert.assertTrue(isDisplayed(addressInput), "Address field not visible!");
        Assert.assertTrue(isDisplayed(createAccountButton), "Create Account button not visible!");
    }

    // ✅ Assertion: Check heading text
    public void verifyAccountInformationText() {
        String actualText = driver.findElement(accountInfoHeading).getText();
        Assert.assertTrue(actualText.contains("Enter Account Information"),
            "Account Information text mismatch!"
        );
    }

    // Action method (unchanged)
    public AccountCreatedPage createAccount(String firstName, String lastName, String password, String address,
                                            String country, String state, String city, String zipcode,
                                            String mobileNumber) {

        click(titleMrsRadioButton);

        type(passwordInput, password);
        selectByVisibleText(dayDropdown, "10");
        selectByVisibleText(monthDropdown, "May");
        selectByVisibleText(yearDropdown, "1995");

        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(addressInput, address);

        selectByVisibleText(countryDropdown, country);

        type(stateInput, state);
        type(cityInput, city);
        type(zipcodeInput, zipcode);
        type(mobileNumberInput, mobileNumber);

        click(createAccountButton);

        return new AccountCreatedPage(driver);
    }

    // ❌ Negative test assertion
    public void submitEmptyRegistrationForm() {
        click(createAccountButton);
    }

    // Optional validation after submit (can fail validation message later)
    public void verifyErrorMessageIfAny(By errorLocator) {
        Assert.assertTrue(
            isDisplayed(errorLocator),
            "Expected error message is not displayed!"
        );
    }
}