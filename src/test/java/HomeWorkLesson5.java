import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import site.*;

import java.util.Date;

public class HomeWorkLesson5 {
//    https://bitbucket.org/qatestlab_automation/lecture-5
//
//    Автоматизировать следующий сценарий:
//Часть А. Проверка открываемой версии магазина:
//
//+            1. Перейти на главную страницу магазина.
//+            2. Удостовериться, что пользователю отображается корректная версия сайта (обычная версия сайта с использованием настольного браузера, мобильная версия сайта с
//+              спользованием мобильного браузера.)
//
//Часть Б. Оформление заказа в магазине:
//+             1. Перейти на главную страницу магазина.
//+            2. Перейти к списку всех товаров воспользовавшись ссылкой «Все товары»
//+            3. Открыть случайный товар из отображаемого списка.
//+            4. Добавить товар в корзину.
//+            5. В сплывающем окне нажать на кнопку «Перейти к оформлению» для перехода в корзину пользователя.
//+            6. Проверить, что в корзине отображается одна позиция, название и цена добавленного товара соответствует значениям, которые отображались на странице товара.
//+            7. Нажать на кнопку «Оформление заказа».
//+            8. Заполнить поля Имя, Фамилия, E-mail (должно быть уникальным) и перейти к следующему шагу оформления заказа.
//+            9. Указать адрес, почтовый индекс, город доставки. Перейти к следующему шагу.
//+            10. Оставить настройки доставки без изменений, перейти к шагу оплаты заказа.
//+            11. Выбрать любой способ оплаты. Отметить опцию «Я ознакомлен(а) и согласен(на) с Условиями обслуживания.» Оформить заказ.
//+            12. В открывшемся окне подтверждения заказа проверить следующие значения:
//+            - Пользователю отображается сообщение «Ваш заказ подтвержден»
//+            - В деталях заказа отображается одна позиция, название и цена товара соответствует значениям, которые отображались на странице товара.
//+            13. Вернуться на страницу товара и проверить изменения количества товара в наличии (в блоке справа, на вкладке «Подробнее о товаре»): количество товара должно
//+              уменьшиться на единицу.
//
//    Настройте выполнение тестового скрипта таким образом, чтобы при вызове выполнения тестов (mvn test) он выполнился на разных браузерах: Chrome, Firefox, Internet Explorer,
//      на мобильном устройстве Android. Для этого можно в testng.xml воспользоваться возможностью передачи параметров.
//
//    Проект должен быть разработан таким образом, чтобы имелась возможность выполнять скрипт используя Selenium Grid. Для настройки тестового грида и проверки работы проекта
//      используйте инструкции по настройке и запуску из лекции (запуск и настройка хаба, нод, подключение ноды с мобильным эмулятором).
//
//    Примечания:
//    А. Логику сценария можно разбить на пару методов @Test.
//    Б. Описывая локаторы обращайте внимание на то, что дизайн мобильной версии сайта отличается от обычного; подбирайте наиболее подходящие локаторы. Для поиска локаторов
//      в мобильной версии сайта можно воспользоваться возможностями панели разработчика браузера. Например, в браузере Chrome откройте Инструменты разработчика и активируйте
//      эмуляцию мобильного устройства.
//    В. Некоторые сайты встраивают JS скрипты, которые срабатывают некорректно в процессе выполнения автоматизированного скрипта. В таких случаях необходимо использовать
//      альтернативные способы взаимодействия с элементами интерфейса. К примеру, в предложенном сценарии для автоматизации может некорректно срабатывать очистка полей Количество
//      и Цена на странице редактирования товара. Вместо использования стандартного метода Selenium API clear() для очистки текстового поля можно посылать введение символа
//      Backspace (org.openqa.selenium.Keys.BACK_SPACE) используя метод sendKeys().
private WebDriver driver;
private BrowserVersion browserVersion;

    @BeforeMethod
    @Parameters("browser")
    public void createDriver(@Optional("Chrome") String browser){
        WebDriver webDriver = null;
        if (browser.equals("Chrome")){
            webDriver = InitWebDriver.initChromeDriver();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("FireFox")){
            webDriver = InitWebDriver.initFireFoxDriver();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("IE")){
            webDriver = InitWebDriver.initIEDriver();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("remoteChrome")){
            webDriver = InitWebDriver.initRemoteChrome();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("remoteIE")){
            webDriver = InitWebDriver.initRemoteIE();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("remoteFireFox")){
            webDriver = InitWebDriver.initRemoteFireFoxDriver();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("android")){
            webDriver =  InitWebDriver.initRemoteAndroid();
            browserVersion = browserVersion.MOBILE_VERSION;
        }
        driver = new EventFiringWebDriver(webDriver).register(new WebDriverLogger());
    }

    @Test
    public void CheckCorrectBrowserVersionOpened(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        Assert.assertTrue(mainPage.isCorrectBrowserVersionOpened(browserVersion), "Incorrect version of the browser is open!");
    }

    @Test
    public void makeOrder(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickAllProductLink();
        new AllProductPage(driver).openRandomProduct();


        ProductPage productPage = new ProductPage(driver);
        String productUrl = driver.getCurrentUrl();
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        Integer productsQuantity = productPage.getAllProductsQuantity();

        productPage.addProductToBasket();
        productPage.clickOrderingByModalBtn();

        BasketPage basketPage = new BasketPage(driver);
        Assert.assertEquals(basketPage.getProductName(), productName, "Incorrect product name in basket page!");
        Assert.assertEquals(basketPage.getProductPrice(), productPrice, "Incorrect product price in basket page!");
        Assert.assertEquals(basketPage.getProductQuantity(), 1, "Incorrect product quantity in basket page!!");
        basketPage.clickOrderingBtn();

        OrderingPage orderingPage = new OrderingPage(driver);
        orderingPage.inputPersonalDataAndContinue("FirstName", "SecondName", new Date().getTime()+"@test.com");
        orderingPage.inputAddressDataAndContinue("Address", "12345", "City");
        orderingPage.clickContinueConfirmDeliveryOptionBtn();
        orderingPage.choosePaymentWayAndFinishOrder();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        Assert.assertTrue(orderConfirmationPage.getConfigurationTitle().equalsIgnoreCase("Ваш заказ подтверждён"), "Incorrect product title in order confirmation page!");
        Assert.assertTrue(orderConfirmationPage.getProductName().contains(productName), "Incorrect name title in order confirmation page!");
        Assert.assertTrue(orderConfirmationPage.getProductPrice().contains(productPrice), "Incorrect price in order confirmation page!");
        Assert.assertEquals(orderConfirmationPage.getProductQuantity(), 1, "Incorrect quantity in order confirmation page!");

        driver.get(productUrl);
        Assert.assertEquals(productPage.getAllProductsQuantity(), (productsQuantity - 1), "Incorrect quantity in product page after selling product!");

    }

    @AfterMethod
    public void closeDriver(){
        driver.quit();
    }
}
