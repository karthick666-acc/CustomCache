package com.example.cache.customcache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cache.customcache.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeRepositoryImplTest {
    @Autowired
    private EmployeeRepositoryImpl employeeRepositoryImpl;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetEmployee() throws DataAccessException {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 1);

        when(jdbcTemplate.queryForObject(Mockito.<String>any(), Mockito.<Object[]>any(),
                Mockito.<RowMapper<EmployeeEntity>>any())).thenReturn(employeeEntity);

        // Act
        EmployeeEntity actualEmployee = employeeRepositoryImpl.getEmployee(1);

        // Assert
        verify(jdbcTemplate).queryForObject(eq(" SELECT id, name, emp_nbr, level, designation  FROM employee Where id = ?"),
                isA(Object[].class), isA(RowMapper.class));
        assertSame(employeeEntity, actualEmployee);
    }

    @Test
    void testAddEmployee() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), (Object[]) any())).thenReturn(1);

        // Act
        int actualAddEmployeeResult = employeeRepositoryImpl.addEmployee(new EmployeeEntity("Emp Name", 1));

        // Assert
        verify(jdbcTemplate).update(eq(" INSERT into employee(id, name, emp_nbr, level, designation)  VALUES (?,?,?,?,?) "),
                (Object[]) any());
        assertEquals(1, actualAddEmployeeResult);
    }

    @Test
    void testRemoveEmployee() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), (Object[]) any())).thenReturn(1);

        // Act
        int actualRemoveEmployeeResult = employeeRepositoryImpl.removeEmployee(new EmployeeEntity("Emp Name", 1));

        // Assert
        verify(jdbcTemplate).update(eq(" DELETE from employee Where id = ?"), (Object[]) any());
        assertEquals(1, actualRemoveEmployeeResult);
    }

    @Test
    void testRemoveAll() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any())).thenReturn(1);

        // Act
        int actualRemoveAllResult = employeeRepositoryImpl.removeAll();

        // Assert
        verify(jdbcTemplate).update(eq(" DELETE from employee"));
        assertEquals(1, actualRemoveAllResult);
    }
}
