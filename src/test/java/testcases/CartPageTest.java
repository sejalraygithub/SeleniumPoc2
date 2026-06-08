package testcases;

import org.testng.annotations.Test;
import Base.BaseTest;
import pomPage.CartPage;
import pomPage.HomePage;

public class CartPageTest extends BaseTest {

    @Test(groups = {"ui"})
    public void verifyCartTableLayoutAfterAddingProduct() {
        CartPage cartPage = new HomePage(driver)
                .openProductsPage()
                .addFirstProductAndOpenCart();

        cartPage.verifyCartTableIsVisible();
        cartPage.verifyProductIsVisibleInCart();
    }

    @Test(groups = {"regression"})
    public void verifyProductCanBeRemovedFromCart() {
        CartPage cartPage = new HomePage(driver)
                .openProductsPage()
                .addFirstProductAndOpenCart();

        cartPage.removeFirstProduct();
        cartPage.verifyEmptyCartMessageIsVisible();
    }

    @Test(groups = {"regression"})
    public void verifyProductQuantityFromDetailsPage() {
        CartPage cartPage = new HomePage(driver)
                .openProductsPage()
                .openFirstProductDetails()
                .addProductWithQuantityAndOpenCart("3");

        cartPage.verifyFirstProductQuantity("3");
    }

    @Test(groups = {"negative"})
    public void verifyGuestUserIsAskedToLoginBeforeCheckout() {
        new HomePage(driver)
                .openProductsPage()
                .addFirstProductAndOpenCart()
                .proceedToCheckoutAsGuest()
                .verifyLoginFormIsVisible();
    }
}