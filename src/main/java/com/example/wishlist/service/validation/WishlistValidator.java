package com.example.wishlist.service.validation;

import com.example.wishlist.exception.WishlistException;
import com.example.wishlist.model.entity.WishlistEntity;
import org.springframework.stereotype.Component;

import static com.example.wishlist.constants.WishlistConstants.*;

@Component
public class WishlistValidator {

    public void validateAddProduct(WishlistEntity wishlist, String productId) {

        if (wishlist.getProducts().size() >= MAX_PRODUCTS) {
            throw new WishlistException(MAX_PRODUCTS_EXCEPTION);
        }
        if(validateIfContainsProduct(wishlist,productId)){
            throw new WishlistException(PRODUCT_EXISTS_EXCEPTION);
        }
    }

    public void validateRemoveProduct(WishlistEntity wishlist, String productId) {
        if(!validateIfContainsProduct(wishlist, productId)) {
            throw new WishlistException(PRODUCT_NOT_EXISTS_EXCEPTION);
        }
    }

    public Boolean validateIfContainsProduct(WishlistEntity wishlist, String productId){
        if(wishlist.getProducts().contains(productId)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
