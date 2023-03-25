package com.prashanth.poc.mongodbpoc.dao;

import com.mongodb.client.result.UpdateResult;
import com.prashanth.poc.mongodbpoc.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean saveEmployee(Employee employee) {
        mongoTemplate.save(employee);
        return true;
    }

    public Employee findEmployee(String firstName, String lastName) {
        List<Employee> employeeList = mongoTemplate.find(Query.query(Criteria.where("firstName").is(firstName).and("lastName").is(lastName)), Employee.class);
        return employeeList.get(0);
    }

    public Employee updateEmployee(String firstName, String lastName, String newLastName) {
        Query query = Query.query(Criteria.where("firstName").is(firstName).and("lastName").is(lastName));
        Update update = Update.update("lastName", newLastName);
        Employee employee = mongoTemplate.findAndModify(query, update, Employee.class);
        return employee;
    }
}
