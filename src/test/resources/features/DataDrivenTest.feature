Feature: Data Driven Test
  Scenario Outline: check which id are available on the database
    Given user wants to call "/users/<id>" endpoint
    When user performs get request
    Then verify status code is 200

    Examples:
    |   id        |
    |   7513176   |
    |   7513175   |
    |   1         |
    |   2         |
    |   7513174   |
    |   23        |
