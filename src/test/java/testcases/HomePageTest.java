package testcases;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pomPage.HomePage;
import pomPage.SignupLoginPage;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed");
    }

    @Test
    public void verifySignupLoginPageOpens() {
        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();

        Assert.assertTrue(signupLoginPage.isSignupFormDisplayed(), "Signup form is not displayed");
        Assert.assertTrue(signupLoginPage.isLoginFormDisplayed(), "Login form is not displayed");
    }
}
