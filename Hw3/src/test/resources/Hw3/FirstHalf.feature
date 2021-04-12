Feature: Exercise 1 of Homework 3

  Background:
    Given Opened homepage
    And Logged in as Roman - Jdi1234

  Scenario: assert browser titles
    Then Browser title is "Home Page"

  Scenario: assert username
    Then Username is "ROMAN IOVLEV"

  Scenario: check header
    Then There are 4 header labels
    And Header labels have proper text

  Scenario: check images
    Then There are 4 images

  Scenario: check images descriptions
    Then There are 4 descriptions

  Scenario: check main header text
    Then Main header is "EPAM FRAMEWORK WISHES…"
    And Text under header is "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."

  Scenario: check iframe
    Then There are 1 iframes
    And In iframe are 1 logo

  Scenario: check subheader
    Then Subheader is "JDI GITHUB"

  Scenario: check link
    Then Link is "https://github.com/epam/JDI"

  Scenario: check left section
    Then There are 1 left section

  Scenario: check footer
    Then There are 1 footer
