package com.example.cache.customcache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.cache.customcache.utils.InternalCache;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppConfig {
  
	@Bean
   public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource){
       return new JdbcTemplate(hikariDataSource);
   }
	
	@Bean
	 public InternalCache internalCache(){
	       return new InternalCache(300);
	 }
	
	
}