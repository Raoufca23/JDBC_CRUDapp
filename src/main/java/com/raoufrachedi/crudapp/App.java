package com.raoufrachedi.crudapp;

import com.raoufrachedi.crudapp.model.Employee;
import com.raoufrachedi.crudapp.dao.EmployeeDao;
import com.raoufrachedi.crudapp.dao.EmployeeDaoImpl;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        // Employee employee = new Employee(0, "Mohamed", true, new Date(), 6000d);
        Employee employee = Employee.builder()
                .name("Raouf")
                .gender(true)
                .birthDate(new Date())
                .salary(9000d)
                .build();
        employeeDao.save(employee);
        // employeeDao.findAll().forEach(System.out::println);
        // Employee emp = employeeDao.findById(2);
        // System.out.println(emp);
        // employeeDao.deleteById(2);
        System.out.println("Done !");
    }
}
