package com.example.cache.customcache.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.cache.customcache.entity.EmployeeEntity;
import com.example.cache.customcache.exception.EmployeeAddException;
import com.example.cache.customcache.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void testGetEmployee() throws Exception {
        // Arrange
        when(employeeServiceImpl.getEmployee(Mockito.<EmployeeEntity>any())).thenReturn(new EmployeeEntity("Emp Name", 1));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/employee/get").param("name", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("nbr", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1186936595,\"empName\":\"Emp Name\",\"empNbr\":1,\"empLevel\":0,\"empdesignation\":null}"));
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddEmployee() throws EmployeeAddException {
      

        // Arrange
        EmployeeController employeeController = new EmployeeController();

        // Act
        employeeController.addEmployee(new EmployeeEntity("Emp Name", 1));
    }

    @Test
    void testRemoveEmployee() throws Exception {
        // Arrange
        when(employeeServiceImpl.removeEmployee(Mockito.<EmployeeEntity>any())).thenReturn(1);
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/employee/remove").param("name", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("nbr", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Removed successfully "));
    }

    @Test
    void testRemoveAll() throws Exception {
        // Arrange
        when(employeeServiceImpl.removeAll()).thenReturn(1);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/employee/removeAll");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("code", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Removed successfully "));
    }

    @Test
    void testRemoveAll2() throws Exception {
        // Arrange
        doNothing().when(employeeServiceImpl).clearCache();
        when(employeeServiceImpl.removeAll()).thenReturn(1);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/employee/removeAll");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("code", String.valueOf(4));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Removed successfully "));
    }
}
