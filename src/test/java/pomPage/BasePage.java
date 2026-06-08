package pomPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    protected WebElement visibleElement(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            handleAds();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }

    protected WebElement clickableElement(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            handleAds();
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }

    protected List<WebElement> visibleElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    //protected void click(By locator) {
      //  try {
      //      clickableElement(locator).click();
      //  } catch (org.openqa.selenium.ElementClickInterceptedException e) {
      //      handleAds();
      //      clickableElement(locator).click();
      //  }
    //}
    protected void click(By locator) {
        try {
            clickableElement(locator).click();
        } catch (Exception e) {
            handleAds();

            WebElement element = visibleElement(locator);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = visibleElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String textOf(By locator) {
        return visibleElement(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return visibleElement(locator).isDisplayed();
        } catch (Exception e) {
            handleAds();
            try {
                return visibleElement(locator).isDisplayed();
            } catch (Exception ignored) {
                return false;
            }
        }
    }

    protected void selectByVisibleText(By locator, String visibleText) {
        new Select(visibleElement(locator)).selectByVisibleText(visibleText);
    }

    protected void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected void scrollToElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", visibleElement(locator));
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    public void handleAds() {
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                try { Thread.sleep(1000); } catch (Exception ignored) {}
                return;
            }
            try {
                ((JavascriptExecutor) driver).executeScript(
                    "const elements = document.querySelectorAll('.google-auto-placed, .adsbygoogle, [id*=google_ads_iframe], [id*=ad_iframe]');" +
                    "elements.forEach(el => el.remove());"
                );
            } catch (Exception ignored) {}
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            List<WebElement> outerFrames = driver.findElements(By.xpath("//iframe[contains(@name, 'aswift') or contains(@id, 'google_ads')]"));
            for (WebElement outerFrame : outerFrames) {
                try {
                    driver.switchTo().frame(outerFrame);
                    List<WebElement> innerFrames = driver.findElements(By.id("ad_iframe"));
                    if (!innerFrames.isEmpty()) {
                        driver.switchTo().frame("ad_iframe");
                        WebElement dismissButton = driver.findElement(By.xpath("//div[@id='dismiss-button'] | //div[@id='dismiss-button']//*[name()='svg'] | //div[@id='dismiss-button']//span"));
                        if (dismissButton.isDisplayed()) {
                            dismissButton.click();
                            break;
                        }
                    }
                    driver.switchTo().defaultContent();
                } catch (Exception ignored) {
                    driver.switchTo().defaultContent();
                }
            }
        } catch (Exception ignored) {
        } finally {
            driver.switchTo().defaultContent();
        }
    }

}
