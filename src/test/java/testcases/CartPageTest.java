package testcases;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pomPage.CartPage;
import pomPage.HomePage;
import pomPage.ProductDetailsPage;
import pomPage.ProductsPage;

public class CartPageTest extends BaseTest {

    @Test
    public void verifyProductCanBeAddedToCart() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();
        CartPage cartPage = productsPage.addFirstProductToCart();

        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed");
        Assert.assertTrue(cartPage.isProductDisplayedInCart(), "Product is not displayed in cart");
    }

    @Test
    public void verifyProductCanBeRemovedFromCart() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();
        CartPage cartPage = productsPage.addFirstProductToCart();

        cartPage.removeProductFromCart();

        Assert.assertTrue(cartPage.isCartEmptyMessageDisplayed(), "Cart empty message is not displayed");
    }

    @Test
    public void verifyProductQuantityInCart() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductDetailsPage productDetailsPage = homePage
                .clickProducts()
                .openFirstProductDetails();
        CartPage cartPage = productDetailsPage.addProductWithQuantity("3");

        Assert.assertEquals(cartPage.getProductQuantity(), "3", "Product quantity is not correct");
    }
}
