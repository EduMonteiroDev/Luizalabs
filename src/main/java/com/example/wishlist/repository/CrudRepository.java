package com.example.wishlist.repository;

import java.util.List;

public interface CrudRepository<T> {
    void save(T entity);
    T findById(String id);
    List<T> findAll();
    void delete(T entity);
}
