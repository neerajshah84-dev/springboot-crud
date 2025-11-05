package com.springbootcrud.springboot_crud.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    private String empName;

    private double empSalary;


    public int getEmpId() {
		return empId;
	}
    
    public void setEmpId(int empId) {
		this.empId = empId;
	}
    
    public void setEmpName(String empName) {
		this.empName = empName;
	}
    public String getEmpName() {
		return empName;
	}
    
    public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
    
    public double getEmpSalary() {
		return empSalary;
	}
    
}