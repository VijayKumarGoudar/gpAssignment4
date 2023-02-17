package com.example.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springsecurity.entity.Employee;
import com.example.springsecurity.repository.EmployeeRepo;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepo.findAll(pageable);    
    }

    public void createEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public Employee getEmployee(long id) {
        return employeeRepo.findById(id)
                           .orElse(null);
    }

    public String deleteEmployee(long id) {
    	Employee employee1 = employeeRepo.findById(id).orElse(null);
    	if(employee1 == null) {
			return "Employee with id " + id + " does not exist";
		} else {
			employeeRepo.deleteById(id);
			return "Deleted employee id - " + id;
		}
    }

	public String updateEmployee(Employee employee) {
		Employee employee1 = employeeRepo.findById(employee.getId()).orElse(null);
		if(employee1 == null) {
			return "Employee with id " + employee.getId() + " does not exist";
		} else {
			employeeRepo.save(employee);
			return "Employee is updated successfully";
		}
	}

	public List<Employee> getEmployeeByKeyWord(String keyword) {
		return employeeRepo.findByKeyword(keyword);
	}
}
