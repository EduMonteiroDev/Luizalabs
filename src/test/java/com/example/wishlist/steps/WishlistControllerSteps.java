package com.example.wishlist.steps;

import com.example.wishlist.controller.WishlistController;
import com.example.wishlist.exception.WishlistException;
import com.example.wishlist.model.dto.ProductDTO;
import com.example.wishlist.model.entity.WishlistEntity;
import com.example.wishlist.repository.WishlistRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class WishlistControllerSteps {

    private final WishlistController wishlistController;

    private final WishlistRepository wishlistRepository;

    private String customerId;
    private ResponseEntity<Void> addResponse;
    private ResponseEntity<Boolean> checkResponse;
    private ResponseEntity<List<String>> wishlistResponse;

    @Given("a wishlist with customerId {string} with product {string} and {string}")
    public void createWishlist(String customerId, String product1, String product2) {
        this.customerId = customerId;
        var wishlistEntity = WishlistEntity.builder().customerId(customerId).products(Arrays.asList(product1, product2)).build();
        wishlistRepository.save(wishlistEntity);
    }

    @When("the user adds product {string} to the wishlist")
    public void addToWishlist(String productId) {
        try {
            addResponse = wishlistController.addToWishlist(customerId, ProductDTO.builder().productId(productId).build());
        } catch (WishlistException e) {
            addResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Then("the product {string} should be added to the wishlist")
    public void verifyProductAdded(String productId) {
        var wishlistEntity = wishlistRepository.findById(customerId);
        Assert.assertNotNull(wishlistEntity);
        Assert.assertTrue(wishlistEntity.getProducts().contains(productId));
    }

    @Then("an error should occur stating that the product already exists in the wishlist")
    public void verifyDuplicateProductError() {
        Assert.assertEquals(HttpStatus.BAD_REQUEST, addResponse.getStatusCode());
    }

    @When("the user removes product {string} from the wishlist")
    public void removeFromWishlist(String productId) {
        try {
            addResponse = wishlistController.removeFromWishlist(customerId, ProductDTO.builder().productId(productId).build());
        } catch (WishlistException e) {
            addResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Then("the product {string} should be removed from the wishlist")
    public void verifyProductRemoved(String productId) {
        var wishlistEntity = wishlistRepository.findById(customerId);
        Assert.assertNotNull(wishlistEntity);
        Assert.assertFalse(wishlistEntity.getProducts().contains(productId));
    }

    @Then("an error should occur stating that the product does not exist in the wishlist")
    public void verifyNonExistingProductError() {
        Assert.assertEquals(HttpStatus.BAD_REQUEST, addResponse.getStatusCode());
    }

    @When("the user requests the wishlist for customerId {string}")
    public void getWishlist(String customerId) {
        wishlistResponse = wishlistController.getWishlist(customerId);
    }

    @Then("the wishlist should contain products {string} and {string}")
    public void the_wishlist_should_contain_products_and(String string, String string2) {
        Assert.assertTrue(wishlistResponse.getBody().contains(string));
        Assert.assertTrue(wishlistResponse.getBody().contains(string2));
    }

    @Then("the wishlist should be empty")
    public void verifyWishlistEmpty() {
        var products = wishlistResponse.getBody();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.isEmpty());
    }

    @When("the user checks if product {string} is in the wishlist")
    public void checkProductInWishlist(String productId) {
        checkResponse = wishlistController.isProductInWishlist(customerId, productId);
    }

    @Then("the response should be true")
    public void verifyProductExistsInWishlist() {
        var exists = checkResponse.getBody();
        Assert.assertNotNull(exists);
        Assert.assertTrue(exists);
    }

    @Then("the response should be false")
    public void verifyProductNotExistsInWishlist() {
        var exists = checkResponse.getBody();
        Assert.assertNotNull(exists);
        Assert.assertFalse(exists);
    }
}