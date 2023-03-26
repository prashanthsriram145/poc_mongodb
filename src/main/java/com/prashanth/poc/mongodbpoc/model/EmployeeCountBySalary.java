package com.prashanth.poc.mongodbpoc.model;

public class EmployeeCountBySalary {
    private Double salary;
    private int employeeCount;

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
}
