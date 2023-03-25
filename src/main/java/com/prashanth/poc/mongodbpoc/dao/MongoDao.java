package com.prashanth.poc.mongodbpoc.dao;

import com.prashanth.poc.mongodbpoc.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean saveEmployee(Employee employee) {
        mongoTemplate.save(employee);
        return true;
    }
}
