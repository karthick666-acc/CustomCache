package com.example.cache.customcache.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.service.EmployeeService;
import com.example.cache.customcache.utils.EmployeeRowMapper;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);
	
	private final JdbcTemplate jdbcTemplate;	
	
	@Autowired
	public EmployeeRepositoryImpl(JdbcTemplate h2DataSource) {
		super();
		this.jdbcTemplate = h2DataSource;
	}

	/**
	 * Retrieving Employee details from Database
	 *
	 */
	@SuppressWarnings("deprecation")
	@Override
	public EmployeeEntity getEmployee(int empId) {
		logger.info("empId "+empId);
			   String sql = " SELECT id, name, emp_nbr, level, designation  FROM employee Where id = ?";
			   List<EmployeeEntity> entities = jdbcTemplate.query(sql,new EmployeeRowMapper(), empId);
			   
			   return CollectionUtils.isEmpty(entities) ? null : entities.get(0);
	}

	/**
	 * Adding Employee details from Database
	 *
	 */
	@Override
	public int addEmployee(EmployeeEntity entity) {
		String sql = " INSERT into employee(id, name, emp_nbr, level, designation) "+
					 " VALUES (?,?,?,?,?) ";
		   return jdbcTemplate.update(sql, entity.getId(), entity.getEmpName(), entity.getEmpAge(),				   
				   entity.getEmpLevel(),
				   entity.getEmpdesignation());
	}
	
	
	/**
	 * Removing Employee details from Database
	 *
	 */
	@Override
	public int removeEmployee(EmployeeEntity entity) {
		String sql = " DELETE from employee Where id = ?";
	   return jdbcTemplate.update(sql, entity.getId());
	}

	/**
	 * Remove all Employee details from Database
	 *
	 */
	@Override
	public int removeAll() {
		String sql = " DELETE from employee";
		   return jdbcTemplate.update(sql);
	}

}
 