package site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderingPage {
    private WebDriver driver;
    private By firstNameField = By.cssSelector("input[name='firstname']");
    private By lastNameField = By.cssSelector("input[name='lastname']");
    private By emailField = By.cssSelector("input[name='email']");
    private By addressField = By.cssSelector("input[name='address1']");
    private By postCodeField = By.cssSelector("input[name='postcode']");
    private By cityField = By.cssSelector("input[name='city']");
    private By continuePersonalDataBtn = By.cssSelector(".tab-pane.active button[name='continue']");
    private By continueAdressDataBtn = By.cssSelector("button[name='confirm-addresses']");
    private By continueConfirmDeliveryOptionBtn = By.cssSelector("button[name='confirmDeliveryOption']");
    private By payCheckBtn = By.cssSelector("#payment-option-1");
    private By agreementCheckBox = By.cssSelector("#conditions-to-approve .ps-shown-by-js");
    private By finishOrderBtn = By.cssSelector("#payment-confirmation button");

    public OrderingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    public void inputEmail(String email){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickContinuePersonalDataBth(){
        driver.findElement(continuePersonalDataBtn).click();
    }

    public void inputAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }

    public void inputPostCode(String postCode){
        driver.findElement(postCodeField).sendKeys(postCode);
    }

    public void inputCity(String city){
        driver.findElement(cityField).sendKeys(city);
    }
    public void clickContinueAdressDataBtn(){
        driver.findElement(continueAdressDataBtn).click();
    }

    public void clickAgreementCheckBox(){
        driver.findElement(agreementCheckBox).click();
    }

    public void clickPayCheckBtn(){
        driver.findElement(payCheckBtn).click();
    }

    public void clickFinishOrderingBtn(){
        driver.findElement(finishOrderBtn).click();
    }



    public void inputPersonalDataAndContinue(String firstName, String lastName, String email){
        inputFirstName(firstName);
        inputLastName(lastName);
        inputEmail(email);
        clickContinuePersonalDataBth();
    }

    public void inputAddressDataAndContinue(String address, String postCode, String city){
        inputAddress(address);
        inputPostCode(postCode);
        inputCity(city);
        clickContinueAdressDataBtn();
    }

    public void clickContinueConfirmDeliveryOptionBtn(){
        driver.findElement(continueConfirmDeliveryOptionBtn).click();
    }

    public void choosePaymentWayAndFinishOrder(){
        clickPayCheckBtn();
        clickAgreementCheckBox();
        clickFinishOrderingBtn();
    }

}
