package com.prashanth.poc.mongodbpoc.service;

import com.prashanth.poc.mongodbpoc.dao.MongoDao;
import com.prashanth.poc.mongodbpoc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoService {

    @Autowired
    private MongoDao mongoDao;

    public boolean saveEmployee(Employee employee) {
        return mongoDao.saveEmployee(employee);
    }

    public Employee findEmployee(String firstName, String lastName) {
        return mongoDao.findEmployee(firstName, lastName);
    }

    public Employee updateEmployee(String firstName, String lastName, String newLastName) {
        return mongoDao.updateEmployee(firstName, lastName, newLastName);
    }
}
