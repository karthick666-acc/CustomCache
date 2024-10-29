package com.example.cache.customcache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.exception.CustomException;
import com.example.cache.customcache.exception.EmployeeAddException;
import com.example.cache.customcache.exception.EmployeeDeleteException;
import com.example.cache.customcache.exception.EmployeeRetrieveException;
import com.example.cache.customcache.repository.EmployeeRepositoryImpl;
import com.example.cache.customcache.utils.InternalCache;

@Service
public class EmployeeServiceImpl  {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	EmployeeRepositoryImpl empRepository;
	
	@Autowired
	InternalCache internalCache;
	
	/**
	 * Fetching employee Details from DB/Cache
	 * @param employeeEntity
	 * @return EmployeeEntity
	 * @throws EmployeeRetrieveException 
	 */
	public EmployeeEntity getEmployee(EmployeeEntity employeeEntity) throws EmployeeRetrieveException  {
		EmployeeEntity employee = null;
		
		try {
			//employeeEntity.setId(employeeEntity.hashCode());
			employee = internalCache.get(employeeEntity.getId());
			if(employee == null) {
				employee = empRepository.getEmployee(employeeEntity.getId());
				internalCache.put(employeeEntity.getId(), employeeEntity);
			}
		}catch (Exception e) {
			throw new EmployeeRetrieveException("Failed to retrieve Employee", e);
		}
		
		logger.info("getEmployee method :"+ employee.toString());
		
		return employee;
		
	}
	
	/**
	 * Adds New employee details into DB and Cache
	 * @param employeeEntity
	 * @return Int
	 * @throws CustomException
	 */
	public int addEmployee(EmployeeEntity employeeEntity) throws EmployeeAddException {
		int status;
		try {
			//employeeEntity.setId(employeeEntity.hashCode());
			internalCache.put(employeeEntity.getId(), employeeEntity);
			status = empRepository.addEmployee(employeeEntity);
		}catch (Exception e) {
			throw new EmployeeAddException("Failed to add Employee", e);
		}
		logger.info(" addEmployee status : "+status);
		return status;
		
	}
	
	
	/**
	 * Removes New employee details into DB and Cache
	 * @param employeeEntity
	 * @return Int
	 * @throws EmployeeDeleteException 
	 */
	public int removeEmployee(EmployeeEntity employeeEntity) throws EmployeeDeleteException {
		int status;
		try {
			internalCache.remove(employeeEntity.getId());
			status = empRepository.removeEmployee(employeeEntity);
		} catch (Exception e) {
			throw new EmployeeDeleteException("Failed to delete Employee", e);
		}
		logger.info(" removeEmployee status : "+status);
		return status;

	}
	
	/**
	 * Removes All employee details From DB and Cache
	 * @return Int
	 * @throws EmployeeDeleteException
	 */
	public int removeAll() throws EmployeeDeleteException {
		int status;
		try {
			internalCache.clear();
			status = empRepository.removeAll();
		} catch (Exception e) {
			throw new EmployeeDeleteException("Failed to delete Employees", e);
		}
		logger.info(" removeAll status : "+status);
		return status;

	}

	/**
	 * Removes All employee details From Cache only
	 * @throws EmployeeDeleteException
	 */
	public void clearCache() throws  EmployeeDeleteException {
		try {
			internalCache.clear();
		} catch (Exception e) {
			throw new EmployeeDeleteException("Failed to clear cache", e);
		}
		logger.info(" clearCache is performed");

	}
	

}
