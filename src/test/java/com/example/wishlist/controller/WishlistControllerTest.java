package com.example.wishlist.controller;

import com.example.wishlist.model.request.ProductRequest;
import com.example.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistControllerTest {

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    @Test
    void addToWishlist_with_success() {
        var customerId = "123";
        var productDTO = ProductRequest.builder().productId("TEST001").build();
        var expectedResponse = ResponseEntity.ok().build();
        var response = wishlistController.addToWishlist(customerId, productDTO);

        assertEquals(expectedResponse, response);
        verify(wishlistService, times(1)).addToWishlist(customerId, productDTO.getProductId());
    }

    @Test
    void removeFromWishlist_with_success() {
        var customerId = "123";
        var productDTO = ProductRequest.builder().productId("TEST001").build();
        var expectedResponse = ResponseEntity.ok().build();
        var response = wishlistController.removeFromWishlist(customerId, productDTO);

        assertEquals(expectedResponse, response);
        verify(wishlistService, times(1)).removeFromWishlist(customerId, productDTO.getProductId());
    }

    @Test
    void getWishlist_with_success() {
        var customerId = "123";
        var wishlist = Arrays.asList("TEST001", "TEST002", "TEST003");
        var expectedResponse = ResponseEntity.ok(wishlist);
        when(wishlistService.getWishlist(customerId)).thenReturn(wishlist);
        var response = wishlistController.getWishlist(customerId);

        assertEquals(expectedResponse, response);
        assertEquals(wishlist, response.getBody());
        verify(wishlistService, times(1)).getWishlist(customerId);
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void isProductInWishlist_with_success(Boolean input1, String input2) {
        var customerId = "123";
        var expectedResponse = ResponseEntity.ok(input1);
        when(wishlistService.isProductInWishlist(customerId, input2)).thenReturn(input1);
        var response = wishlistController.isProductInWishlist(customerId, input2);

        assertEquals(expectedResponse, response);
        assertEquals(input1, response.getBody());
        verify(wishlistService, times(1)).isProductInWishlist(customerId, input2);
    }

    static Stream<Arguments> inputsParams() {
        return Stream.of(
                Arguments.of(Boolean.TRUE, "TEST001"),
                Arguments.of(Boolean.FALSE, "TEST002")
        );
    }
}
