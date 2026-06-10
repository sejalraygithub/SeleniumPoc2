package utils;

public class TestData {

    public static String userName = "Sejal Rai";
    public static String firstName = "Sejal";
    public static String lastName = "Rai";
    public static String password = "Password123";
    public static String address = "BTM Layout";
    public static String country = "India";
    public static String state = "Karnataka";
    public static String city = "Bengaluru";
    public static String zipcode = "560076";
    public static String mobileNumber = "9876543210";

    public static String productName = "dress";

    public static String cardHolderName = "Sejal Rai";
    public static String cardNumber = "4111111111111111";
    public static String cvc = "123";
    public static String expiryMonth = "12";
    public static String expiryYear = "2030";

    public static String getNewEmail() {
        return "sejal" + System.currentTimeMillis() + "@testmail.com";
    }
}
