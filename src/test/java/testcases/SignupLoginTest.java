package testcases;

import org.testng.annotations.Test;
import Base.BaseTest;
import pomPage.AccountCreatedPage;
import pomPage.AccountInformationPage;
import pomPage.HomePage;
import pomPage.SignupLoginPage;
import utils.TestData;

public class SignupLoginTest extends BaseTest {

    @Test(groups = {"ui"})
    public void verifySignupAndLoginFormsAreVisible() {
        SignupLoginPage signupLoginPage = new HomePage(driver).openSignupLoginPage();
        signupLoginPage.verifySignupFormIsVisible();
        signupLoginPage.verifyLoginFormIsVisible();
    }

    @Test(groups = {"ui", "regression"})
    public void verifyAccountInformationFormIsVisibleAfterSignup() {
        SignupLoginPage signupLoginPage = new HomePage(driver).openSignupLoginPage();
        AccountInformationPage accountInformationPage =
                signupLoginPage.startSignup(TestData.SIGNUP_USER_NAME, TestData.uniqueEmail());

        accountInformationPage.verifyAccountInformationFormIsVisible();
    }

    @Test(groups = {"e2e", "regression"})
    public void verifyNewUserCanCreateAccount() {
        SignupLoginPage signupLoginPage = new HomePage(driver).openSignupLoginPage();
        AccountInformationPage accountInformationPage =
                signupLoginPage.startSignup(TestData.SIGNUP_USER_NAME, TestData.uniqueEmail());

        AccountCreatedPage accountCreatedPage = accountInformationPage.createAccount(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.PASSWORD,
                TestData.ADDRESS,
                TestData.COUNTRY,
                TestData.STATE,
                TestData.CITY,
                TestData.ZIPCODE,
                TestData.MOBILE_NUMBER
        );

        accountCreatedPage.verifyAccountCreatedMessageIsVisible();
    }

    @Test(groups = {"negative", "regression"})
    public void verifyExistingEmailShowsSignupError() {
        SignupLoginPage signupLoginPage = new HomePage(driver).openSignupLoginPage();
        signupLoginPage.submitSignup(TestData.SIGNUP_USER_NAME, BaseTest.validEmail);
        signupLoginPage.isExistingEmailErrorVisible();
    }

    @Test(groups = {"negative"})
    public void verifyBlankSignupDoesNotOpenAccountInformationPage() {
        SignupLoginPage signupLoginPage = new HomePage(driver).openSignupLoginPage();
        signupLoginPage.submitSignup("", "");
        signupLoginPage.verifySignupFormIsVisible();
    }
}