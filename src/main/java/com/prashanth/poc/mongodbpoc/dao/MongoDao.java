package com.prashanth.poc.mongodbpoc.dao;

import com.mongodb.client.result.UpdateResult;
import com.prashanth.poc.mongodbpoc.model.Employee;
import com.prashanth.poc.mongodbpoc.model.EmployeeCountBySalary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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

    public List<Employee> getEmployeeByAggregation(String firstName) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("firstName").is(firstName));
        ProjectionOperation projectionOperation = project("firstName", "lastName", "salary");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, projectionOperation);

        AggregationResults<Employee> result = mongoTemplate.aggregate(aggregation, "employee", Employee.class);
        return result.getMappedResults();
    }

    public List<EmployeeCountBySalary> getEmployeesCountBySalaryAggregation() {
        ProjectionOperation projectionOperation = project().andExpression("_id").as("salary")
                .andExpression("employeeCount").as("employeeCount");
        GroupOperation groupOperation = group("salary").count().as("employeeCount");
        SortOperation sortOperation = sort(Sort.by(Sort.Direction.DESC, "employeeCount"));
        Aggregation aggregation = Aggregation.newAggregation(groupOperation, sortOperation, projectionOperation);

        AggregationResults<EmployeeCountBySalary> result = mongoTemplate.aggregate(aggregation, "employee", EmployeeCountBySalary.class);
        return result.getMappedResults();
    }
}
