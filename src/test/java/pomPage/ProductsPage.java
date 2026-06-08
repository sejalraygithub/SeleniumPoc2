package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductsPage extends BasePage {

    private final By allProductsHeading = By.xpath("//h2[contains(.,'All Products')]");
    private final By searchProductInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By productCards = By.cssSelector(".features_items .product-image-wrapper");
    private final By searchedProductsHeading = By.xpath("//h2[contains(.,'Searched Products')]");

    private final By firstAddToCartButton = By.xpath("(//a[contains(@class,'add-to-cart') and contains(.,'Add to cart')])[1]");
    private final By secondAddToCartButton = By.xpath("(//a[contains(@class,'add-to-cart') and contains(.,'Add to cart')])[3]");
    private final By firstViewProductLink = By.xpath("(//a[contains(.,'View Product')])[1]");
    private final By viewCartLinkOnPopup = By.xpath("//u[contains(.,'View Cart')]");
    private final By continueShoppingButton = By.xpath("//button[contains(.,'Continue Shopping')]");

    private final By womenCategoryLink = By.xpath("//a[@href='#Women']");
    private final By dressCategoryLink = By.xpath("//a[contains(@href,'category_products/1')]");
    private final By brandPoloLink = By.xpath("//a[contains(@href,'brand_products/Polo')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // ✅ ASSERTIONS ADDED
    // =========================

    public void verifyProductsPageIsVisible() {
        Assert.assertTrue(isDisplayed(allProductsHeading), "All Products heading not visible!");
        Assert.assertTrue(isDisplayed(searchProductInput), "Search input not visible!");
        Assert.assertTrue(isDisplayed(searchButton), "Search button not visible!");
    }

    public void verifyProductListIsVisible() {
        int count = visibleElements(productCards).size();
        Assert.assertTrue(count > 0, "No products are visible on Products page!");
    }

    public void verifyProductCountGreaterThan(int minCount) {
        int count = visibleElements(productCards).size();
        Assert.assertTrue(count >= minCount, "Product count is less than expected!");
    }

    public void verifySearchedProductsHeadingIsVisible() {
        Assert.assertTrue(
            isDisplayed(searchedProductsHeading),
            "Searched Products heading not visible!"
        );
    }

    public void verifySearchResultsAreDisplayed() {
        int count = visibleElements(productCards).size();
        Assert.assertTrue(count > 0, "No search results found!");
    }

    public void verifyCategorySectionWorks() {
        Assert.assertTrue(isDisplayed(womenCategoryLink), "Women category not visible!");
        click(womenCategoryLink);

        Assert.assertTrue(isDisplayed(dressCategoryLink), "Dress category not visible!");
        click(dressCategoryLink);
    }

    public void verifyBrandSectionWorks() {
        scrollToElement(brandPoloLink);
        Assert.assertTrue(isDisplayed(brandPoloLink), "Brand link not visible!");
    }

    // =========================
    // ACTION METHODS (UNCHANGED)
    // =========================

    public boolean isProductsPageVisible() {
        return isDisplayed(allProductsHeading) &&
               isDisplayed(searchProductInput) &&
               isDisplayed(searchButton);
    }

    public int visibleProductCount() {
        return visibleElements(productCards).size();
    }

    public ProductsPage searchProduct(String productName) {
        type(searchProductInput, productName);
        click(searchButton);
        return this;
    }

    public boolean isSearchedProductsHeadingVisible() {
        return isDisplayed(searchedProductsHeading);
    }

    public CartPage addFirstProductAndOpenCart() {
        scrollToElement(firstAddToCartButton);
        click(firstAddToCartButton);
        click(viewCartLinkOnPopup);
        return new CartPage(driver);
    }

    public ProductsPage addTwoProductsToCart() {
        scrollToElement(firstAddToCartButton);
        click(firstAddToCartButton);
        click(continueShoppingButton);

        scrollToElement(secondAddToCartButton);
        click(secondAddToCartButton);
        click(continueShoppingButton);

        return this;
    }

    public CartPage openCartFromPopupAfterFirstProduct() {
        scrollToElement(firstAddToCartButton);
        click(firstAddToCartButton);
        click(viewCartLinkOnPopup);
        return new CartPage(driver);
    }

    public ProductDetailsPage openFirstProductDetails() {
        scrollToElement(firstViewProductLink);
        click(firstViewProductLink);
        return new ProductDetailsPage(driver);
    }

    public ProductsPage filterWomenDressProducts() {
        click(womenCategoryLink);
        click(dressCategoryLink);
        return this;
    }

    public ProductsPage filterPoloBrandProducts() {
        scrollToElement(brandPoloLink);
        click(brandPoloLink);
        return this;
    }
}