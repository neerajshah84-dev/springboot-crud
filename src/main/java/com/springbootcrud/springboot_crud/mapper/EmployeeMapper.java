package com.springbootcrud.springboot_crud.mapper;

import com.springbootcrud.springboot_crud.dto.EmployeeDTO;
import com.springbootcrud.springboot_crud.model.Employee;
import com.springbootcrud.springboot_crud.model.EmployeeLocation;
import com.springbootcrud.springboot_crud.model.Location;

public class EmployeeMapper {

    public static EmployeeDTO toEmpDTO (Employee employee , EmployeeLocation empLocation) {
        if(employee == null)
            return null;

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setEmpName(employee.getEmpName());
        employeeDTO.setEmpSalary(employee.getEmpSalary());

        if(empLocation != null && empLocation.getLocation() != null) {
            employeeDTO.setLocationId(empLocation.getLocation().getLocationId());
            employeeDTO.setLocationCity(empLocation.getLocation().getCity());
            employeeDTO.setLocationCountry(empLocation.getLocation().getCountry());

        }

        return employeeDTO ;

    }


    public  static Employee toEntity (EmployeeDTO  dto) {
        if(dto == null) return null;

        Employee employee = new Employee();
        if(dto.getEmpId() != null) {
            employee.setEmpId(dto.getEmpId());
        }
        employee.setEmpName(dto.getEmpName());
        employee.setEmpSalary( dto.getEmpSalary() );

        return employee;
    }

    public static EmployeeLocation toEmployeeLocation (Integer empId , Location location) {
        EmployeeLocation employeeLocation = new EmployeeLocation();
        employeeLocation.setEmpId(empId);
        employeeLocation.setLocation(location);
        return employeeLocation;
    }

}
