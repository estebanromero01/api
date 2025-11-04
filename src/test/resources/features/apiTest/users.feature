@user
Feature: API management fot users
  @get @success
  Scenario: get all users
    Given i want to get all users
    When I send request to get all users
    Then I should see the response with status 200
    And  the response body should be match with the schema "apiTest/all_user.json"
  @post @success
  Scenario: add a new user
    Given i want to add a user with id 11 username "messi" email "messi@gmail.com" password "1234"
    When I send request to add user
    Then I should see the response with status 201
    And  the response body should be match with the schema "apiTest/user.json"
    And the property "id" must be equal to 11
    And the property "username" must be equal to "messi"
    And the property "email" must be equal to "messi@gmail.com"
    And the property "password" must be equal to "1234"
  @put @success
  Scenario: Update a user
    Given i want tp update a user with id 12 username "ronaldo" email "cr7@gmail.com" password "4321"
    When I send request to update a user
    Then I should see the response with status 200
    And the response body should be match with the schema "apiTest/user.json"
    And the property "id" must be equal to 12
    And the property "username" must be equal to "ronaldo"
    And the property "email" must be equal to "cr7@gmail.com"
    And the property "password" must be equal to "4321"
  @delete @success
    Scenario: Delete a user
      Given i want to delete a user with id 11
      When I send request to delete a user
      Then I should see the response with status 200
  @delete @failed
    Scenario: Delete a user with invalid id
      Given i want to delete a user with id "ABC"
      When I send request to delete a user
      Then I should see the response with status 400
      And the property "status" must be equal to "error"
      And the property "message" must be equal to "user id should be provided"
  @get @failed
    Scenario: Get a user with invalid path
      Given i want to get a user with a invalid path in url
      When I send request to get all users
      Then I should see the response with status 404