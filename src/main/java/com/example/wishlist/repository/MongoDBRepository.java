package com.example.wishlist.repository;

import com.example.wishlist.exception.DatabaseException;
import com.example.wishlist.model.entity.WishlistEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.wishlist.constants.WishlistConstants.DATABASE_CONNECTION_EXCEPTION;

@Repository
public class MongoDBRepository implements WishlistRepository {
    private final MongoTemplate mongoTemplate;

    public MongoDBRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public void save(WishlistEntity entity) {
        try {
            mongoTemplate.save(entity);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }
    }

    @Override
    public WishlistEntity findById(String id) {
        try{
            return mongoTemplate.findById(id, WishlistEntity.class);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }
    }

    @Override
    public List<WishlistEntity> findAll() {
        try{
            return mongoTemplate.findAll(WishlistEntity.class);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }

    }

    @Override
    public void delete(WishlistEntity entity) {
        try {
            mongoTemplate.remove(entity);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }
    }
}
