Feature: Verify the total calories updated meal is added

  Background:
    Given User login to the fitness pal app
@Login
  Scenario Outline: User adds breakfast meal and verifies remaining calories
    And User click on the "dairy" tab
    And User click on the "Breakfast" Add food button
    Then User enter "<BreakfastMeal>" in the search bar
    And User click on the "Lunch" Add food button
    Then User enter "<LunchMeal>" in the search bar
    And User click on the "Dinner" Add food button
    Then User enter "<DinnerMeal>" in the search bar
    Then User verifies the remaining calories from goal calories "3000"
    Examples:
      | BreakfastMeal | LunchMeal     | DinnerMeal  |
      | chicken    | ChickenBreast | SaladGreens |


