@crud
Feature: API for test

  @create
  Scenario: Create a post
    Given I want create a post with title "Foo" and body "Bar" for user with ID 1
    When I send request to create the post
    Then I should see the response with status 201
    And the response body should be match with the schema "apiTest/post.json"

  @read
  Scenario: Read a post
    Given I want read a post of user with ID 1
    When I send request to read the post
    Then I should see the response with status 200
    And the response body should be match with the schema "apiTest/allPosts.json"
    And the property "id" has the item 1

  @update
  Scenario: Update a post
    Given I want update the title of post with id 1 to "Oof"
    When I send request to update the post
    Then I should see the response with status 200
    And the response body should be match with the schema "apiTest/post.json"
    And the property "title" must be equal to "Oof"

  @delete
  Scenario: Delete a post
    Given I want delete a post with id 1
    When I send request to delete the post
    Then I should see the response with status 200
