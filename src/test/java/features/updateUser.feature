Feature: Update a user from API

  Scenario: Update a user from API
    Given user hit the put user api
    Then user get 200 as status code after user updated
    Then user verify the user has been updated