package com.example.cache.customcache.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.repository.EmployeeRepositoryImpl;
import com.example.cache.customcache.utils.InternalCache;

@SpringBootTest
class EmployeeServiceImplTest {
	
	@Autowired
	EmployeeServiceImpl service;
	
	@MockBean
	EmployeeRepositoryImpl repository;
	
	@MockBean
	InternalCache cache;
	
	@BeforeEach
	void setup() throws Exception {
		EmployeeEntity entity = new EmployeeEntity(1,"Karthick", 44, 8, "Assoc Manager");
		Mockito.when(cache.get(1)).thenReturn(entity);
		Mockito.when(repository.getEmployee(1)).thenReturn(entity);
		
	}

	@Test
	public void testGetEmployee_Success_1() throws Exception {
		String name = "Karthick";
		EmployeeEntity entity = cache.get(1);
		assertEquals(name, entity.getEmpName());
	}
	
	@Test
	public void testGetEmployee_Success_2() throws Exception {
		String name = "Karthick";
		EmployeeEntity entity = repository.getEmployee(1);
		assertEquals(name, entity.getEmpName());
	}

	@Test
	void testAddEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	void testClearCache() {
		fail("Not yet implemented");
	}

}
