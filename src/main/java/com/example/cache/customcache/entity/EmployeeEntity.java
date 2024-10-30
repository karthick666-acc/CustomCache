package com.example.cache.customcache.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class EmployeeEntity implements Serializable {
	
	private int id;
	private String empName;
	private int empAge;
	private int empLevel;
	private String empdesignation;
	
	
	
	
	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity( String empName, int empNbr) {
		super();
		this.empName = empName;
		this.empAge = empNbr;
		//this.id = this.hashCode();
	}
	
	public EmployeeEntity( String empName, int empNbr, int empLevel, String empdesignation) {
		super();
		this.empName = empName;
		this.empAge = empNbr;
		this.empLevel = empLevel;
		this.empdesignation = empdesignation;
		//this.id = this.hashCode();
	}
			
	public EmployeeEntity(int id, String empName, int empNbr, int empLevel, String empdesignation) {
		super();
		this.id = id;
		this.empName = empName;
		this.empAge = empNbr;
		this.empLevel = empLevel;
		this.empdesignation = empdesignation;
		//this.id = this.hashCode();
	}
	
	public int getId() {
		return this.hashCode();
	}	
	
	public void setId(int id) {
		this.id = id;
	}	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empNbr) {
		this.empAge = empNbr;
	}
	public int getEmpLevel() {
		return empLevel;
	}
	public void setEmpLevel(int empLevel) {
		this.empLevel = empLevel;
	}
	public String getEmpdesignation() {
		return empdesignation;
	}
	public void setEmpdesignation(String empdesignation) {
		this.empdesignation = empdesignation;
	}
	
	  // Override hashCode method
    @Override
    public int hashCode() {
        // Generate hashCode based on employee number and name 
        int result = 17;
        result = 31 * result + this.empAge;
        result = 31 * result + (this.empName != null ? this.empName.hashCode() : 0);
        this.id = result;
        return result;
    }
    // Override equals method for the HashMap key comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeeEntity emp = (EmployeeEntity) obj;
        if (this.empAge != emp.empAge) return false;
        return empName != null ? empName.equals(emp.empName) : emp.empName == null;
    }

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", empName=" + empName + ", empAge=" + empAge + ", empLevel=" + empLevel
				+ ", empdesignation=" + empdesignation + "]";
	}
	
	

}
