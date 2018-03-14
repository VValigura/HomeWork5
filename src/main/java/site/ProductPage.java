package site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;
    private By productName = By.cssSelector("h1[itemprop='name']");
    private By productPrice = By.cssSelector("[itemprop='price']");
    private By productQuantity = By.cssSelector(".product-quantities span");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName(){
        return driver.findElement(productName).getAttribute("textContent");
    }

    public String getProductPrice(){
        return driver.findElement(productPrice).getAttribute("textContent").toString().replace(" ₴", "");
    }

    public String getProductQuantity(){
        return driver.findElement(productQuantity).getAttribute("textContent").toString().replace(" Товары", "");
    }




}
