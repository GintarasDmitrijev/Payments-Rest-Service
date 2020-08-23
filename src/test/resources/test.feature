
Feature: Payments display and cancellation

  Scenario: client makes call to GET /payments
    When the client calls /payments
    Then the client receives status code of 200

  Scenario: client makes call to GET /payments/1
    When the client calls /payments/1
    Then the client receives status code of 200

  Scenario: client makes call to GET /payments/4/cancel
    When the client calls /payments/4/cancel
    Then the client receives status code of 200


