package testcases;

import org.testng.annotations.Test;
import Base.BaseTest;
import pomPage.HomePage;
import pomPage.SignupLoginPage;

public class LoginTest extends BaseTest {

    @Test(groups = {"positive", "regression"})
    public void verifyValidUserCanLogin() {

        SignupLoginPage signupLoginPage = new HomePage(driver)
                .openSignupLoginPage();

        HomePage homePage = signupLoginPage.login(BaseTest.validEmail, BaseTest.validPassword);

        homePage.verifyUserIsLoggedIn();
    }

    @Test(groups = {"e2e", "regression"})
    public void verifyValidUserCanLoginAndLogout() {

        SignupLoginPage signupLoginPage = new HomePage(driver)
                .openSignupLoginPage();

        HomePage homePage = signupLoginPage.login(BaseTest.validEmail, BaseTest.validPassword);

        homePage.logout();
        signupLoginPage.verifyLoginFormIsVisible();
    }

    @Test(groups = {"negative", "regression"})
    public void verifyInvalidLoginShowsCorrectError() {

        SignupLoginPage signupLoginPage = new HomePage(driver)
                .openSignupLoginPage();

        signupLoginPage.submitLogin("wrong.user@test.com", "wrong-password");

        signupLoginPage.verifyInvalidLoginErrorIsVisible();
    }

    @Test(groups = {"negative"})
    public void verifyBlankLoginDoesNotSubmit() {

        SignupLoginPage signupLoginPage = new HomePage(driver)
                .openSignupLoginPage();

        signupLoginPage.submitLogin("", "");

        signupLoginPage.verifyLoginFormIsVisible();
    }
}