Feature: Getting user from API

  Scenario: Get user list from API
    Given user hit the get user api
    Then user get 200 as status code
    Then user verify the result from response