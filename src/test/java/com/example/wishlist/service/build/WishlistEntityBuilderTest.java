package com.example.wishlist.service.build;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WishlistEntityBuilderTest {
    @InjectMocks
    private WishlistEntityBuilder wishlistEntityBuilder;

    @Test
    void build_ReturnsWishlistEntityWithCustomerIdAndProductId() {
        var customerId = "123";
        var productId = "TEST001";

        var wishlistEntity = wishlistEntityBuilder.build(customerId, productId);

        assertEquals(customerId, wishlistEntity.getCustomerId());
        assertEquals(Arrays.asList(productId), wishlistEntity.getProducts());
    }
}
