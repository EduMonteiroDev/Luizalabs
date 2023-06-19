package com.example.wishlist.controller;

import com.example.wishlist.model.dto.ProductDTO;
import com.example.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/{customerId}/add")
    public ResponseEntity<Void> addToWishlist(@PathVariable String customerId, @RequestBody ProductDTO productDTO) {
        wishlistService.addToWishlist(customerId, productDTO.getProductId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{customerId}/remove")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable String customerId, @RequestBody ProductDTO productDTO) {
        wishlistService.removeFromWishlist(customerId, productDTO.getProductId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<String>> getWishlist(@PathVariable String customerId) {
        return ResponseEntity.ok(wishlistService.getWishlist(customerId));
    }

    @GetMapping("/{customerId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable String customerId, @PathVariable String productId) {
        return ResponseEntity.ok(wishlistService.isProductInWishlist(customerId, productId));
    }
}
