Feature: Create a user from API

  Scenario: Create a user from API
    Given user hit the delete user api
    Then user get 204 as status code after user deleted
    Then user verify the user has been deleted