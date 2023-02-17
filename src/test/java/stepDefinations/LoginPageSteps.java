package stepDefinations;

import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pageObject.LoginPageObj;
import utilities.DriverManager;
import utilities.MobileUtilities;
import static pageObject.LoginPageObj.xpathContains;
import static utilities.DriverManager.*;


public class LoginPageSteps {
    static int foodCaloriesVal;
    static int TotalCalories;
    int productCount;
public LoginPageObj loginPageObj;
public LoginPageSteps(){

    loginPageObj = new LoginPageObj();
}

    @Given("^User login to the fitness pal app$")
    public void user_login_to_the_fitness_pal_app()  {
    loginPageObj.pushNotification();
    loginPageObj.login();
        MobileUtilities.pause(1);
    }
    @And("^User click on the \"([^\"]*)\" tab$")
    public void user_click_on_the_something_tab(String tabName)  {
        MobileUtilities.click(MobileUtilities.findElement(String.format(xpathContains, tabName)));
    }
    @And("^User click on the \"([^\"]*)\" Add food button$")
    public void user_click_on_the_something_add_food_button(String mealType)  {
    loginPageObj.addFood(mealType);
    }

    @Then("^User enter \"([^\"]*)\" in the search bar$")
    public void user_enter_something_in_the_search_bar(String mealName)  {
        MobileUtilities.setText(loginPageObj.searchField, mealName);
        loginPageObj.searchField.sendKeys(Keys.ENTER);
       productCount = getAndroidDriver().findElements(By.id(mealName)).size();

        for (int i = 0; i < productCount; i++)
        {
            String meal = By.id(mealName).findElements(getAndroidDriver()).get(i).getText();
            if (meal.equalsIgnoreCase(mealName))
            {
                By.id(mealName).findElements(getAndroidDriver()).get(i).click();
                foodCaloriesVal= Integer.parseInt(loginPageObj.foodCaloriesValue.getText());
                TotalCalories=TotalCalories+foodCaloriesVal;

            }

        }
}

    @Then("^User verifies the remaining calories from goal calories \"([^\"]*)\"$")
    public void user_verifies_the_remaining_calories_from_goal_calories_something(String goalCalories) {
        MobileUtilities.click(loginPageObj.dashboard);
        MobileUtilities.click(loginPageObj.dashboardImage);
        int remainingCalories= Integer.parseInt(loginPageObj.totalCaloriesValue.getText());
        Assert.assertEquals(remainingCalories, TotalCalories);
    }


}
