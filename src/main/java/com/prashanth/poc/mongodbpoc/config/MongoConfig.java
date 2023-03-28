package com.prashanth.poc.mongodbpoc.config;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig {

    @Value("${mongo.url}")
    private String mongoUrl;

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public MongoClient createMongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUrl);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).readConcern(ReadConcern.MAJORITY).
                writeConcern(WriteConcern.MAJORITY).readPreference(ReadPreference.secondary()).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(createMongoClient(), "sampledb");
        mongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
        mongoTemplate.setReadPreference(ReadPreference.secondary());
        return mongoTemplate;
    }
}
