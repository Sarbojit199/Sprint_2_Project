package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class EmployeeController {
		
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@GetMapping("/employees")
	public List<Employee> getEmployee(){
	
		return employeeRepository.findAll();
	}
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById( @PathVariable Long id) {
	Employee employee1 = employeeRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("not exist with id" +id));;
		return ResponseEntity.ok(employee1);
	}
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody  Employee employee) {
		Employee employees =this.employeeRepository.save(employee);
		return employees;
	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
		try {
	     this.employeeRepository.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.ACCEPTED.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> update(@RequestBody Employee employee,@PathVariable Long id ){
	Employee	 employee1 = employeeRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("not exist with id" +id));
		
	employee1.setFirstName(employee.getFirstName());
		employee1.setLastName(employee.getLastName());
		employee1.setEmailId(employee.getEmailId());
		Employee updateEmployee=employeeRepository.save(employee1);
		return ResponseEntity.ok(updateEmployee);
	
	}
	
	
	
	
	
	
}
