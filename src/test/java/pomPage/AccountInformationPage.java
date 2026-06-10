package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AccountInformationPage {

    WebDriver driver;

    WebElement accountInformationHeading;
    WebElement genderRadioButton;
    WebElement passwordTextbox;
    WebElement dayDropdown;
    WebElement monthDropdown;
    WebElement yearDropdown;
    WebElement firstNameTextbox;
    WebElement lastNameTextbox;
    WebElement addressTextbox;
    WebElement countryDropdown;
    WebElement stateTextbox;
    WebElement cityTextbox;
    WebElement zipcodeTextbox;
    WebElement mobileNumberTextbox;
    WebElement createAccountButton;

    public AccountInformationPage(WebDriver driver) {
        this.driver = driver;
        accountInformationHeading = driver.findElement(By.xpath("//b[contains(text(),'Enter Account Information')]"));
        genderRadioButton = driver.findElement(By.id("id_gender2"));
        passwordTextbox = driver.findElement(By.id("password"));
        dayDropdown = driver.findElement(By.id("days"));
        monthDropdown = driver.findElement(By.id("months"));
        yearDropdown = driver.findElement(By.id("years"));
        firstNameTextbox = driver.findElement(By.id("first_name"));
        lastNameTextbox = driver.findElement(By.id("last_name"));
        addressTextbox = driver.findElement(By.id("address1"));
        countryDropdown = driver.findElement(By.id("country"));
        stateTextbox = driver.findElement(By.id("state"));
        cityTextbox = driver.findElement(By.id("city"));
        zipcodeTextbox = driver.findElement(By.id("zipcode"));
        mobileNumberTextbox = driver.findElement(By.id("mobile_number"));
        createAccountButton = driver.findElement(By.cssSelector("button[data-qa='create-account']"));
    }

    public boolean isAccountInformationFormDisplayed() {
        return accountInformationHeading.isDisplayed()
                && passwordTextbox.isDisplayed()
                && firstNameTextbox.isDisplayed()
                && lastNameTextbox.isDisplayed()
                && createAccountButton.isDisplayed();
    }

    public AccountCreatedPage createAccount(String firstName, String lastName, String password, String address,
                                            String country, String state, String city, String zipcode,
                                            String mobileNumber) {

        genderRadioButton.click();

        passwordTextbox.clear();
        passwordTextbox.sendKeys(password);

        new Select(dayDropdown).selectByVisibleText("10");
        new Select(monthDropdown).selectByVisibleText("May");
        new Select(yearDropdown).selectByVisibleText("1995");

        firstNameTextbox.clear();
        firstNameTextbox.sendKeys(firstName);

        lastNameTextbox.clear();
        lastNameTextbox.sendKeys(lastName);

        addressTextbox.clear();
        addressTextbox.sendKeys(address);

        new Select(countryDropdown).selectByVisibleText(country);

        stateTextbox.clear();
        stateTextbox.sendKeys(state);

        cityTextbox.clear();
        cityTextbox.sendKeys(city);

        zipcodeTextbox.clear();
        zipcodeTextbox.sendKeys(zipcode);

        mobileNumberTextbox.clear();
        mobileNumberTextbox.sendKeys(mobileNumber);

        createAccountButton.click();

        return new AccountCreatedPage(driver);
    }
}
