package com.example.play2game.Restcrud.Service;

import com.example.play2game.Restcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {

     List<Employee> findAll();
     Employee findById(int id);
     Employee save(Employee theEmployee);
     void delete(int id);
}
