package utilities;



import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MobileUtilities {

    static AppiumDriver<MobileElement> MDriver = (AppiumDriver<MobileElement>) DriverManager.getAppiumDriver();
    static Logger logger = Logger.getLogger(MobileUtilities.class);
    static WebDriverWait wait = new WebDriverWait(MDriver, 60);
    static TouchAction touchAction = new TouchAction(MDriver);

    static Actions actions = new Actions(MDriver);
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().resourceIdMatches(\".*part_id.*\").text(\"exact_text\"))")
    MobileElement element;

    public static void implicitWait(long timer) {
        MDriver.manage().timeouts().implicitlyWait(timer, TimeUnit.SECONDS);
    }


    public static void click(MobileElement element) {
        try {
            pause(1);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (Exception e) {
            logger.error("Failed to perform click operation Because Exception : " + e.getMessage());
            throw e;
        }
    }

    public static void clickAfter(MobileElement element) {
        try {
            pause(1);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            logger.error("Failed to perform click operation Because Exception : " + e.getMessage());
            throw e;
        }
    }

    public static void setText(MobileElement element, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void scrollToElement() {
        try {
            DriverManager.getAndroidDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setAsVerticalList().flingToEnd(10)"));
            pause(1);
        } catch (Exception e) {
        }
    }

    public static void scroll(MobileElement element) {
        try {
            JavascriptExecutor js = MDriver;
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("direction", "down");
            scrollObject.put("element", element.getId());

            js.executeScript("mobile: scroll", scrollObject);
            js.executeScript("mobile: scroll", scrollObject);
            js.executeScript("mobile: scroll", scrollObject);

        } catch (Exception e) {
            throw e;
        }
    }


    public static void selectByText(List<MobileElement> elements, String text) {
        List<MobileElement> list = elements;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equalsIgnoreCase(text)) {
                list.get(i).click();
                break;
            }
        }
    }

    public static boolean isElementPresent(MobileElement element) {
        boolean elementFlag = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            if (element.isDisplayed()) {
                elementFlag = true;
            }

        } catch (Exception e) {
        }
        return elementFlag;
    }

    public static void tapOnCoordinates(int xCord, int yCord) {
        try {
            touchAction.tap(PointOption.point((xCord), yCord)).perform();
        } catch (Exception e) {
        }
    }

    public static void pause(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void touchOnElement(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            touchAction.tap(tapOptions().withElement(ElementOption.element(element)));
        } catch (Exception e) {

        }
    }


    public static MobileElement findElement(String xpath) {
        MobileElement element = null;
        try {
            MDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            element = MDriver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            MobileUtilities.scrollToElement();
            MDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            element = MDriver.findElement(By.xpath(xpath));
        }
        return element;
    }

    public static String getText(MobileElement element) {
        String text = "";
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            text = element.getText();
        } catch (Exception e) {

        }
        return text;
    }


}
