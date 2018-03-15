package site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage {
    private WebDriver driver;
    private By productName = By.cssSelector(".product-line-info a.label");
    private By productQuantity = By.cssSelector(".js-cart-line-product-quantity.form-control");
    private By productPrice = By.cssSelector(".product-price");
    private By orderingBtn = By.cssSelector("a.btn.btn-primary");

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName(){
        return driver.findElement(productName).getAttribute("textContent");
    }

    public int getProductQuantity(){
        return Integer.parseInt(driver.findElement(productQuantity).getAttribute("value").toString());
    }

    public String getProductPrice(){
        return driver.findElement(productPrice).getAttribute("outerText").toString().replace(" ₴", "");
    }

    public void clickOrderingBtn(){
        driver.findElement(orderingBtn).click();
    }
}
