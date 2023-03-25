package com.prashanth.poc.mongodbpoc;

import com.prashanth.poc.mongodbpoc.config.MongoConfig;
import com.prashanth.poc.mongodbpoc.controller.MongoController;
import com.prashanth.poc.mongodbpoc.dao.MongoDao;
import com.prashanth.poc.mongodbpoc.service.MongoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = "com.prashanth.mongodbpoc")
//@ComponentScan(basePackageClasses = {MongoController.class, MongoService.class, MongoDao.class, MongoConfig.class})
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.prashanth.poc.mongodbpoc.repository", mongoTemplateRef = "mongoTemplate")
public class MongodbpocApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MongodbpocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
