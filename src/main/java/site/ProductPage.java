package site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private By productName = By.cssSelector("h1[itemprop='name']");
    private By productPrice = By.cssSelector("[itemprop='price']");
    private By allProductQuantity = By.cssSelector(".product-quantities span");
    private By addToBasketBtn = By.cssSelector(".add-to-cart");
    private By orderingModalBtn = By.cssSelector(".modal-content .btn.btn-primary");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName(){
        return driver.findElement(productName).getAttribute("textContent");
    }

    public String getProductPrice(){
        return driver.findElement(productPrice).getAttribute("outerText").toString().replace(" ₴", "");
    }

    public Integer getAllProductsQuantity(){
        return Integer.parseInt(driver.findElement(allProductQuantity).getAttribute("textContent").toString().replace(" Товары", "").replace(" Товар", ""));
    }

    public void addProductToBasket(){
        driver.findElement(addToBasketBtn).click();
    }

    public void clickOrderingByModalBtn(){
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(orderingModalBtn))).click();
    }




}
