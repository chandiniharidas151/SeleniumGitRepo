Feature: Login Functionality

  Scenario Outline: Login to Practice Website
    Given User navigates to Practice Website
    When User Logs in with User name "<userName>" and password "<password>"
    Then User is redirected to a successful login page

    Examples:
      | userName | password      |
      | student  | Password123   |

  Scenario Outline: Negative User Name Test
    Given User navigates to Practice Website
    When User Logs in with User name "<userName>" and password "<password>"
    Then Error message is displayed "<errorMessage>"

    Examples:
      | userName       | password      | errorMessage              |
      | studentInvalid | Password123   | Your username is invalid! |
      | student1234    | Password123   | Your username is invalid! |
      | student        | Password123456 | Your password is invalid! |
      | student        | Password123abc | Your password is invalid! |

