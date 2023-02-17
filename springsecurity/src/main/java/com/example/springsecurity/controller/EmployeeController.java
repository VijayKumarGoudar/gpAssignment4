package com.example.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.entity.Employee;
import com.example.springsecurity.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    
    @GetMapping("/employees")
    public List<Employee> getEmployees() {

    	Pageable pageable =  PageRequest.of(0, 1000);
        Page<Employee> employees = employeeService.getEmployees(pageable);
    	List<Employee> employeeList = employees.getContent();
   
        return employeeList;
    }

    @GetMapping("/employees/sort")
    public List<Employee> getEmployees(@RequestParam(defaultValue = "firstName") String sortBy,
    		@RequestParam(defaultValue = "ASC") String sortDir) {
    	 
    	Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
    			: Sort.by(sortBy).descending();

    	Pageable pageable =  PageRequest.of(0, 1000, sort);
        Page<Employee> employees = employeeService.getEmployees(pageable);
    	List<Employee> employeeList = employees.getContent();
   
        return employeeList;
    }

    @PostMapping("/createEmployee")
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }
    
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") long id) {

        return employeeService.getEmployee(id);
       
    }

    @PostMapping("/updateEmployee")
    public String updateEmployeeForm(@RequestBody Employee employee) {

        return employeeService.updateEmployee(employee);
       
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
       return employeeService.deleteEmployee(id);
    }
    
    @GetMapping("/employees/search/{keyword}")
    public List<Employee> getEmployeeByKeyWord(@PathVariable String keyword) {
    	
    	return employeeService.getEmployeeByKeyWord(keyword);
    }
    
    
}
