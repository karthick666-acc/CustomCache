package com.example.cache.customcache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cache.customcache.CustomcacheApplication;
import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.repository.EmployeeRepositoryImpl;
import com.example.cache.customcache.utils.CustomException;
import com.example.cache.customcache.utils.InternalCache;

@Service
public class EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	EmployeeRepositoryImpl empRepository;
	
	@Autowired
	InternalCache internalCache;
	
	public EmployeeEntity getEmployee(EmployeeEntity employeeEntity) throws CustomException {
		EmployeeEntity employee = null;
		
		try {
			employee = internalCache.get(employeeEntity.getId());
			if(employee == null) {
				employee = empRepository.getEmployee(employeeEntity.getId());
				internalCache.put(employeeEntity.getId(), employeeEntity);
			}
		}catch (Exception e) {
			throw new CustomException("Failed to retrieve Employee", e);
		}
		
		logger.info("getEmployee method :"+ employee.toString());
		
		return employee;
		
	}
	
	public int addEmployee(EmployeeEntity employeeEntity) throws CustomException {
		int status;
		try {
			employeeEntity.setId(employeeEntity.hashCode());
			internalCache.put(employeeEntity.getId(), employeeEntity);
			status = empRepository.addEmployee(employeeEntity);
		}catch (Exception e) {
			throw new CustomException("Failed to add Employee", e);
		}
		logger.info(" addEmployee status : "+status);
		return status;
		
	}
	
	
	public int removeEmployee(EmployeeEntity employeeEntity) throws CustomException {
		int status;
		try {
			internalCache.remove(employeeEntity.getId());
			status = empRepository.removeEmployee(employeeEntity);
		} catch (Exception e) {
			throw new CustomException("Failed to delete Employee", e);
		}
		logger.info(" removeEmployee status : "+status);
		return status;

	}
	
	public int removeAll() throws CustomException {
		int status;
		try {
			internalCache.clear();
			status = empRepository.removeAll();
		} catch (Exception e) {
			throw new CustomException("Failed to delete Employees", e);
		}
		logger.info(" removeAll status : "+status);
		return status;

	}

	public void clearCache() throws CustomException {
		try {
			internalCache.clear();
		} catch (Exception e) {
			throw new CustomException("Failed to clear cache", e);
		}
		logger.info(" clearCache is performed");

	}
	

}
