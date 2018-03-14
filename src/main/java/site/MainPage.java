package site;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage {
    private WebDriver driver;
    private By allProductLink = By.cssSelector(".all-product-link");
    private By burgerMenu = By.cssSelector(".material-icons.d-inline");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get("http://prestashop-automation.qatestlab.com.ua");
    }

    public void clickAllProductLink(){
        driver.findElement(allProductLink).click();
    }

    public boolean isDesktopVersionOfSite(){
        return new WebDriverWait(driver, 4).until(not(visibilityOf(driver.findElement(burgerMenu)))).booleanValue();
    }

    public boolean isMobileVersionOfSite(){
        return new WebDriverWait(driver, 4).until(visibilityOf(driver.findElement(burgerMenu))).isDisplayed();
    }

    public boolean isCorrectBrowserVersionOpened(BrowserVersion browserVersion){
        if (browserVersion.equals(BrowserVersion.DESKTOP_VERSION)){
            try {
                return isDesktopVersionOfSite();
            } catch (TimeoutException ex){
                return false;
            }
        } else{
            try {
                return isMobileVersionOfSite();
            } catch (TimeoutException ex){
                return false;
            }
        }
    }




}
