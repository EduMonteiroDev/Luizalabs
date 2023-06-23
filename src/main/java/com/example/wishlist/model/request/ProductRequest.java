package com.example.wishlist.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "O productId está vazio.")
    private String productId;
}
