package com.example.wishlist.controller;

import com.example.wishlist.model.request.ProductRequest;
import com.example.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
@Validated
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Void> addToWishlist(@NotBlank @PathVariable String customerId, @Valid @RequestBody ProductRequest productRequest) {
        wishlistService.addToWishlist(customerId, productRequest.getProductId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> removeFromWishlist(@NotBlank @PathVariable String customerId,@Valid @RequestBody ProductRequest productRequest) {
        wishlistService.removeFromWishlist(customerId, productRequest.getProductId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<String>> getWishlist(@NotBlank @PathVariable String customerId) {
        return ResponseEntity.ok(wishlistService.getWishlist(customerId));
    }

    @GetMapping("/{customerId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@NotBlank @PathVariable String customerId,@NotBlank @PathVariable String productId) {
        return ResponseEntity.ok(wishlistService.isProductInWishlist(customerId, productId));
    }
}
