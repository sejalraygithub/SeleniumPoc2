package testcases;

import org.testng.annotations.Test;
import Base.BaseTest;
import pomPage.CheckoutPage;
import pomPage.HomePage;
import pomPage.PaymentPage;
import utils.TestData;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"ui", "regression"})
    public void verifyCheckoutPageShowsAddressAndOrderSummary() {

        CheckoutPage checkoutPage = new HomePage(driver)
                .openSignupLoginPage()
                .login(BaseTest.validEmail, BaseTest.validPassword)
                .openProductsPage()
                .searchProduct(TestData.PRODUCT_SEARCH_TEXT)
                .addFirstProductAndOpenCart()
                .proceedToCheckout();

        checkoutPage.verifyCheckoutPageIsVisible();
    }

    @Test(groups = {"e2e", "regression"})
    public void verifyLoggedInUserCanPlaceOrderAndDownloadInvoice() {

        PaymentPage paymentPage = new HomePage(driver)
                .openSignupLoginPage()
                .login(BaseTest.validEmail, BaseTest.validPassword)
                .openProductsPage()
                .searchProduct(TestData.PRODUCT_SEARCH_TEXT)
                .addFirstProductAndOpenCart()
                .proceedToCheckout()
                .placeOrder("Please deliver between 10 AM and 6 PM");

        paymentPage.verifyPaymentFormIsVisible();

        paymentPage.payWithCard(
                TestData.CARD_HOLDER_NAME,
                TestData.CARD_NUMBER,
                TestData.CARD_CVC,
                TestData.CARD_EXPIRY_MONTH,
                TestData.CARD_EXPIRY_YEAR
        );

        paymentPage.verifyOrderPlacedMessageIsVisible();
        paymentPage.verifyInvoiceDownloadIsAvailable();
    }
}