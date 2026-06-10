package testcases;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pomPage.AccountCreatedPage;
import pomPage.AccountInformationPage;
import pomPage.CartPage;
import pomPage.CheckoutPage;
import pomPage.HomePage;
import pomPage.PaymentPage;
import pomPage.ProductsPage;
import pomPage.SignupLoginPage;
import utils.TestData;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutPageIsDisplayed() throws InterruptedException {
        String email = TestData.getNewEmail();

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        AccountInformationPage accountInformationPage =
                signupLoginPage.enterSignupDetails(TestData.userName, email);
        AccountCreatedPage accountCreatedPage = accountInformationPage.createAccount(
                TestData.firstName,
                TestData.lastName,
                TestData.password,
                TestData.address,
                TestData.country,
                TestData.state,
                TestData.city,
                TestData.zipcode,
                TestData.mobileNumber
        );

        homePage = accountCreatedPage.clickContinue();
        ProductsPage productsPage = homePage.clickProducts();
        CartPage cartPage = productsPage.addFirstProductToCart();
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), "Checkout page is not displayed");
    }

    @Test
    public void verifyUserCanPlaceOrder() throws InterruptedException {
        String email = TestData.getNewEmail();

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        AccountInformationPage accountInformationPage =
                signupLoginPage.enterSignupDetails(TestData.userName, email);
        AccountCreatedPage accountCreatedPage = accountInformationPage.createAccount(
                TestData.firstName,
                TestData.lastName,
                TestData.password,
                TestData.address,
                TestData.country,
                TestData.state,
                TestData.city,
                TestData.zipcode,
                TestData.mobileNumber
        );

        homePage = accountCreatedPage.clickContinue();
        ProductsPage productsPage = homePage.clickProducts();
        CartPage cartPage = productsPage.addFirstProductToCart();
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();
        PaymentPage paymentPage = checkoutPage.placeOrder("Please deliver between 10 AM and 6 PM");

        Assert.assertTrue(paymentPage.isPaymentFormDisplayed(), "Payment form is not displayed");

        paymentPage.payByCard(
                TestData.cardHolderName,
                TestData.cardNumber,
                TestData.cvc,
                TestData.expiryMonth,
                TestData.expiryYear
        );

        Assert.assertTrue(paymentPage.isOrderPlacedMessageDisplayed(), "Order placed message is not displayed");
        Assert.assertTrue(paymentPage.isInvoiceDownloadDisplayed(), "Invoice download is not displayed");
    }
}
