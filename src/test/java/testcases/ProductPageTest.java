package testcases;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pomPage.HomePage;
import pomPage.ProductDetailsPage;
import pomPage.ProductsPage;
import utils.TestData;

public class ProductPageTest extends BaseTest {

    @Test
    public void verifyProductsPageIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();

        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");
        Assert.assertTrue(productsPage.isProductListDisplayed(), "Product list is not displayed");
    }

    @Test
    public void verifyUserCanSearchProduct() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();

        productsPage.searchProduct(TestData.productName);

        Assert.assertTrue(productsPage.isSearchedProductsHeadingDisplayed(), "Searched products heading is not displayed");
        Assert.assertTrue(productsPage.isProductListDisplayed(), "Searched product list is not displayed");
    }

    @Test
    public void verifyProductDetailsPageIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        ProductDetailsPage productDetailsPage = homePage
                .clickProducts()
                .openFirstProductDetails();

        Assert.assertTrue(productDetailsPage.isProductDetailsDisplayed(), "Product details page is not displayed");
    }
}
