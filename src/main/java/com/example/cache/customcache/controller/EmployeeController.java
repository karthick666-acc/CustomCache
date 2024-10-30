package com.example.cache.customcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.exception.CustomException;
import com.example.cache.customcache.exception.EmployeeAddException;
import com.example.cache.customcache.exception.EmployeeDeleteException;
import com.example.cache.customcache.exception.EmployeeRetrieveException;
import com.example.cache.customcache.service.EmployeeService;
import com.example.cache.customcache.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;

	/**
	 * Fetch employee details 
	 * @param empName
	 * @param empNbr
	 * @return ResponseEntity
	 * @throws EmployeeRetrieveException
	 */
	@GetMapping("/employee/get")
	public ResponseEntity<Object> getEmployee(@RequestParam(name = "name") String empName,
			@RequestParam(name = "nbr") int empNbr) throws EmployeeRetrieveException {
		EmployeeEntity employeeEntity = null;
		employeeEntity = employeeService.getEmployee(new EmployeeEntity(empName, empNbr));
		return new ResponseEntity<Object>(employeeEntity, HttpStatus.OK);

	}

	@PostMapping("/employee/add")
	public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity entity) throws EmployeeAddException {
		int status = employeeService.addEmployee(entity);
		return new ResponseEntity<EmployeeEntity>(entity, HttpStatus.CREATED);

	}

	@DeleteMapping("/employee/remove")
	public ResponseEntity<Object> removeEmployee(@RequestParam(name = "name") String empName,
			@RequestParam(name = "nbr") int empNbr) throws EmployeeDeleteException {
		int status = employeeService.removeEmployee(new EmployeeEntity(empName, empNbr));
		return new ResponseEntity<Object>("Removed successfully ", HttpStatus.OK);

	}

	@DeleteMapping("/employee/removeAll")
	public ResponseEntity<Object> removeAll(@RequestParam(name = "code") int code) throws EmployeeDeleteException {
		int status;
		if (code == 1) {
			status = employeeService.removeAll();
		} else {
			employeeService.clearCache();
		}
		return new ResponseEntity<Object>("Removed successfully ", HttpStatus.OK);

	}

}
