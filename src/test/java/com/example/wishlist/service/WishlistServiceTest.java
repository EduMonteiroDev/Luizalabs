package com.example.wishlist.service;

import com.example.wishlist.model.entity.WishlistEntity;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.service.build.WishlistEntityBuilder;
import com.example.wishlist.service.validation.WishlistValidator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private WishlistValidator wishlistValidator;

    @Mock
    private WishlistEntityBuilder wishlistEntityBuilder;
    @InjectMocks
    private WishlistService wishlistService;

    @ParameterizedTest
    @MethodSource("inputsParams")
    void addToWishlist_ExistingWishlist_AddsProduct(String customerId, String productId) {
        var wishlistEntity = WishlistEntity.builder().customerId(customerId).products(new ArrayList<>()).build();

        when(wishlistRepository.findById(customerId)).thenReturn(wishlistEntity);

        wishlistService.addToWishlist(customerId, productId);

        assertTrue(wishlistEntity.getProducts().contains(productId));
        verify(wishlistRepository).save(wishlistEntity);
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void addToWishlist_NonExistingWishlist_CreatesAndAddsProduct(String customerId, String productId) {

        when(wishlistRepository.findById(customerId)).thenReturn(null);
        when(wishlistEntityBuilder.build(customerId, productId)).thenReturn(WishlistEntity.builder().customerId(customerId).products(Arrays.asList(productId)).build());

        wishlistService.addToWishlist(customerId, productId);

        verify(wishlistEntityBuilder).build(customerId, productId);
        verify(wishlistRepository).save(any(WishlistEntity.class));
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void removeFromWishlist_ExistingWishlist_RemovesProduct(String customerId, String productId) {

        var products = new ArrayList<>(Arrays.asList(productId));
        var wishlistEntity = WishlistEntity.builder().customerId(customerId).products(products).build();
        when(wishlistRepository.findById(customerId)).thenReturn(wishlistEntity);

        wishlistService.removeFromWishlist(customerId, productId);

        assertFalse(wishlistEntity.getProducts().contains(productId));
        verify(wishlistRepository).save(wishlistEntity);
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void removeFromWishlist_NonExistingWishlist_DoesNotRemoveProduct(String customerId, String productId) {

        when(wishlistRepository.findById(customerId)).thenReturn(null);

        wishlistService.removeFromWishlist(customerId, productId);

        verify(wishlistRepository, never()).save(any(WishlistEntity.class));
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void getWishlist_ExistingWishlist_ReturnsProducts(String customerId) {
        var wishlistEntity = WishlistEntity.builder().customerId(customerId).products(Arrays.asList("TEST001","TEST002")).build();
        var products = Arrays.asList("TEST001", "TEST002");

        when(wishlistRepository.findById(customerId)).thenReturn(wishlistEntity);

        List<String> result = wishlistService.getWishlist(customerId);

        assertEquals(products, result);
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void getWishlist_NonExistingWishlist_ReturnsEmptyList(String customerId) {

        when(wishlistRepository.findById(customerId)).thenReturn(null);

        var result = wishlistService.getWishlist(customerId);

        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void isProductInWishlist_ExistingWishlistAndProductExists_ReturnsTrue(String customerId, String productId) {
        var wishlistEntity = WishlistEntity.builder().customerId(customerId).products(Arrays.asList(productId)).build();

        when(wishlistRepository.findById(customerId)).thenReturn(wishlistEntity);
        when(wishlistValidator.validateIfContainsProduct(wishlistEntity, productId)).thenReturn(Boolean.TRUE);

        var result = wishlistService.isProductInWishlist(customerId, productId);

        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void isProductInWishlist_ExistingWishlistAndProductDoesNotExist_ReturnsFalse(String customerId, String productId) {
        var wishlistEntity = WishlistEntity.builder().customerId(customerId).products(Arrays.asList()).build();

        when(wishlistRepository.findById(customerId)).thenReturn(wishlistEntity);
        when(wishlistValidator.validateIfContainsProduct(wishlistEntity, productId)).thenReturn(Boolean.FALSE);

        var result = wishlistService.isProductInWishlist(customerId, productId);

        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("inputsParams")
    void isProductInWishlist_NonExistingWishlist_ReturnsFalse(String customerId, String productId) {

        when(wishlistRepository.findById(customerId)).thenReturn(null);

        var result = wishlistService.isProductInWishlist(customerId, productId);

        assertFalse(result);
    }

    static Stream<Arguments> inputsParams() {
        return Stream.of(
                Arguments.of("123", "TEST001")
        );
    }
}
