Feature: Testing Application
    @smoke
    Scenario Outline: User Register on the website
    Given I "WaitForPageToBeLoad" "10" seconds
    When I "Click" on "XPATH" "TagWithText" with values "a~Login or register"
    And I "Click" on "XPATH" "TagWithAttribute" with values "input~value~register"
    And I "Click" on "XPATH" "ButtonWithText" with values "Continue"
    And I "EnterValue" "<firstname>" for "XPATH" "TagWithAttribute" with values "input~name~firstname"
    And I "EnterValue" "<lastname>" for "XPATH" "TagWithAttribute" with values "input~name~lastname"
    And I "EnterValue" "<email>" for "XPATH" "TagWithAttribute" with values "input~name~email"
    And I "EnterValue" "<address_1>" for "XPATH" "TagWithAttribute" with values "input~name~address_1"
    And I "Click" on "XPATH" "TagWithAttribute" with values "select~name~country_id"
    And I "Click" on "XPATH" "DropDownOption" with values "<country>"
    And I "Click" on "XPATH" "TagWithAttribute" with values "select~name~zone_id"
    And I "Click" on "XPATH" "DropDownOption" with values "<state>"
    And I "EnterValue" "<city>" for "XPATH" "TagWithAttribute" with values "input~name~city"
    And I "EnterValue" "<postcode>" for "XPATH" "TagWithAttribute" with values "input~name~postcode"
    And I "EnterValue" "<loginname>" for "XPATH" "TagWithAttribute" with values "input~name~loginname"
    And I "EnterValue" "<password>" for "XPATH" "TagWithAttribute" with values "input~name~password"
    And I "EnterValue" "<password>" for "XPATH" "TagWithAttribute" with values "input~name~confirm"
    And I "Click" on "XPATH" "TagWithAttribute" with values "input~value~0"
    And I "Click" on "XPATH" "TagWithAttribute" with values "input~name~agree"
    And I "Click" on "XPATH" "ButtonWithText" with values "Continue"
    Then I "VerifyVisibility" is "true" for "XPATH" "TagWithText" with values "span~<accountConfirmationText>"

    Examples:
        | firstname | lastname | email         | address_1 | country | state         | city  | postcode | loginname | password | accountConfirmationText       |
        | Shabbir   | Rayeen   | abc@gmail.com | ABC Nagar | India   | Uttar Pradesh | Konch | 285205   | Shab2893  | 123456   | Your Account Has Been Created |