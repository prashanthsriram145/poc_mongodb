package com.prashanth.poc.mongodbpoc.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig {

    @Value("${mongo.url}")
    private String mongoUrl;

    @Bean
    public MongoClient createMongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUrl);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(createMongoClient(), "sampledb");
    }
}
