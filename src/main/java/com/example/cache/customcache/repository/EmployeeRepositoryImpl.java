package com.example.cache.customcache.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.utils.EmployeeRowMapper;

public class EmployeeRepositoryImpl implements EmployeeRepository{
	
	
	private final JdbcTemplate jdbcTemplate;	
	
	@Autowired
	public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<EmployeeEntity> getEmployee(int empId) {
			   String sql = " SELECT id, name, emp_nbr, level, designation  FROM employee Where"+ 
			           		"id = ?";
			   return jdbcTemplate.query(sql,new Object[]{empId},new EmployeeRowMapper());
	}

	@Override
	public int addEmployee(EmployeeEntity entity) {
		String sql = " INSERT into employee(id, name, emp_nbr, level, designation) "+
					 " VALUES (?,?,?) ";
		   return jdbcTemplate.update(sql, entity.getId(), entity.getEmpName(), entity.getEmpNbr(),				   
				   entity.getEmpLevel(),
				   entity.getEmpdesignation());
	}

	@Override
	public int removeEmployee(EmployeeEntity entity) {
		String sql = " DELETE from employee Where id = ?";
	   return jdbcTemplate.update(sql, entity.getId());
	}

	@Override
	public int removeAll() {
		String sql = " DELETE from employee";
		   return jdbcTemplate.update(sql);
	}

}
 