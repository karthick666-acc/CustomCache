package com.example.cache.customcache.repository;

import java.util.List;

import com.example.cache.customcache.entity.EmployeeEntity;

public interface EmployeeRepository  {
  
 EmployeeEntity getEmployee(int empId);    
   
 int addEmployee(EmployeeEntity entity); 
 
 int removeEmployee(EmployeeEntity entity);  

 int removeAll();  
    
    
}