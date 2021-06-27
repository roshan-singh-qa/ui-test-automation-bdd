@smoke
Feature: Sign-In

  Scenario: Verify login options
    Given user is present on home page
    When user go to sign-in page
    Then user will see sign option