package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupLoginPage {

    WebDriver driver;

    WebElement signupHeading;
    WebElement signupNameTextbox;
    WebElement signupEmailTextbox;
    WebElement signupButton;
    WebElement loginHeading;
    WebElement loginEmailTextbox;
    WebElement loginPasswordTextbox;
    WebElement loginButton;

    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
        signupHeading = driver.findElement(By.xpath("//h2[contains(text(),'New User Signup!')]"));
        signupNameTextbox = driver.findElement(By.cssSelector("input[data-qa='signup-name']"));
        signupEmailTextbox = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
        signupButton = driver.findElement(By.cssSelector("button[data-qa='signup-button']"));
        loginHeading = driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"));
        loginEmailTextbox = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        loginPasswordTextbox = driver.findElement(By.cssSelector("input[data-qa='login-password']"));
        loginButton = driver.findElement(By.cssSelector("button[data-qa='login-button']"));
    }

    public boolean isSignupFormDisplayed() {
        return signupHeading.isDisplayed()
                && signupNameTextbox.isDisplayed()
                && signupEmailTextbox.isDisplayed()
                && signupButton.isDisplayed();
    }

    public boolean isLoginFormDisplayed() {
        return loginHeading.isDisplayed()
                && loginEmailTextbox.isDisplayed()
                && loginPasswordTextbox.isDisplayed()
                && loginButton.isDisplayed();
    }

    public AccountInformationPage enterSignupDetails(String name, String email) {
        signupNameTextbox.clear();
        signupNameTextbox.sendKeys(name);
        signupEmailTextbox.clear();
        signupEmailTextbox.sendKeys(email);
        signupButton.click();
        return new AccountInformationPage(driver);
    }

    public void submitSignupDetails(String name, String email) {
        signupNameTextbox.clear();
        signupNameTextbox.sendKeys(name);
        signupEmailTextbox.clear();
        signupEmailTextbox.sendKeys(email);
        signupButton.click();
    }

    public HomePage loginUser(String email, String password) {
        loginEmailTextbox.clear();
        loginEmailTextbox.sendKeys(email);
        loginPasswordTextbox.clear();
        loginPasswordTextbox.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public void submitLoginDetails(String email, String password) {
        loginEmailTextbox.clear();
        loginEmailTextbox.sendKeys(email);
        loginPasswordTextbox.clear();
        loginPasswordTextbox.sendKeys(password);
        loginButton.click();
    }

    public boolean isExistingEmailErrorDisplayed() {
        return driver.findElements(By.xpath("//*[contains(text(),'Email Address already exist!')]")).size() > 0;
    }

    public boolean isInvalidLoginErrorDisplayed() {
        return driver.findElements(By.xpath("//*[contains(text(),'Your email or password is incorrect!')]")).size() > 0;
    }
}
