package Base;

    import pomPage.AccountCreatedPage;
    import pomPage.AccountInformationPage;
    import pomPage.HomePage;
    import pomPage.SignupLoginPage;
    import org.openqa.selenium.PageLoadStrategy;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;

	import java.time.Duration;

	public class BaseTest {
	    protected WebDriver driver;
	    public static final String BASE_URL = "https://automationexercise.com/";
	    public static String validEmail = null;
	    public static final String validPassword = "Test@12345";

	    @BeforeMethod(alwaysRun = true)
	    public void openApplication() {
	       
	        ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
	        chromeOptions.addArguments("--disable-notifications");
	        
	        
	        driver = new ChromeDriver(chromeOptions);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	        // Ensure dynamic user is registered
	        
	        if (validEmail == null) {
	            ensureValidUserExists(driver);
	        }

	        driver.get(BASE_URL);
	    }

	    private static synchronized void ensureValidUserExists(WebDriver tempDriver) {
	        if (validEmail != null) {
	            return;
	        }
	        try {
	            tempDriver.get(BASE_URL);
	            HomePage homePage = new HomePage(tempDriver);
	            
	            SignupLoginPage signupLoginPage = homePage.openSignupLoginPage();
	            
	            String email = "Sejal" + System.currentTimeMillis() + "@gmail.com";
	            
	            AccountInformationPage accountInfo = signupLoginPage.startSignup("Auto User", email);
	            
	            AccountCreatedPage accountCreated = accountInfo.createAccount(
	                    "Auto", "User", validPassword, "123 Main St", "United States", "New York", "New York", "10001", "1234567890"
	            );
	            if (accountCreated.isAccountCreatedMessageVisible()) {
	                validEmail = email;
	                System.out.println("--- DYNAMICALLY REGISTERED TEST USER: " + validEmail + " ---");
	                tempDriver.manage().deleteAllCookies();
	            }
	        } catch (Exception e) {
	            System.err.println("Failed to dynamically register user: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    @AfterMethod(alwaysRun = true)
	    public void closeBrowser() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}

