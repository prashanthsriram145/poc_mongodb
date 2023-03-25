package com.prashanth.poc.mongodbpoc.repository;

import com.prashanth.poc.mongodbpoc.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
