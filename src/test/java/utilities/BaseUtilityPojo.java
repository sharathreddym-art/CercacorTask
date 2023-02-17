package utilities;



import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

public class BaseUtilityPojo {

    public static WebDriver driver;

    public static IOSDriver iosDriver;
    public AppiumDriver MDriver;

    public static  WebDriver getDriver ( ) {
        return driver;
    }

    public void setDriver ( WebDriver driver ) {
        this.driver = driver;
    }

    public static  WebDriver getMDriver ( ) {
        return iosDriver;
    }

    public void setMDriver ( MobileDriver driver ) {
        MDriver = MDriver;
    }

}
