@api-video
Feature: API video
  As user
  I want to test API for  contains and response

  Background: Base and Port Setup
    Given The baseURL "http://turing.niallbunting.com" and port "3001" up and running
    And  Set Request and Response Header content type to "ContentType.JSON"

  @api-video-get @api
  Scenario: GET Request
    When I execute "GET" request to the end point "/api/video"
    Then I should see response status code "200"
    And The response body should contain following details about the API
      | _id    | 596cbda86ed7c10011a68b24 |
      | artist | Lady Gaga                |
      | song   | Poker face               |

  @api-video-get-id @api
  Scenario: GET Request using ID
    When I execute "GET" request to the end point "/api/video/596cbda86ed7c10011a68b24"
    Then I should see response status code "200"
    And The response body should contain following details about the API
      | _id    | 596cbda86ed7c10011a68b24 |
      | artist | Lady Gaga                |
      | song   | Poker face               |

  @api-video-post @api
  Scenario: POST Request to add data
    When I specify the input data to be added to the API
      | Fields       | Values                   |
      | _id          | 596cbda86ed7c10011A77AB  |
      | artist       | Madona                   |
      | song         | The world                |
      | publishDate  | 2011-02-01T00:00:00.000Z |
      | __v          | 9                        |
      | date_created | 2016-07-17T14:03:29.649Z |
    Then I execute "POST" request to the end point "/api/video"
    Then I should see response status code "500"
    And The response body should contain following details about the API
      | _id    | 596cbda86ed7c10011A77AB |
      | artist | Madona                  |
      | song   | The world               |

  @api-video-put @api
  Scenario: PUT Request
    When I specify the input data to be added to the API
      | Fields       | Values                   |
      | _id          | 596cbda86ed7c10011A77AB  |
      | artist       | Kate Winslate            |
      | song         | The Titanic Song         |
      | publishDate  | 2011-02-01T00:00:00.000Z |
      | __v          | 9                        |
      | date_created | 2016-07-17T14:03:29.649Z |
    Then I execute "PUT" request to the end point "/api/video/596cbda86ed7c10011A77AB"
    Then I should see response status code "200"

  @api-video-delete @api
  Scenario: DELETE Request
    When I execute "DELETE" request to the end point "/api/video/596cbda86ed7c10011a68b24"
    Then I should see response status code "204"