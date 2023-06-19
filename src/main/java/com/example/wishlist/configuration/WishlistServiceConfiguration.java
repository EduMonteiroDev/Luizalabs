package com.example.wishlist.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishlistServiceConfiguration {

    private final Environment environment;
    public String databaseUri(){
        return environment.getProperty("spring.data.mongodb.uri");
    }
    public String databaseName(){
        return environment.getProperty("spring.data.mongodb.database");
    }
    public String databaseUsername(){
        return environment.getProperty("mongodb.username");
    }
    public String databasePassword(){
        return environment.getProperty("mongodb.password");
    }
}
