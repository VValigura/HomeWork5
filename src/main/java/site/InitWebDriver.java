package site;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class InitWebDriver {

    public static WebDriver initChromeDriver(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        //chromeDriver.manage().window().setSize(new Dimension(400,400) );
        return chromeDriver;
    }

    public static WebDriver initFireFoxDriver(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    public static WebDriver initIEDriver(){
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        WebDriver ieDriver = new InternetExplorerDriver(capabilities); //добавил запрещенный метод очистки куки, так-как deleteAllCookies() всеравно оставал открытую сесию админа
        ieDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        ieDriver.manage().window().maximize();
        ieDriver.manage().deleteAllCookies(); //почемуто ИЕ сохраняет сессию с предыдущего запуска
        return ieDriver;
    }

    public static WebDriver initRemoteChrome() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        System.out.println( System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        WebDriver chromeDriver = null;
        try {
            chromeDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        chromeDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        //chromeDriver.manage().window().setSize(new Dimension(400,400) );
        return chromeDriver;

    }

    public static WebDriver initRemoteIE() {
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        WebDriver ieDriver = null; //добавил запрещенный метод очистки куки, так-как deleteAllCookies() всеравно оставал открытую сесию админа
        try {
            ieDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ieDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        ieDriver.manage().window().maximize();
        ieDriver.manage().deleteAllCookies(); //почемуто ИЕ сохраняет сессию с предыдущего запуска
        return ieDriver;
    }


    public static WebDriver initRemoteFireFoxDriver(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        WebDriver firefoxDriver = null;
        try {
            firefoxDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        firefoxDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }


    public static WebDriver initRemoteAndroid(){
        DesiredCapabilities capability = DesiredCapabilities.android();
        capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");
        capability.setCapability(MobileCapabilityType.PLATFORM, "Android");
        capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capability.setCapability(MobileCapabilityType.DEVICE_NAME,"81HEBM6245N7");
        capability.setCapability(MobileCapabilityType.VERSION, "5.0.1");
        WebDriver androidDriver = null;
        try {
            androidDriver = new AndroidDriver(new URL("http://localhost:4444/wd/hub"), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        androidDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        androidDriver.manage().window().maximize();
        return androidDriver;
    }
}
