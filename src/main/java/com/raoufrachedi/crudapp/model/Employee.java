package com.raoufrachedi.crudapp.model;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private boolean gender;
    private Date birthDate;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, boolean gender, Date birth_date, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birth_date;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birthDate;
    }

    public void setBirth_date(Date birth_date) {
        this.birthDate = birth_date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static EmployeeBuilder builder() {
        return  new EmployeeBuilder();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birth_date=" + birthDate +
                ", salary=" + salary +
                '}';
    }
}
