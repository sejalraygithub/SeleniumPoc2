package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {

    private final By homeSlider = By.id("slider-carousel");
    private final By productsLink = By.xpath("//a[contains(.,'Products')]");
    private final By cartLink = By.xpath("//a[contains(.,'Cart')]");
    private final By signupLoginLink = By.linkText("Signup / Login");
    private final By featuredProductsHeading = By.xpath("//h2[contains(.,'Features Items')]");
    private final By recommendedItemsHeading = By.xpath("//h2[contains(.,'recommended items')]");
    private final By loggedInUserName = By.xpath("//a[contains(.,'Logged in as')]");
    private final By logoutLink = By.xpath("//a[contains(.,'Logout')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // ✅ ASSERTIONS ADDED
    // =========================

    public void verifyHomePageIsVisible() {
        Assert.assertTrue(
            isDisplayed(homeSlider),
            "Home slider is not visible!"
        );

        Assert.assertTrue(
            pageTitle().contains("Automation Exercise"),
            "Home page title mismatch!"
        );
    }

    public void verifyFeaturedProductsSectionIsVisible() {
        Assert.assertTrue(
            isDisplayed(featuredProductsHeading),
            "Featured products section is not visible!"
        );
    }

    public void verifyRecommendedItemsSectionIsVisible() {
        scrollToBottom();

        Assert.assertTrue(
            isDisplayed(recommendedItemsHeading),
            "Recommended items section is not visible!"
        );
    }

    public void verifyUserIsLoggedIn() {
        Assert.assertTrue(
            isDisplayed(loggedInUserName),
            "Logged-in username is not visible!"
        );
    }

    public void verifyLogoutLinkIsVisible() {
        Assert.assertTrue(
            isDisplayed(logoutLink),
            "Logout link is not visible!"
        );
    }

    // =========================
    // ACTION METHODS (UNCHANGED)
    // =========================

    public boolean isHomePageVisible() {
        return isDisplayed(homeSlider) && pageTitle().contains("Automation Exercise");
    }

    public ProductsPage openProductsPage() {
        click(productsLink);
        return new ProductsPage(driver);
    }

    public CartPage openCartPage() {
        click(cartLink);
        return new CartPage(driver);
    }

    public SignupLoginPage openSignupLoginPage() {
        click(signupLoginLink);
        return new SignupLoginPage(driver);
    }

    public boolean isFeaturedProductsSectionVisible() {
        return isDisplayed(featuredProductsHeading);
    }

    public boolean isRecommendedItemsSectionVisible() {
        scrollToBottom();
        return isDisplayed(recommendedItemsHeading);
    }

    public String loggedInText() {
        return textOf(loggedInUserName);
    }

    public SignupLoginPage logout() {
        click(logoutLink);
        return new SignupLoginPage(driver);
    }
}