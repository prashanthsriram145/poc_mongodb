package com.prashanth.poc.mongodbpoc.controller;

import com.prashanth.poc.mongodbpoc.model.Employee;
import com.prashanth.poc.mongodbpoc.service.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
