package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {

    WebDriver driver;

    WebElement allProductsHeading;
    WebElement searchTextbox;
    WebElement searchButton;
    WebElement searchedProductsHeading;
    WebElement firstAddToCartButton;
    WebElement viewCartLink;
    WebElement firstViewProductLink;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        allProductsHeading = driver.findElement(By.xpath("//h2[contains(text(),'All Products')]"));
        searchTextbox = driver.findElement(By.id("search_product"));
        searchButton = driver.findElement(By.id("submit_search"));
    }

    public boolean isProductsPageDisplayed() {
        return allProductsHeading.isDisplayed()
                && searchTextbox.isDisplayed()
                && searchButton.isDisplayed();
    }

    public boolean isProductListDisplayed() {
        return driver.findElements(By.cssSelector(".features_items .product-image-wrapper")).size() > 0;
    }

    public ProductsPage searchProduct(String productName) {
        searchTextbox.clear();
        searchTextbox.sendKeys(productName);
        searchButton.click();
        return this;
    }

    public boolean isSearchedProductsHeadingDisplayed() {
        searchedProductsHeading = driver.findElement(By.xpath("//h2[contains(text(),'Searched Products')]"));
        return searchedProductsHeading.isDisplayed();
    }

    public CartPage addFirstProductToCart() throws InterruptedException {
        firstAddToCartButton = driver.findElement(By.xpath("(//a[contains(@class,'add-to-cart') and contains(text(),'Add to cart')])[1]"));
        firstAddToCartButton.click();
        Thread.sleep(1000);
        viewCartLink = driver.findElement(By.xpath("//u[contains(text(),'View Cart')]"));
        viewCartLink.click();
        return new CartPage(driver);
    }

    public ProductDetailsPage openFirstProductDetails() {
        firstViewProductLink = driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]"));
        firstViewProductLink.click();
        return new ProductDetailsPage(driver);
    }
}
