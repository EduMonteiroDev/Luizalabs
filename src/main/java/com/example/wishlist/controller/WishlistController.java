package com.example.wishlist.controller;

import com.example.wishlist.model.request.ProductRequest;
import com.example.wishlist.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
@Tag(name = "Wishlist Controller", description = "Endpoints to manipulate wishlists. ")
@RequiredArgsConstructor
@Validated
public class WishlistController {

    private final WishlistService wishlistService;

    @Operation(summary = "Add item to wishlist",
            description = "Endpoint to add items in wishlist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    @PostMapping("/{customerId}")
    public ResponseEntity<Void> addToWishlist(@NotBlank @PathVariable String customerId, @Valid @RequestBody ProductRequest productRequest) {
        wishlistService.addToWishlist(customerId, productRequest.getProductId());
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Remove item from wishlist",
            description = "Endpoint to remove items from wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Object.class)))})
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> removeFromWishlist(@NotBlank @PathVariable String customerId,@Valid @RequestBody ProductRequest productRequest) {
        wishlistService.removeFromWishlist(customerId, productRequest.getProductId());
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Return wishlist items",
            description = "Endpoint to get all wishlist items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    @GetMapping("/{customerId}")
    public ResponseEntity<List<String>> getWishlist(@NotBlank @PathVariable String customerId) {
        return ResponseEntity.ok(wishlistService.getWishlist(customerId));
    }
    @Operation(summary = "Return validation in existence item from wishlist.",
            description = "Validate if item exists in wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    @GetMapping("/{customerId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@NotBlank @PathVariable String customerId,@NotBlank @PathVariable String productId) {
        return ResponseEntity.ok(wishlistService.isProductInWishlist(customerId, productId));
    }
}
