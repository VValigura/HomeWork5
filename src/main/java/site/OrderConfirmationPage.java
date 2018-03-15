package site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {
    private WebDriver driver;
    private By configurationTitle = By.cssSelector(".h1.card-title");
    private By productQuantity = By.cssSelector(".col-xs-2");
    private By productName = By.cssSelector(".col-sm-4.col-xs-9.details");
    private By productPrice = By.cssSelector(".col-xs-5.text-sm-right.text-xs-left");

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfigurationTitle(){
        return driver.findElement(configurationTitle).getAttribute("outerText").replace("\uE876", "");
    }

    public int getProductQuantity(){
        return Integer.parseInt(driver.findElement(productQuantity).getAttribute("textContent"));
    }

    public String getProductName(){
        return driver.findElement(productName).getAttribute("textContent");
    }

    public String getProductPrice(){
        return driver.findElement(productPrice).getAttribute("outerText").toString().replace(" ₴", "");
    }


}
