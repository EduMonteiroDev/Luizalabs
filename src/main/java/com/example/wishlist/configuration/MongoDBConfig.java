package com.example.wishlist.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.wishlist.repository")
@RequiredArgsConstructor
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    private final WishlistServiceConfiguration config;
    @Override
    protected String getDatabaseName() {
        return config.databaseName();
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(config.databaseUri()));

        MongoCredential credential = MongoCredential.createCredential(
                config.databaseUsername(),
                getDatabaseName(),
                config.databasePassword().toCharArray()
        );
        builder.credential(credential);
    }
}