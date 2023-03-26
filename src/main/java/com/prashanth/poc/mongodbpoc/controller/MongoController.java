package com.prashanth.poc.mongodbpoc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashanth.poc.mongodbpoc.model.Employee;
import com.prashanth.poc.mongodbpoc.model.EmployeeCountBySalary;
import com.prashanth.poc.mongodbpoc.service.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MongoController {

    @Autowired
    private MongoService mongoService;

    @PostMapping(value = "/saveEmployee",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void saveEmployee(@RequestBody Employee employee) {
        mongoService.saveEmployee(employee);
    }

    @GetMapping(value = "/findEmployee")
    public ResponseEntity<Employee> findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = mongoService.findEmployee(firstName, lastName);
        return ResponseEntity.ok(employee);
    }

    @PostMapping(value = "/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody String employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(employee, JsonNode.class);
        Employee updatedEmployee = mongoService.updateEmployee(jsonNode.get("firstName").asText(), jsonNode.get("lastName").asText(), jsonNode.get("newLastName").asText());
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/findEmployeesByAgg")
    public ResponseEntity<List<Employee>> findEmployeesByAgg(@RequestParam String firstName) {
        List<Employee> employeeList = mongoService.getEmployeeByAggregation(firstName);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/employeeCountBySalary")
    public ResponseEntity<List<EmployeeCountBySalary>> getEmployeesCountBySalaryAggregation() {
        List<EmployeeCountBySalary> employeeCountBySalaries = mongoService.getEmployeesCountBySalaryAggregation();
        return ResponseEntity.ok(employeeCountBySalaries);
    }

}
