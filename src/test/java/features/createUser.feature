Feature: Create a user from API

  Scenario: Create a user from API
    Given user hit the post user api
    Then user get 201 as status code after user created
    Then user verify the user has been created