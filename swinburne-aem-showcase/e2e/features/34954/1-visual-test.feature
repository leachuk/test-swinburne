@applitools
Feature: Visual Test

  Automated Visual (Applitools) Tests

 
  @ORPHAN
  Scenario Outline: Visual Testing for Showcase pages

    Given that I am on the "<pageName>" page at "<pageUrl>"
    When I view the page visually
    Then the visual elements should not have changed
    
    Examples:
     |pageName      |pageUrl                                              |
     | Find a course | /content/swinburne-showcase/en/pages/find-a-course0.html?wcmmode=disabled |
     | JIE | /content/swinburne-showcase/en/pages/jie.html?wcmmode=disabled |
     | NIE | /content/swinburne-showcase/en/pages/nie.html?wcmmode=disabled |