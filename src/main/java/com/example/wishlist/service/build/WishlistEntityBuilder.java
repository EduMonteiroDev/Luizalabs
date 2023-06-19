package com.example.wishlist.service.build;

import com.example.wishlist.model.entity.WishlistEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class WishlistEntityBuilder {

    public WishlistEntity build(String customerId, String productId){
        return WishlistEntity.builder().customerId(customerId).products(Arrays.asList(productId)).build();
    }
}
