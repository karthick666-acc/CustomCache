package com.example.cache.customcache.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CacheErrorTest {
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CacheError actualCacheError = new CacheError(-1, "An error occurred");
        actualCacheError.setErrorCode(-1);
        actualCacheError.setErrorDescription("An error occurred");
        int actualErrorCode = actualCacheError.getErrorCode();

        // Assert that nothing has changed
        assertEquals("An error occurred", actualCacheError.getErrorDescription());
        assertEquals(-1, actualErrorCode);
    }
}
