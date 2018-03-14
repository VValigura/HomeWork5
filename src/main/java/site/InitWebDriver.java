package site;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class InitWebDriver {

    public static WebDriver initChromeDriver(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    public static WebDriver initFireFoxDriver(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    public static WebDriver initIEDriver(){
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        WebDriver ieDriver = new InternetExplorerDriver(capabilities); //добавил запрещенный метод очистки куки, так-как deleteAllCookies() всеравно оставал открытую сесию админа
        ieDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        ieDriver.manage().window().maximize();
        ieDriver.manage().deleteAllCookies(); //почемуто ИЕ сохраняет сессию с предыдущего запуска
        return ieDriver;
    }
}
