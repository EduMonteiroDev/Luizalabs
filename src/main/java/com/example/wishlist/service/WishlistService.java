package com.example.wishlist.service;

import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.service.build.WishlistEntityBuilder;
import com.example.wishlist.service.validation.WishlistValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistValidator wishlistValidator;
    private final WishlistEntityBuilder wishlistEntityBuilder;

    public void addToWishlist(String customerId, String productId) {
        var wishlistEntity = wishlistRepository.findById(customerId);
        if(nonNull(wishlistEntity)){
            wishlistValidator.validateAddProduct(wishlistEntity, productId);
            wishlistEntity.getProducts().add(productId);
        }else {
            wishlistEntity = wishlistEntityBuilder.build(customerId, productId);
        }

        wishlistRepository.save(wishlistEntity);
    }

    public void removeFromWishlist(String customerId, String productId) {
        var wishlistEntity = wishlistRepository.findById(customerId);
        if (nonNull(wishlistEntity)) {
            wishlistValidator.validateRemoveProduct(wishlistEntity, productId);
            wishlistEntity.getProducts().remove(productId);
            wishlistRepository.save(wishlistEntity);
        }
    }

    public List<String> getWishlist(String customerId) {
        var wishlistEntity = wishlistRepository.findById(customerId);
        if(isNull(wishlistEntity)){
            return List.of();
        }
        return wishlistEntity.getProducts();
    }

    public Boolean isProductInWishlist(String customerId, String productId){
        var wishlistEntity = wishlistRepository.findById(customerId);
        if(isNull(wishlistEntity)){
            return Boolean.FALSE;
        }
        return wishlistValidator.validateIfContainsProduct(wishlistEntity, productId);
    }
}
