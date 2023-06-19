package com.example.wishlist.repository;

import com.example.wishlist.exception.DatabaseException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static com.example.wishlist.constants.WishlistConstants.DATABASE_CONNECTION_EXCEPTION;

public class MongoDBRepository<T> implements CrudRepository<T>{
    private final MongoTemplate mongoTemplate;
    private final Class<T> entityClass;

    public MongoDBRepository(MongoTemplate mongoTemplate, Class<T> entityClass) {
        this.mongoTemplate = mongoTemplate;
        this.entityClass = entityClass;
    }
    @Override
    public void save(T entity) {
        try {
            mongoTemplate.save(entity);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }
    }

    @Override
    public T findById(String id) {
        try{
            return mongoTemplate.findById(id, entityClass);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }
    }

    @Override
    public List<T> findAll() {
        try{
            return mongoTemplate.findAll(entityClass);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }

    }

    @Override
    public void delete(T entity) {
        try {
            mongoTemplate.remove(entity);
        }catch (Exception e){
            throw new DatabaseException(DATABASE_CONNECTION_EXCEPTION);
        }
    }
}
