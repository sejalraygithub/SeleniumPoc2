package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignupLoginPage extends BasePage {

    private final By newUserSignupHeading = By.xpath("//h2[contains(.,'New User Signup!')]");
    private final By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("button[data-qa='signup-button']");

    private final By loginHeading = By.xpath("//h2[contains(.,'Login to your account')]");
    private final By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private final By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");

    private final By signupEmailError = By.xpath("//*[contains(text(),'Email Address already exist!')]");
    private final By invalidLoginError = By.xpath("//*[contains(text(),'Your email or password is incorrect!')]");

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // ✅ ASSERTIONS ADDED
    // =========================

    public void verifySignupFormIsVisible() {
        Assert.assertTrue(isDisplayed(newUserSignupHeading), "Signup heading not visible!");
        Assert.assertTrue(isDisplayed(signupNameInput), "Signup name input not visible!");
        Assert.assertTrue(isDisplayed(signupEmailInput), "Signup email input not visible!");
        Assert.assertTrue(isDisplayed(signupButton), "Signup button not visible!");
    }

    public void verifyLoginFormIsVisible() {
        Assert.assertTrue(isDisplayed(loginHeading), "Login heading not visible!");
        Assert.assertTrue(isDisplayed(loginEmailInput), "Login email input not visible!");
        Assert.assertTrue(isDisplayed(loginPasswordInput), "Login password input not visible!");
        Assert.assertTrue(isDisplayed(loginButton), "Login button not visible!");
    }

    public void verifySignupPageIsLoaded() {
        Assert.assertTrue(
            isDisplayed(newUserSignupHeading) && isDisplayed(loginHeading),
            "Signup/Login page not loaded properly!"
        );
    }

    public void verifyExistingEmailErrorIsVisible() {
        Assert.assertTrue(
            isDisplayed(signupEmailError),
            "Existing email error message not visible!"
        );
    }

    public void verifyInvalidLoginErrorIsVisible() {
        Assert.assertTrue(
            isDisplayed(invalidLoginError),
            "Invalid login error message not visible!"
        );
    }

    // =========================
    // ACTION METHODS (UNCHANGED)
    // =========================

    public AccountInformationPage startSignup(String name, String email) {
        type(signupNameInput, name);
        type(signupEmailInput, email);
        click(signupButton);
        return new AccountInformationPage(driver);
    }

    public void submitSignup(String name, String email) {
        type(signupNameInput, name);
        type(signupEmailInput, email);
        click(signupButton);
    }

    public HomePage login(String email, String password) {
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
        return new HomePage(driver);
    }

    public void submitLogin(String email, String password) {
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
    }

    public boolean isExistingEmailErrorVisible() {
        return isDisplayed(signupEmailError);
    }

    public boolean isInvalidLoginErrorVisible() {
        return isDisplayed(invalidLoginError);
    }
}