package testcases;

import org.testng.annotations.Test;
import Base.BaseTest;
import pomPage.HomePage;

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
        homePage.openProductsPage();
        homePage.verifyHomePageIsVisible();
    }

    @Test(groups = {"regression"})
    public void verifyCartNavigationFromHomePage() {

        HomePage homePage = new HomePage(driver);
        homePage.openCartPage();

        // validation handled in CartPage if needed
    }

    @Test(groups = {"regression"})
    public void verifySignupLoginNavigationFromHomePage() {

        HomePage homePage = new HomePage(driver);
        homePage.openSignupLoginPage();

        // validation handled in SignupLoginPage if needed
    }
}