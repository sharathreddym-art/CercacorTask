package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtilities;
import utilities.DriverManager;
import utilities.MobileUtilities;
import utilities.ReadProperty;

import java.time.Duration;

import static utilities.DriverManager.getAndroidDriver;

public class LoginPageObj {
    int productCount;

    public final static String xpathContains = "//tagname[contains(@text='%s')]";
    @FindBy(xpath = "//tagname[@text='username']")
    public MobileElement username;
    @FindBy(xpath = "//tagname[@text='password']")
    public MobileElement password;
    @FindBy(xpath = "//tagname[@text='signin']")
    public MobileElement signInBtn;
    @FindBy(xpath = "//tagname[@text='enable']")
    public MobileElement pushNotificationEnable;
    @FindBy(xpath = "//tagname[@text='disable']")
    public MobileElement pushNotificationDisable;
    @FindBy(xpath = "//tagname[@text='dairy']")
    public MobileElement dairyTab;
    @FindBy(xpath = "//tagname[@text='searchField']")
    public MobileElement searchField;
    @FindBy(xpath = "//tagname[@text='add']")
    public MobileElement addPlusBtn;
    @FindBy(xpath = "//tagname[contains(@testId, 'oatmeal']")
    public MobileElement oatmeal;
    @FindBy(id="caloriesValue")
    public MobileElement foodCaloriesValue;

    //Dairy tab
    @FindBy(xpath = "//tagname[@testId='navBack']")
    public MobileElement navigationBackBtn;

    //Dashboard PAge
    @FindBy(id="dashboard")
    public MobileElement dashboard;
    @FindBy(id="dashboardImage")
    public MobileElement dashboardImage;
    @FindBy (id= "totalCalories")
    public MobileElement totalCaloriesValue;
    @FindBy(id="netCalories")
    public MobileElement netCaloriesValue;
    @FindBy(id="goal")
    public MobileElement goalCaloriesValue;



    public LoginPageObj() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getAndroidDriver(), Duration.ofSeconds(10)), this);
    }

    public void pushNotification() {
        if (MobileUtilities.isElementPresent(pushNotificationDisable)) MobileUtilities.click(pushNotificationDisable);
    }

    public void login() {
        try {
            MobileUtilities.setText(username, CommonUtilities.decrypt(ReadProperty.getProperty("username")));
            MobileUtilities.setText(password, CommonUtilities.decrypt(ReadProperty.getProperty("password")));
            MobileUtilities.click(signInBtn);
        } catch (Exception e) {
            throw e;
        }
    }

    public void addFood(String mealType) {

        int addFoodCount = getAndroidDriver().findElements(By.id(mealType)).size();
        for (int i = 0; i < addFoodCount; i++)
        {
            String meal = By.id(mealType).findElements(getAndroidDriver()).get(i).getText();
            if (meal.equalsIgnoreCase(mealType))
            {
                By.id("Add_Food").findElements(getAndroidDriver()).get(i).click();
            }

        }
    }

    public void addMeal(String mealName) {
        MobileUtilities.setText(searchField, mealName);
        searchField.sendKeys(Keys.ENTER);
        productCount = getAndroidDriver().findElements(By.id(mealName)).size();

        for (int i = 0; i < productCount; i++)
        {
            String meal = By.id(mealName).findElements(getAndroidDriver()).get(i).getText();
            if (meal.equalsIgnoreCase(mealName))
            {
                By.id(mealName).findElements(getAndroidDriver()).get(i).click();
                foodCaloriesValue.getText();

            }

        }

    }
}
