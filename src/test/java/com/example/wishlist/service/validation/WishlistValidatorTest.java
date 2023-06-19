package com.example.wishlist.service.validation;

import com.example.wishlist.exception.WishlistException;
import com.example.wishlist.model.entity.WishlistEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WishlistValidatorTest {

    @InjectMocks
    private WishlistValidator wishlistValidator;

    @Test
    public void execute_validateAddProduct_without_exception() {
        var productId = "TEST001";
        var wishlist = WishlistEntity.builder().products(Arrays.asList()).build();

        assertDoesNotThrow(()-> wishlistValidator.validateAddProduct(wishlist, productId));
    }

    @Test
    public void execute_validateAddProduct_and_throw_MAX_PRODUCTS_EXCEPTION(){
        var productId = "TEST001";
        var products = Stream.generate(UUID::randomUUID)
                .limit(20)
                .map(UUID::toString)
                .collect(Collectors.toList());
        var wishlist = WishlistEntity.builder().products(products).build();
        assertThrows(WishlistException.class, ()-> wishlistValidator.validateAddProduct(wishlist, productId));
    }

    @Test
    public void execute_validateAddProduct_and_throw_PRODUCT_EXISTS_EXCEPTION(){
        var productId = "TEST001";
        var wishlist = WishlistEntity.builder().products(Arrays.asList(productId)).build();
        assertThrows(WishlistException.class, ()-> wishlistValidator.validateAddProduct(wishlist, productId));
    }


    @Test
    public void execute_validateRemoveProduct_without_exception() {
        var productId = "TEST001";
        var wishlist = WishlistEntity.builder().products(Arrays.asList(productId)).build();

        assertDoesNotThrow(()-> wishlistValidator.validateRemoveProduct(wishlist, productId));
    }

    @Test
    public void execute_validateRemoveProduct_and_throw_PRODUCT_NOT_EXISTS_EXCEPTION() {
        var productId = "TEST002";
        var wishlist = WishlistEntity.builder().products(Arrays.asList("TEST001")).build();

        assertThrows(WishlistException.class, ()-> wishlistValidator.validateRemoveProduct(wishlist, productId));
    }


    @Test
    public void execute_validateIfContainsProduct_and_return_true() {
        var productId = "TEST001";
        var wishlist = WishlistEntity.builder().products(Arrays.asList(productId)).build();

        boolean result = wishlistValidator.validateIfContainsProduct(wishlist, productId);

        assertTrue(result);
    }

    @Test
    public void execute_validateIfContainsProduct_and_return_false() {
        var productId = "TEST002";
        var wishlist = WishlistEntity.builder().products(Arrays.asList("TEST001")).build();

        boolean result = wishlistValidator.validateIfContainsProduct(wishlist, productId);

        assertFalse(result);
    }
}
