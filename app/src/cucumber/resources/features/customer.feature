Feature: Customer

  Scenario Outline: Get customer successfully
    Given let variable "accountNumber" equal to "<accountNumber>"
    When the client performs GET request on "customers/{(accountNumber)}"
    Then status code is 200
    And response contains property "accountNumber" with value "<accountNumber>"
    And response contains property "firstName" with value "<firstName>"
    And response contains property "lastName" with value "<lastName>"

    Examples:
      | accountNumber | firstName | lastName |
      | 1111111111    | Joe       | Bloggs   |

  Scenario Outline: Get customer not found
    Given let variable "accountNumber" equal to "<accountNumber>"
    When the client performs GET request on "customers/{(accountNumber)}"
    Then status code is 404
    And response contains property "message" with value "customer with account number {(accountNumber)} not found"

    Examples:
      | accountNumber |
      | 9999999999    |