package com.example.wishlist.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WishlistConstants {
    public static final int MAX_PRODUCTS = 20;
    public static final String MAX_PRODUCTS_EXCEPTION = "A Wishlist já atingiu o limite máximo de produtos.";
    public static final String PRODUCT_EXISTS_EXCEPTION = "O produto já está presente na Wishlist.";
    public static final String PRODUCT_NOT_EXISTS_EXCEPTION = "O produto não está presente na Wishlist.";
    public static final String DATABASE_CONNECTION_EXCEPTION = "Erro no banco de dados. Por favor, tente novamente mais tarde.";
    public static final String INVALID_REQUEST_EXCEPTION = "Requisição inválida, verifique os dados da requisição.";
}
