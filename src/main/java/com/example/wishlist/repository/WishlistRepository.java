package com.example.wishlist.repository;

import com.example.wishlist.model.entity.WishlistEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository extends MongoDBRepository<WishlistEntity> {

    public WishlistRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate, WishlistEntity.class);
    }

}
