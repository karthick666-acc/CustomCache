package com.example.cache.customcache.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.example.cache.customcache.entity.EmployeeEntity;

public class InternalCache {
	
	 private int capacity;
	 private Map<Integer, EmployeeEntity> cacheMap;
	 private LinkedList<Integer> sortingList;
	 
	 public InternalCache(int capacity) {
	        this.capacity = capacity;
	        this.cacheMap = new HashMap<>();
	        this.sortingList = new LinkedList<>();
	 }
	 
	 public void put(int key, EmployeeEntity employeeEntity) {
		 // Updating Existing key
		 if (cacheMap.containsKey(key)) {	          
	            cacheMap.put(key, employeeEntity);	          
	            sortingList.remove(Integer.valueOf(key));
	     } else {	      
	    	 // Adding new key
	            if (cacheMap.size() >= capacity) {
	                int leastUsedKey = sortingList.removeLast();
	                cacheMap.remove(leastUsedKey);
	            }
	            cacheMap.put(key, employeeEntity);
	     }
		 sortingList.addFirst(key);
		 
	 }
	 
	 public EmployeeEntity get(int key) {
		 
	        if (!cacheMap.containsKey(key)) {
	            return null;
	        }
	        sortingList.remove(Integer.valueOf(key));

	        sortingList.addFirst(key);

	        return cacheMap.get(key);
	    }
	 
	 public void remove(int key) {
		 
	        if (cacheMap.containsKey(key)) {	        	
	        	sortingList.remove(Integer.valueOf(key));
	 	        cacheMap.remove(key);
	        }
	       
	    }
	 
	 public void clear() {
		 
	        if (!cacheMap.isEmpty()) {	        	
	 	        cacheMap.clear();
	        }
	       
	    }

}
