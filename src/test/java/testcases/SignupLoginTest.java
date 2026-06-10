package testcases;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pomPage.AccountCreatedPage;
import pomPage.AccountInformationPage;
import pomPage.HomePage;
import pomPage.SignupLoginPage;
import utils.TestData;

public class SignupLoginTest extends BaseTest {

    @Test
    public void verifyNewUserCanCreateAccount() {
        String email = TestData.getNewEmail();

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        AccountInformationPage accountInformationPage =
                signupLoginPage.enterSignupDetails(TestData.userName, email);

        AccountCreatedPage accountCreatedPage = accountInformationPage.createAccount(
                TestData.firstName,
                TestData.lastName,
                TestData.password,
                TestData.address,
                TestData.country,
                TestData.state,
                TestData.city,
                TestData.zipcode,
                TestData.mobileNumber
        );

        Assert.assertTrue(accountCreatedPage.isAccountCreatedMessageDisplayed(), "Account created message is not displayed");
    }

    @Test
    public void verifyAccountInformationFormOpensAfterSignup() {
        String email = TestData.getNewEmail();

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        AccountInformationPage accountInformationPage =
                signupLoginPage.enterSignupDetails(TestData.userName, email);

        Assert.assertTrue(accountInformationPage.isAccountInformationFormDisplayed(), "Account information form is not displayed");
    }
}
