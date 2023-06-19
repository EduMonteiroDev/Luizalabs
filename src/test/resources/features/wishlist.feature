Feature: Wishlist Management

  Background:
    Given a wishlist with customerId "123" with product "TEST001" and "TEST002"

  Scenario: Add product to wishlist
    When the user adds product "TEST010" to the wishlist
    Then the product "TEST001" should be added to the wishlist

  Scenario: Add duplicate product to wishlist
    When the user adds product "TEST001" to the wishlist
    Then an error should occur stating that the product already exists in the wishlist

  Scenario: Remove product from wishlist
    When the user removes product "TEST001" from the wishlist
    Then the product "TEST001" should be removed from the wishlist

  Scenario: Remove non-existing product from wishlist
    When the user removes product "TEST003" from the wishlist
    Then an error should occur stating that the product does not exist in the wishlist

  Scenario: Get wishlist of existing customer
    When the user requests the wishlist for customerId "123"
    Then the wishlist should contain products "TEST001" and "TEST002"

  Scenario: Get wishlist of non-existing customer
    When the user requests the wishlist for customerId "456"
    Then the wishlist should be empty

  Scenario: Check if product is in wishlist
    When the user checks if product "TEST001" is in the wishlist
    Then the response should be true

  Scenario: Check if product is not in wishlist
    When the user checks if product "TEST003" is in the wishlist
    Then the response should be false