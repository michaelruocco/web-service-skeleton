Feature: Create Customer

  Scenario: Create customer - success
    Given request body from file "json/post-customer.json"
    And content type is "application/json"
    When the client performs POST request on "/customers"
    Then status code is 201
    And header "Location" contains "/customers/22222222"