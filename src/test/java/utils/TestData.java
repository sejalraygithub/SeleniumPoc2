package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestData {
    public static final String SIGNUP_USER_NAME = "Sejal Rai";
    public static final String FIRST_NAME = "Sejal";
    public static final String LAST_NAME = "Rai";
    public static final String PASSWORD = "Test@12345";
    public static final String ADDRESS = "Btm Layout";
    public static final String COUNTRY = "India";
    public static final String STATE = "Karnatka";
    public static final String CITY = "Bengaluru";
    public static final String ZIPCODE = "560076";
    public static final String MOBILE_NUMBER = "9876543210";
    public static final String PRODUCT_SEARCH_TEXT = "dress";
    public static final String INVALID_PRODUCT_SEARCH_TEXT = "xyz-no-product";
    public static final String CARD_HOLDER_NAME = "Sejal";
    public static final String CARD_NUMBER = "4111111111111111";
    public static final String CARD_CVC = "123";
    public static final String CARD_EXPIRY_MONTH = "12";
    public static final String CARD_EXPIRY_YEAR = "2030";

    private TestData() {
    }

    public static String uniqueEmail() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "Sejal" + timestamp + "@testmail.com";
    }
}
