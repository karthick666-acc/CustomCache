package com.example.cache.customcache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.repository.EmployeeRepository;
import com.example.cache.customcache.utils.InternalCache;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	InternalCache internalCache;
	
	public List<EmployeeEntity> getEmployee(EmployeeEntity employeeEntity) {
		internalCache.get(employeeEntity.getId());
		List<EmployeeEntity> employeeEntities = empRepository.getEmployee(employeeEntity.getId());
		return employeeEntities;
		
	}
	
	public int addEmployee(EmployeeEntity employeeEntity) {
		internalCache.put(employeeEntity.getId(), employeeEntity);
		int status = empRepository.addEmployee(employeeEntity);
		return status;
		
	}
	
	
	public int removeEmployee(EmployeeEntity employeeEntity) {	
		internalCache.remove(employeeEntity.getId());
		int status = empRepository.removeEmployee(employeeEntity);
		return status;
		
	}
	
	public int removeAll() {	
		internalCache.clear();
		int status = empRepository.removeAll();
		return status;
		
	}
	
	public void clearCache() {		
		
		internalCache.clear();
		
	}
	

}
