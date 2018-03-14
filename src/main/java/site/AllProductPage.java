package site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllProductPage {

    private WebDriver driver;
    private By nextBtn = By.cssSelector(".next.js-search-link:not(.disabled)");

    public AllProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isGoodPresentByText(String partialLinkText){
        Boolean isGoodPresent = false;

        if (driver.findElements(nextBtn).size() == 0){
            if (driver.findElements(By.xpath("//*[contains(text(), '" + partialLinkText + "')]")).size() > 0){
                isGoodPresent = true;
            }
        } else {
            while (driver.findElements(nextBtn).size() > 0){
                if (driver.findElements(By.xpath("//*[contains(text(), '" + partialLinkText + "')]")).size() > 0){
                    isGoodPresent = true;
                    break;
                }
                driver.findElement(nextBtn).click();
            }
        }
        return isGoodPresent;
    }

    public void clickOnProduct(String partialLinkText){
        driver.findElement(By.xpath("//*[contains(text(), '" + partialLinkText + "')]")).click();
    }
}
