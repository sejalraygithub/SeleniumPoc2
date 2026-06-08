package testcases;

import org.testng.annotations.Test;
import Base.BaseTest;
import pomPage.HomePage;
import pomPage.ProductDetailsPage;
import pomPage.ProductsPage;
import utils.TestData;

public class ProductPageTest extends BaseTest {

    @Test(groups = {"ui"})
    public void verifyProductsPageLayout() {

        ProductsPage productsPage = new HomePage(driver)
                .openProductsPage();

        productsPage.verifyProductsPageIsVisible();
        productsPage.verifyProductListIsVisible();
    }

    @Test(groups = {"e2e", "regression"})
    public void verifyUserCanSearchProductAndAddToCart() {

        ProductsPage productsPage = new HomePage(driver)
                .openProductsPage();

        productsPage.searchProduct(TestData.PRODUCT_SEARCH_TEXT);
        productsPage.verifySearchedProductsHeadingIsVisible();

        productsPage.addFirstProductAndOpenCart()
                .verifyProductIsVisibleInCart();
    }

    @Test(groups = {"regression"})
    public void verifyProductDetailsPageShowsProductInformation() {

        ProductDetailsPage productDetailsPage = new HomePage(driver)
                .openProductsPage()
                .openFirstProductDetails();

        productDetailsPage.verifyProductDetailsAreVisible();
    }

    @Test(groups = {"regression"})
    public void verifyCategoryFilterWorks() {

        ProductsPage productsPage = new HomePage(driver)
                .openProductsPage()
                .filterWomenDressProducts();

        productsPage.verifyProductListIsVisible();
    }

    @Test(groups = {"regression"})
    public void verifyBrandFilterWorks() {

        ProductsPage productsPage = new HomePage(driver)
                .openProductsPage()
                .filterPoloBrandProducts();

        productsPage.verifyBrandSectionWorks();
    }

    @Test(groups = {"negative"})
    public void verifyInvalidProductSearchShowsNoProductCards() {

        ProductsPage productsPage = new HomePage(driver)
                .openProductsPage();

        productsPage.searchProduct(TestData.INVALID_PRODUCT_SEARCH_TEXT);
        productsPage.verifySearchedProductsHeadingIsVisible();
    }
}