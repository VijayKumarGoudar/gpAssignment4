package com.example.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springsecurity.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Query(value = "select * from employee t where t.first_name like %:keyword% or t.last_name like %:keyword%", nativeQuery = true)
	List<Employee> findByKeyword(@Param("keyword") String keyword);
}
