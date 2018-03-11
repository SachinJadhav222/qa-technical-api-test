@apitest
  Feature: API Test
    As QA
    I want to test the API for valid response
  @api1
    Scenario: API test
    Given I specify the Base URL "http://restapi.demoqa.com/utilities/weather/city"
    When I specify request type  "Get" and set the parameter "London"
    Then I should see response code "200"
