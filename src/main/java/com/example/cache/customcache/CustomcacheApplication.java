package com.example.cache.customcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.service.EmployeeService;

@SpringBootApplication
public class CustomcacheApplication {
	
	 private static final Logger logger = LoggerFactory.getLogger(CustomcacheApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CustomcacheApplication.class, args);
		logger.info("Application started");
		testCache(context);
	}
	
	public static void testCache(ConfigurableApplicationContext context) {
		try {
			EmployeeService employeeService = context.getBean(EmployeeService.class);
			
			logger.info("Employee has been Added");
			
			employeeService.addEmployee(new EmployeeEntity( "Karthick", 44, 7, "Assoc Mgr"));
			
			employeeService.addEmployee(new EmployeeEntity( "Raj", 45, 7, "Assoc Mgr"));
			
			logger.info("Retrieving Employee details");
			
			EmployeeEntity entity = employeeService.getEmployee(new EmployeeEntity( "Karthick", 44));
			
			logger.info(entity.toString());
			
			logger.info("Removing Employee details");
			
			employeeService.removeEmployee(new EmployeeEntity( "Karthick", 44));
			
			logger.info("Remove all Employee details");
			
			employeeService.removeAll();
			
			employeeService.addEmployee(new EmployeeEntity( "Karthick", 44, 7, "Assoc Mgr"));
			
			employeeService.addEmployee(new EmployeeEntity( "Raj", 45, 7, "Assoc Mgr"));
			
			logger.info("Clearing cache");
			
			employeeService.clearCache();
			
			logger.info("Retrieving Employee details");
			
			employeeService.getEmployee(new EmployeeEntity( "Karthick", 44));
			
		}catch (Exception e) {
			logger.info("",e.getCause());
		}
		
		
	}

}
