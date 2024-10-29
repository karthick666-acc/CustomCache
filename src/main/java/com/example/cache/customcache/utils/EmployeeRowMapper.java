package com.example.cache.customcache.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.cache.customcache.entity.EmployeeEntity;

public class EmployeeRowMapper implements RowMapper<EmployeeEntity> {
	   @Override
	   public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
	       return new EmployeeEntity(
	               rs.getInt("id"),
	               rs.getString("name"),
	               rs.getInt("emp_nbr"),
	               rs.getInt("level"),
	               rs.getString("designation")
	               
	       );
	   }
	}