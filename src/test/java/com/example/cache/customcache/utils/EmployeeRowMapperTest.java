package com.example.cache.customcache.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cache.customcache.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EmployeeRowMapperTest {
    @Test
    void testMapRow() throws SQLException {
        // Arrange
        EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt(Mockito.<String>any())).thenReturn(1);
        when(rs.getString(Mockito.<String>any())).thenReturn("String");

        // Act
        EmployeeEntity actualMapRowResult = employeeRowMapper.mapRow(rs, 10);

        // Assert
        verify(rs, atLeast(1)).getInt(Mockito.<String>any());
        verify(rs, atLeast(1)).getString(Mockito.<String>any());
        assertEquals("String", actualMapRowResult.getEmpName());
        assertEquals("String", actualMapRowResult.getEmpdesignation());
        assertEquals(1, actualMapRowResult.getId());
        assertEquals(1, actualMapRowResult.getEmpLevel());
        assertEquals(1, actualMapRowResult.getEmpAge());
    }

    @Test
    void testMapRow2() throws SQLException {
        // Arrange
        EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt(Mockito.<String>any())).thenThrow(new SQLException());

        // Act and Assert
        assertThrows(SQLException.class, () -> employeeRowMapper.mapRow(rs, 10));
        verify(rs).getInt(eq("id"));
    }
}
