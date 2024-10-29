package com.example.cache.customcache.service;

import com.example.cache.customcache.entity.EmployeeEntity;

public interface EmployeeService {
	
	 EmployeeEntity getEmployee(EmployeeEntity employeeEntity);
	 
	 int addEmployee(EmployeeEntity employeeEntity);
	 
	 int removeEmployee(EmployeeEntity employeeEntity);
	 
	 int removeAll();
	 
	 void clearCache();	 

}
