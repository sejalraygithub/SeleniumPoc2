package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;

    WebElement homeButton;
    WebElement signupLoginButton;
    WebElement productsButton;
    WebElement cartButton;
    WebElement logoutButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageDisplayed() {
        if (driver.findElements(By.xpath("//a[contains(text(),'Home')]")).size() == 0) {
            return false;
        }

        homeButton = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        return homeButton.isDisplayed();
    }

    public SignupLoginPage clickSignupLogin() {
        signupLoginButton = driver.findElement(By.linkText("Signup / Login"));
        signupLoginButton.click();
        return new SignupLoginPage(driver);
    }

    public ProductsPage clickProducts() {
        productsButton = driver.findElement(By.xpath("//a[contains(text(),'Products')]"));
        productsButton.click();
        return new ProductsPage(driver);
    }

    public CartPage clickCart() {
        cartButton = driver.findElement(By.xpath("//a[contains(text(),'Cart')]"));
        cartButton.click();
        return new CartPage(driver);
    }

    public boolean isLoggedInAsUserDisplayed() {
        return driver.findElements(By.xpath("//a[contains(text(),'Logged in as')]")).size() > 0;
    }

    public SignupLoginPage clickLogout() {
        logoutButton = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        logoutButton.click();
        return new SignupLoginPage(driver);
    }
}
