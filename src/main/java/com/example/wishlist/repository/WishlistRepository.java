package com.example.wishlist.repository;

import com.example.wishlist.model.entity.WishlistEntity;

import java.util.List;

public interface WishlistRepository{
    void save(WishlistEntity entity);
    WishlistEntity findById(String id);
    List<WishlistEntity> findAll();
    void delete(WishlistEntity entity);
}
