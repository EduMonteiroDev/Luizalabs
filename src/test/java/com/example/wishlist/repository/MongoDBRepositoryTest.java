package com.example.wishlist.repository;

import com.example.wishlist.exception.DatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MongoDBRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;

    private MongoDBRepository<TestEntity> repository;

    @BeforeEach
    void setup() {
        repository = new MongoDBRepository<>(mongoTemplate, TestEntity.class);
    }

    @Test
    void save_CallsMongoTemplateSave() {
        var entity = TestEntity.builder().build();

        repository.save(entity);

        verify(mongoTemplate, Mockito.times(1)).save(entity);
    }

    @Test
    void save_ThrowsDatabaseException_WhenMongoTemplateThrowsException() {
        var entity = TestEntity.builder().build();

        doThrow(RuntimeException.class).when(mongoTemplate).save(entity);

        assertThrows(DatabaseException.class, () -> repository.save(entity));
    }

    @Test
    void findById_CallsMongoTemplateFindById() {
        var id = "123";

        repository.findById(id);

        verify(mongoTemplate, Mockito.times(1)).findById(id, TestEntity.class);
    }

    @Test
    void findById_ThrowsDatabaseException_WhenMongoTemplateThrowsException() {
        var id = "123";
        doThrow(RuntimeException.class).when(mongoTemplate).findById(id, TestEntity.class);

        assertThrows(DatabaseException.class, () -> repository.findById(id));
    }

    @Test
    void findAll_CallsMongoTemplateFindAll() {
        repository.findAll();

        verify(mongoTemplate, Mockito.times(1)).findAll(TestEntity.class);
    }

    @Test
    void findAll_ThrowsDatabaseException_WhenMongoTemplateThrowsException() {
        doThrow(RuntimeException.class).when(mongoTemplate).findAll(TestEntity.class);

        assertThrows(DatabaseException.class, () -> repository.findAll());
    }

    @Test
    void delete_CallsMongoTemplateRemove() {
        var entity = TestEntity.builder().build();

        repository.delete(entity);

        verify(mongoTemplate, Mockito.times(1)).remove(entity);
    }

    @Test
    void delete_ThrowsDatabaseException_WhenMongoTemplateThrowsException() {
        var entity = TestEntity.builder().build();

        doThrow(RuntimeException.class).when(mongoTemplate).remove(entity);

        assertThrows(DatabaseException.class, () -> repository.delete(entity));
    }
}

