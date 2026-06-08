package testcases;

import org.testng.annotations.Test;
import Base.BaseTest;
import pomPage.CartPage;
import pomPage.HomePage;
import pomPage.ProductsPage;
import pomPage.SignupLoginPage;

public class HomePageTest extends BaseTest {

    @Test(groups = {"e2e", "smoke"})
    public void verifyHomePageLoadsCorrectly() {

        HomePage homePage = new HomePage(driver);
        homePage.verifyHomePageIsVisible();
    }

    @Test(groups = {"ui"})
    public void verifyHomePageMainSectionsAreVisible() {

        HomePage homePage = new HomePage(driver);
        homePage.verifyFeaturedProductsSectionIsVisible();
        homePage.verifyRecommendedItemsSectionIsVisible();
    }

    @Test(groups = {"regression"})
    public void verifyProductsNavigationFromHomePage() {

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.openProductsPage();
        productsPage.verifyProductsPageIsVisible();
    }

    @Test(groups = {"regression"})
    public void verifyCartNavigationFromHomePage() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.openCartPage();
        cartPage.verifyCartTableIsVisible();
    }

    @Test(groups = {"regression"})
    public void verifySignupLoginNavigationFromHomePage() {

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.openSignupLoginPage();
        signupLoginPage.verifySignupFormIsVisible();
    }
}