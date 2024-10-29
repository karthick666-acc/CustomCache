package com.example.cache.customcache.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.exception.CustomException;
import com.example.cache.customcache.service.EmployeeService;

public class InternalCache {

	private static final Logger logger = LoggerFactory.getLogger(InternalCache.class);

	private int capacity;
	private Map<Integer, EmployeeEntity> cacheMap;
	private LinkedList<Integer> sortingList;

	public InternalCache(int capacity) {
		this.capacity = capacity;
		this.cacheMap = new HashMap<>();
		this.sortingList = new LinkedList<>();
	}

	/**
	 * A new key and value will be added and the key will be placed in the first index of Linked list
	 * If Map exceed its its size, least used value will be removed
	 * @param key
	 * @param employeeEntity
	 * @throws CustomException
	 */
	public void put(int key, EmployeeEntity employeeEntity) throws Exception {
		// Updating Existing key

		logger.info("cacheMap  Before put operation: " + cacheMap);
		logger.info("sortingList Before put operation: " + sortingList);
		try {
			if (cacheMap.containsKey(key)) {
				logger.info("Updating key");
				cacheMap.put(key, employeeEntity);
				sortingList.remove(Integer.valueOf(key));
			} else {
				// Adding new key
				logger.info("Adding new key");
				if (cacheMap.size() >= capacity) {
					int leastUsedKey = sortingList.removeLast();
					cacheMap.remove(leastUsedKey);
				}
				cacheMap.put(key, employeeEntity);
			}
			sortingList.addFirst(key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

		logger.info("cacheMap  After put operation: " + cacheMap);
		logger.info("sortingList After put operation: " + sortingList);

	}

	/**
	 *  Value will be returned for the given key and the key will be placed in the first index of Linked list
	 * @param key
	 * @return EmployeeEntity
	 * @throws CustomException
	 */
	public EmployeeEntity get(int key) throws Exception {

		logger.info("cacheMap  Before get operation: " + cacheMap);
		logger.info("sortingList Before get operation: " + sortingList);
		try {
			if (cacheMap.isEmpty() || !cacheMap.containsKey(key)) {
				return null;
			}
			sortingList.remove(Integer.valueOf(key));

			sortingList.addFirst(key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("cacheMap  after get operation: " + cacheMap);
		logger.info("sortingList after get operation: " + sortingList);

		return cacheMap.get(key);
	}

	/**
	 * Key and value will be removed from both List and Map
	 * @param key
	 */
	public void remove(int key) {
		logger.info("cacheMap  before remove operation: " + cacheMap);
		logger.info("sortingList before remove operation: " + sortingList);
		if (cacheMap.containsKey(key)) {
			sortingList.remove(Integer.valueOf(key));
			cacheMap.remove(key);
		}

		logger.info("cacheMap  after remove operation: " + cacheMap);
		logger.info("sortingList after remove operation: " + sortingList);

	}

	/**
	 * Map and List will be cleared here
	 */
	public void clear() {
		logger.info("cacheMap  before clear operation: " + cacheMap);
		logger.info("sortingList before clear operation: " + sortingList);
		if (!cacheMap.isEmpty()) {
			cacheMap.clear();
			sortingList.clear();
		}

		logger.info("cacheMap  before after operation: " + cacheMap);
		logger.info("sortingList before after operation: " + sortingList);

	}

}
