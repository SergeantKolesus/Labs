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