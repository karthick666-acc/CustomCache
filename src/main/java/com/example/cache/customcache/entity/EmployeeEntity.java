package com.example.cache.customcache.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class EmployeeEntity implements Serializable {
	
	private int id;
	private String empName;
	private int empNbr;
	private int empLevel;
	private String empdesignation;
	
	
	
	
	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity( String empName, int empNbr) {
		super();
		this.empName = empName;
		this.empNbr = empNbr;
		//this.id = this.hashCode();
	}
	
	public EmployeeEntity( String empName, int empNbr, int empLevel, String empdesignation) {
		super();
		this.empName = empName;
		this.empNbr = empNbr;
		this.empLevel = empLevel;
		this.empdesignation = empdesignation;
		//this.id = this.hashCode();
	}
			
	public EmployeeEntity(int id, String empName, int empNbr, int empLevel, String empdesignation) {
		super();
		this.id = id;
		this.empName = empName;
		this.empNbr = empNbr;
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
	public int getEmpNbr() {
		return empNbr;
	}
	public void setEmpNbr(int empNbr) {
		this.empNbr = empNbr;
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
        result = result * 31 + this.empNbr;
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
        if (this.empNbr != emp.empNbr) return false;
        return empName != null ? empName.equals(emp.empName) : emp.empName == null;
    }

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", empName=" + empName + ", empNbr=" + empNbr + ", empLevel=" + empLevel
				+ ", empdesignation=" + empdesignation + "]";
	}
	
	

}
