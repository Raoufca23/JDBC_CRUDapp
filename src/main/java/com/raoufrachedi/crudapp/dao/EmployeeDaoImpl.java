package com.raoufrachedi.crudapp.dao;

import com.raoufrachedi.crudapp.model.Employee;
import com.raoufrachedi.crudapp.model.EmployeeBuilder;
import com.raoufrachedi.crudapp.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public List<Employee> findAll() {
        Connection conn = DBConnection.getConnection();
        if(conn == null) {
            return null;
        }
        List<Employee> employees = new LinkedList<>();
        String query = "SELECT * FROM employees";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .gender(resultSet.getBoolean("gender"))
                        .birthDate(resultSet.getDate("birth_date"))
                        .salary(resultSet.getDouble("salary"))
                        .build();
                employees.add(employee);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Connection conn = DBConnection.getConnection();
        if(conn == null) {
            return null;
        }
        String query = "SELECT * FROM employees WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .gender(resultSet.getBoolean("gender"))
                        .birthDate(resultSet.getDate("birth_date"))
                        .salary(resultSet.getDouble("salary"))
                        .build();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void save(Employee employee) {
        Connection conn = DBConnection.getConnection();
        if(conn == null) {
            return;
        }
        if(employee.getId() > 0) {
            String query = "UPDATE employees SET name=?, gender=?, birth_date=?, salary=? WHERE id=?";
            try ( PreparedStatement preparedStatement = conn.prepareStatement(query) ) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setBoolean(2, employee.isGender());
                preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirth_date()));
                preparedStatement.setDouble(4, employee.getSalary());
                preparedStatement.setInt(5, employee.getId());

                preparedStatement.executeUpdate();

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } else {
          String query = "INSERT INTO employees (name, gender, birth_date, salary) VALUES(?, ?, ?, ?)";
          try ( PreparedStatement preparedStatement = conn.prepareStatement(query) ) {
              preparedStatement.setString(1, employee.getName());
              preparedStatement.setBoolean(2, employee.isGender());
              preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirth_date()));
              preparedStatement.setDouble(4, employee.getSalary());

              preparedStatement.executeUpdate();

          } catch (SQLException se) {
              se.printStackTrace();
          } finally {
              try {
                  conn.close();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }
        }
    }

    @Override
    public void deleteById(int id) {
        Connection conn = DBConnection.getConnection();
        if(conn == null) {
            return;
        }
        String query = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try{
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
