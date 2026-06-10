package testcases;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pomPage.AccountCreatedPage;
import pomPage.AccountInformationPage;
import pomPage.HomePage;
import pomPage.SignupLoginPage;
import utils.TestData;

public class LoginTest extends BaseTest {

    @Test
    public void verifyUserCanLogin() {
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

        homePage = accountCreatedPage.clickContinue();
        signupLoginPage = homePage.clickLogout();
        homePage = signupLoginPage.loginUser(email, TestData.password);

        Assert.assertTrue(homePage.isLoggedInAsUserDisplayed(), "User is not logged in");
    }

    @Test
    public void verifyInvalidLoginShowsError() {
        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();

        signupLoginPage.submitLoginDetails("wronguser@testmail.com", "wrongpassword");

        Assert.assertTrue(signupLoginPage.isInvalidLoginErrorDisplayed(), "Invalid login error is not displayed");
    }
}
