package com.example.cache.customcache.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.cache.customcache.utils.InternalCache;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppConfig {
	
	
	@Bean
    public DataSource h2DataSource()    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:dcbapp");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
  
	@Bean
   public JdbcTemplate h2JdbcTemplate(){
       return new JdbcTemplate(h2DataSource());
   }
	
	@Bean
	 public InternalCache internalCache(){
	       return new InternalCache(300);
	 }
	
	
}