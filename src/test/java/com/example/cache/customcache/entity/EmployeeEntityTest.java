package com.example.cache.customcache.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class EmployeeEntityTest {

	@Test
    void testGetId() {
        assertEquals(1186936595, (new EmployeeEntity("Emp Name", 1)).getId());
    }

    @Test
    void testGetId2() {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 1);
        employeeEntity.setEmpName(null);

        // Act and Assert
        assertEquals(16368, employeeEntity.getId());
    }

    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 1);
        EmployeeEntity employeeEntity2 = new EmployeeEntity("Emp Name", 1);

        // Act and Assert
        assertEquals(employeeEntity, employeeEntity2);
        int expectedHashCodeResult = employeeEntity.hashCode();
        assertEquals(expectedHashCodeResult, employeeEntity2.hashCode());
    }

    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity(null, 1);
        EmployeeEntity employeeEntity2 = new EmployeeEntity(null, 1);

        // Act and Assert
        assertEquals(employeeEntity, employeeEntity2);
        int expectedHashCodeResult = employeeEntity.hashCode();
        assertEquals(expectedHashCodeResult, employeeEntity2.hashCode());
    }

    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 1);

        // Act and Assert
        assertEquals(employeeEntity, employeeEntity);
        int expectedHashCodeResult = employeeEntity.hashCode();
        assertEquals(expectedHashCodeResult, employeeEntity.hashCode());
    }

    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity(null, 1);

        // Act and Assert
        assertNotEquals(employeeEntity, new EmployeeEntity("Emp Name", 1));
    }

    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        EmployeeEntity employeeEntity = new EmployeeEntity("Emp Name", 0);

        // Act and Assert
        assertNotEquals(employeeEntity, new EmployeeEntity("Emp Name", 1));
    }

    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange, Act and Assert
        assertNotEquals(new EmployeeEntity("Emp Name", 1), null);
    }

    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange, Act and Assert
        assertNotEquals(new EmployeeEntity("Emp Name", 1), "Different type to EmployeeEntity");
    }

    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        EmployeeEntity actualEmployeeEntity = new EmployeeEntity(1, "Emp Name", 1, 1, "Empdesignation");
        actualEmployeeEntity.setEmpLevel(1);
        actualEmployeeEntity.setEmpName("Emp Name");
        actualEmployeeEntity.setEmpNbr(1);
        actualEmployeeEntity.setEmpdesignation("Empdesignation");
        actualEmployeeEntity.setId(1);
        String actualToStringResult = actualEmployeeEntity.toString();
        int actualEmpLevel = actualEmployeeEntity.getEmpLevel();
        String actualEmpName = actualEmployeeEntity.getEmpName();
        int actualEmpNbr = actualEmployeeEntity.getEmpNbr();

        // Assert that nothing has changed
        assertEquals("Emp Name", actualEmpName);
        assertEquals("Empdesignation", actualEmployeeEntity.getEmpdesignation());
        assertEquals("EmployeeEntity [id=1, empName=Emp Name, empNbr=1, empLevel=1, empdesignation=Empdesignation]",
                actualToStringResult);
        assertEquals(1, actualEmpLevel);
        assertEquals(1, actualEmpNbr);
    }

    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        EmployeeEntity actualEmployeeEntity = new EmployeeEntity("Emp Name", 1);
        actualEmployeeEntity.setEmpLevel(1);
        actualEmployeeEntity.setEmpName("Emp Name");
        actualEmployeeEntity.setEmpNbr(1);
        actualEmployeeEntity.setEmpdesignation("Empdesignation");
        actualEmployeeEntity.setId(1);
        String actualToStringResult = actualEmployeeEntity.toString();
        int actualEmpLevel = actualEmployeeEntity.getEmpLevel();
        String actualEmpName = actualEmployeeEntity.getEmpName();
        int actualEmpNbr = actualEmployeeEntity.getEmpNbr();

        // Assert that nothing has changed
        assertEquals("Emp Name", actualEmpName);
        assertEquals("Empdesignation", actualEmployeeEntity.getEmpdesignation());
        assertEquals("EmployeeEntity [id=1, empName=Emp Name, empNbr=1, empLevel=1, empdesignation=Empdesignation]",
                actualToStringResult);
        assertEquals(1, actualEmpLevel);
        assertEquals(1, actualEmpNbr);
    }

    @Test
    void testGettersAndSetters3() {
        // Arrange and Act
        EmployeeEntity actualEmployeeEntity = new EmployeeEntity("Emp Name", 1, 1, "Empdesignation");
        actualEmployeeEntity.setEmpLevel(1);
        actualEmployeeEntity.setEmpName("Emp Name");
        actualEmployeeEntity.setEmpNbr(1);
        actualEmployeeEntity.setEmpdesignation("Empdesignation");
        actualEmployeeEntity.setId(1);
        String actualToStringResult = actualEmployeeEntity.toString();
        int actualEmpLevel = actualEmployeeEntity.getEmpLevel();
        String actualEmpName = actualEmployeeEntity.getEmpName();
        int actualEmpNbr = actualEmployeeEntity.getEmpNbr();

        // Assert that nothing has changed
        assertEquals("Emp Name", actualEmpName);
        assertEquals("Empdesignation", actualEmployeeEntity.getEmpdesignation());
        assertEquals("EmployeeEntity [id=1, empName=Emp Name, empNbr=1, empLevel=1, empdesignation=Empdesignation]",
                actualToStringResult);
        assertEquals(1, actualEmpLevel);
        assertEquals(1, actualEmpNbr);
    }
}
