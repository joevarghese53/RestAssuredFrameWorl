Feature: End to End test
  Scenario: user can perform all crud operations
    Given user wants to call "/users" endpoint
    And set header "Content-Type" to "application/json"
    And set header "Authorization" to "Bearer bd7c611734d28a2568743aa903c3712dde8704e06c43a23ef0d1e885326af263"
    And set request body from file "create-user.json" using pojo
    When user performs post request
    Then verify status code is 201
    And store the "id" from response
    Given user wants to call "/users/7513178" endpoint
    When user performs get request
    Then verify status code is 200
    And  verify response has field "status" same as "active"
    Given user wants to call "/users" endpoint
    When user performs get request
    Then verify status code is 200
    Given user wants to call "/users/7513178" endpoint
    And set header "Content-Type" to "application/json"
    And set header "Authorization" to "Bearer bd7c611734d28a2568743aa903c3712dde8704e06c43a23ef0d1e885326af263"
    And set request body from file "update-user.json"
    When user performs put request
    Then verify status code is 200
    And  verify response has field "gender" same as "male"
    Given user wants to call "/users/7513177" endpoint
    And set header "Authorization" to "Bearer bd7c611734d28a2568743aa903c3712dde8704e06c43a23ef0d1e885326af263"
    When user performs delete request
    Then verify status code is 204
