package site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllProductPage {

    private WebDriver driver;
    private By allProducts = By.cssSelector(".h3.product-title");

    public AllProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRandomProduct(){
        List<WebElement> products =  driver.findElements(allProducts);
        products.get((int) (products.size()* Math.random())).click();
    }


}
