package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class DriverManager {
    private static WebDriver driver;
    private DesiredCapabilities capabilities;
    private static AppiumDriver appiumDriver;
    private static IOSDriver iosDriver;
    private static AndroidDriver androidDriver;

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static IOSDriver getIOSDriver() {
        return iosDriver;
    }

    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    public static final String browserStackUsername="sharathmandadi_bOcHvB";
    public static final String automateKey="EyjG6b1z9YbFcj95XKrp";
    public static final String url="https://"+browserStackUsername+ ":"+automateKey+ "@hub-cloud.browserstack.com/wd/hub";


    public static AppiumDriver getAppiumDriver() {
        return appiumDriver;
    }
    public void initializeDriver() {
        if (ReadProperty.getProperty("TypeOfExecution").equalsIgnoreCase("Web")) {
            String browser = ReadProperty.getProperty("browser");
            switch (browser) {
                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                case "FireFox":
                    WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "Edge":
                    WebDriverManager.edgedriver().setup();
                    driver= new EdgeDriver();
                    driver.manage().window().maximize();
                    break;
                case "Safari":
                    WebDriverManager.safaridriver().setup();
                    driver= new SafariDriver();
                    driver.manage().window().maximize();
                    break;

            }
        } else if (ReadProperty.getProperty("TypeOfExecution").equalsIgnoreCase("iOS")) {
                 capabilities = new DesiredCapabilities();
                 capabilities.setCapability("deviceName", "iPhone 13 Pro");
            capabilities.setCapability("platformName", "IOS");
            capabilities.setCapability("os_version", "15.5");
            capabilities.setCapability("app", "bs://7c5afa9dff75be0d15669d58f0ec7b81d61fa3da");
            try {
                iosDriver = new IOSDriver<>(new URL(url), capabilities);
                appiumDriver=iosDriver;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


//            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13 Pro");
//            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
//            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITEST");
//            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
//            capabilities.setCapability("bundleId", "com.fitnessPal");
//            capabilities.setCapability("udid", "111111111111111111111");
//            capabilities.setCapability("xcodeOrgId", "111111111111");
//            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3000");
//
//            try {
//                iosDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//                appiumDriver=iosDriver;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
        } else if (ReadProperty.getProperty("TypeOfExecution").equalsIgnoreCase("Android")) {
            capabilities = new DesiredCapabilities();
            String apkFile = System.getProperty("user.dir") + "/src/test/java/Input/" + ReadProperty.getProperty("APKFileName") + ".apk";
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.APP, apkFile);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.fitnessPal");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.MainActivity");
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3000");
            try {
                androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                appiumDriver=androidDriver;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }else
        {

        }


        }
    }

	
