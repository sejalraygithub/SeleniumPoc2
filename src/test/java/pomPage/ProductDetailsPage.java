package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage {

    WebDriver driver;

    WebElement productName;
    WebElement productCategory;
    WebElement productPrice;
    WebElement productAvailability;
    WebElement quantityTextbox;
    WebElement addToCartButton;
    WebElement viewCartLink;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        productName = driver.findElement(By.cssSelector(".product-information h2"));
        productCategory = driver.findElement(By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]"));
        productPrice = driver.findElement(By.cssSelector(".product-information span span"));
        productAvailability = driver.findElement(By.xpath("//b[contains(text(),'Availability')]"));
        quantityTextbox = driver.findElement(By.id("quantity"));
        addToCartButton = driver.findElement(By.cssSelector("button.cart"));
    }

    public boolean isProductDetailsDisplayed() {
        return productName.isDisplayed()
                && productCategory.isDisplayed()
                && productPrice.isDisplayed()
                && productAvailability.isDisplayed();
    }

    public CartPage addProductWithQuantity(String quantity) throws InterruptedException {
        quantityTextbox.clear();
        quantityTextbox.sendKeys(quantity);
        addToCartButton.click();
        Thread.sleep(1000);
        viewCartLink = driver.findElement(By.xpath("//u[contains(text(),'View Cart')]"));
        viewCartLink.click();
        return new CartPage(driver);
    }
}
