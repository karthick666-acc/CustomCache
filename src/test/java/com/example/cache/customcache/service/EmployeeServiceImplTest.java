package com.example.cache.customcache.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.exception.EmployeeAddException;
import com.example.cache.customcache.exception.EmployeeDeleteException;
import com.example.cache.customcache.exception.EmployeeRetrieveException;
import com.example.cache.customcache.repository.EmployeeRepositoryImpl;
import com.example.cache.customcache.utils.InternalCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    @MockBean
    private EmployeeRepositoryImpl employeeRepositoryImpl;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @MockBean
    private InternalCache internalCache;

    @Test
    void testGetEmployee() throws Exception {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 1);

        when(internalCache.get(anyInt())).thenReturn(employeeEntity);

        // Act
        EmployeeEntity actualEmployee = employeeServiceImpl.getEmployee(new EmployeeEntity("Emp Name", 1));

        // Assert
        verify(internalCache).get(eq(1186936595));
        assertSame(employeeEntity, actualEmployee);
    }

    @Test
    void testGetEmployee2() throws Exception {
        // Arrange
        when(internalCache.get(anyInt())).thenThrow(new EmployeeDeleteException("An error occurred"));

        // Act and Assert
        assertThrows(EmployeeRetrieveException.class,
                () -> employeeServiceImpl.getEmployee(new EmployeeEntity("Emp Name", 1)));
        verify(internalCache).get(eq(1186936595));
    }

    @Test
    void testGetEmployee3() throws Exception {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 1);

        when(employeeRepositoryImpl.getEmployee(anyInt())).thenReturn(employeeEntity);
        when(internalCache.get(anyInt())).thenReturn(null);
        doNothing().when(internalCache).put(anyInt(), Mockito.<EmployeeEntity>any());

        // Act
        EmployeeEntity actualEmployee = employeeServiceImpl.getEmployee(new EmployeeEntity("Emp Name", 1));

        // Assert
        verify(employeeRepositoryImpl).getEmployee(eq(1186936595));
        verify(internalCache).get(eq(1186936595));
        verify(internalCache).put(eq(1186936595), isA(EmployeeEntity.class));
        assertSame(employeeEntity, actualEmployee);
    }

    @Test
    void testGetEmployee4() throws Exception {
        // Arrange
        when(internalCache.get(anyInt())).thenReturn(mock(EmployeeEntity.class));

        // Act
        employeeServiceImpl.getEmployee(new EmployeeEntity("Emp Name", 1));

        // Assert
        verify(internalCache).get(eq(1186936595));
    }

    @Test
    void testGetEmployee5() throws Exception {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 1);

        when(internalCache.get(anyInt())).thenReturn(employeeEntity);

        // Act
        EmployeeEntity actualEmployee = employeeServiceImpl.getEmployee(new EmployeeEntity(null, 1));

        // Assert
        verify(internalCache).get(eq(16368));
        assertSame(employeeEntity, actualEmployee);
    }

    @Test
    void testAddEmployee() throws Exception {
        // Arrange
        when(employeeRepositoryImpl.addEmployee(Mockito.<EmployeeEntity>any())).thenReturn(2);
        doNothing().when(internalCache).put(anyInt(), Mockito.<EmployeeEntity>any());

        // Act
        int actualAddEmployeeResult = employeeServiceImpl.addEmployee(new EmployeeEntity("Emp Name", 1));

        // Assert
        verify(employeeRepositoryImpl).addEmployee(isA(EmployeeEntity.class));
        verify(internalCache).put(eq(1186936595), isA(EmployeeEntity.class));
        assertEquals(2, actualAddEmployeeResult);
    }

    @Test
    void testAddEmployee2() throws Exception {
        // Arrange
        doThrow(new Exception(" addEmployee status : ")).when(internalCache).put(anyInt(), Mockito.<EmployeeEntity>any());

        // Act and Assert
        assertThrows(EmployeeAddException.class, () -> employeeServiceImpl.addEmployee(new EmployeeEntity("Emp Name", 1)));
        verify(internalCache).put(eq(1186936595), isA(EmployeeEntity.class));
    }

    @Test
    void testAddEmployee3() throws Exception {
        // Arrange
        when(employeeRepositoryImpl.addEmployee(Mockito.<EmployeeEntity>any())).thenReturn(2);
        doNothing().when(internalCache).put(anyInt(), Mockito.<EmployeeEntity>any());
        EmployeeEntity employeeEntity = mock(EmployeeEntity.class);
        when(employeeEntity.getId()).thenReturn(1);

        // Act
        int actualAddEmployeeResult = employeeServiceImpl.addEmployee(employeeEntity);

        // Assert
        verify(employeeEntity).getId();
        verify(employeeRepositoryImpl).addEmployee(isA(EmployeeEntity.class));
        verify(internalCache).put(eq(1), isA(EmployeeEntity.class));
        assertEquals(2, actualAddEmployeeResult);
    }

    @Test
    void testRemoveEmployee() throws EmployeeDeleteException {
        // Arrange
        when(employeeRepositoryImpl.removeEmployee(Mockito.<EmployeeEntity>any())).thenReturn(1);
        doNothing().when(internalCache).remove(anyInt());

        // Act
        int actualRemoveEmployeeResult = employeeServiceImpl.removeEmployee(new EmployeeEntity("Emp Name", 1));

        // Assert
        verify(employeeRepositoryImpl).removeEmployee(isA(EmployeeEntity.class));
        verify(internalCache).remove(eq(1186936595));
        assertEquals(1, actualRemoveEmployeeResult);
    }

    @Test
    void testRemoveEmployee2() throws EmployeeDeleteException {
        // Arrange
        when(employeeRepositoryImpl.removeEmployee(Mockito.<EmployeeEntity>any())).thenReturn(1);
        doNothing().when(internalCache).remove(anyInt());
        EmployeeEntity employeeEntity = mock(EmployeeEntity.class);
        when(employeeEntity.getId()).thenReturn(1);

        // Act
        int actualRemoveEmployeeResult = employeeServiceImpl.removeEmployee(employeeEntity);

        // Assert
        verify(employeeEntity).getId();
        verify(employeeRepositoryImpl).removeEmployee(isA(EmployeeEntity.class));
        verify(internalCache).remove(eq(1));
        assertEquals(1, actualRemoveEmployeeResult);
    }

    @Test
    void testRemoveAll() throws EmployeeDeleteException {
        // Arrange
        when(employeeRepositoryImpl.removeAll()).thenReturn(1);
        doNothing().when(internalCache).clear();

        // Act
        int actualRemoveAllResult = employeeServiceImpl.removeAll();

        // Assert
        verify(employeeRepositoryImpl).removeAll();
        verify(internalCache).clear();
        assertEquals(1, actualRemoveAllResult);
    }

    @Test
    void testClearCache() throws EmployeeDeleteException {
        // Arrange
        doNothing().when(internalCache).clear();

        // Act
        employeeServiceImpl.clearCache();

        // Assert
        verify(internalCache).clear();
    }
}
