package com.example.play2game.Restcrud.rest;

import com.example.play2game.Restcrud.Service.EmployeeService;
import com.example.play2game.Restcrud.Service.EmployeeServiceImpl;
import com.example.play2game.Restcrud.dao.EmployeeDao;
import com.example.play2game.Restcrud.dao.EmployeeDaoImpl;
import com.example.play2game.Restcrud.entity.Employee;
import com.example.play2game.Restcrud.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/employees")
    public List<Employee> getAllEmployee(){
        return employeeService.findAll();
    }


    @RequestMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        int tableSize = getAllEmployee().size();

        //Employee employee = employeeService.findById(id);
        if (id < 0 || id >= tableSize){
            throw new EmployeeNotFoundException("Employee not found id-"+id);
        }
        return employeeService.findById(id);
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return  dbEmployee;
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee theEmployee){
        Employee employee = employeeService.save(theEmployee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable int id){
        Employee employee = getEmployee(id);
        if(employee!=null){
            employeeService.delete(id);
        } else{
            System.out.println("Employee does not exists in DB");
        }
    }
}
