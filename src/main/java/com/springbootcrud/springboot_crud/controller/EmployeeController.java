package com.springbootcrud.springboot_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootcrud.springboot_crud.exception.RecordNotFoundException;
import com.springbootcrud.springboot_crud.model.Employee;
import com.springbootcrud.springboot_crud.service.IEmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@PostMapping("/save")
	public ResponseEntity<Employee> save (@RequestBody Employee employee) {
		System.out.println("emp name: "+ employee.getEmpName());
		System.out.println("emp name: "+ employee.getEmpSalary());
		
		return ResponseEntity.ok(employeeService.save(employee));
	}
	
	@GetMapping("/findbyid/{empId}")
	public ResponseEntity< Optional<Employee>> findById(@PathVariable int empId) {
		return ResponseEntity.ok(employeeService.findById(empId));
	}
	
	@GetMapping("/findall")
	public ResponseEntity<List<Employee>> findAll() {
		return ResponseEntity.ok(employeeService.findAll());
	}
	

	@PutMapping("/update/{empId}")
	public ResponseEntity<Employee> update(@PathVariable int empId, @RequestBody Employee employee) {
		//throw custom exception if emp not found
		
		Employee emp = employeeService.findById(empId).orElseThrow( ()->new RecordNotFoundException("Employee #ID Not Found!!"));
		emp.setEmpName(employee.getEmpName());
		emp.setEmpSalary(employee.getEmpSalary());
		
		return ResponseEntity.ok(employeeService.update(emp) );
	}
	
	@DeleteMapping("/deletebyid/{empId}")
	public ResponseEntity<String> deleteById(@PathVariable int empId) {
		employeeService.deleteById(empId);
		
		return ResponseEntity.ok("Employee Deleted Successfully!!!"); 
	}
}
