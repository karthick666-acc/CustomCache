package com.example.cache.customcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.service.EmployeeService;
import com.example.cache.customcache.service.EmployeeServiceImpl;

@SpringBootApplication
public class CustomcacheApplication {
	
	 private static final Logger logger = LoggerFactory.getLogger(CustomcacheApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CustomcacheApplication.class, args);
		logger.info("Application started");
		testCache(context);
	}
	
	/**
	 * @param context
	 * For testing Cache
	 */
	public static void testCache(ConfigurableApplicationContext context) {
		try {
			EmployeeServiceImpl employeeService = context.getBean(EmployeeServiceImpl.class);
			
			logger.info("Employee has been Added");
			
			employeeService.addEmployee(new EmployeeEntity( "Karthick", 44, 7, "Assoc Mgr"));
			
			employeeService.addEmployee(new EmployeeEntity( "Raj", 45, 7, "Assoc Mgr"));
			
			
			  logger.info("Retrieving Employee details");
			  
			  EmployeeEntity entity = employeeService.getEmployee(new EmployeeEntity(
			  "Karthick", 44));
			  
			  logger.info(entity.toString());
			  
				/*
				 * logger.info("Removing Employee details");
				 * 
				 * employeeService.removeEmployee(new EmployeeEntity( "Karthick", 44));
				 * 
				 * logger.info("Remove all Employee details");
				 * 
				 * employeeService.removeAll();
				 * 
				 * employeeService.addEmployee(new EmployeeEntity( "Karthick", 44, 7,
				 * "Assoc Mgr"));
				 * 
				 * employeeService.addEmployee(new EmployeeEntity( "Raj", 45, 7, "Assoc Mgr"));
				 * 
				 * logger.info("Clearing cache");
				 * 
				 * 
				 * employeeService.clearCache();
				 */		
			
		}catch (Exception e) {
			logger.info("",e.getCause());
		}
		
		
	}

}
