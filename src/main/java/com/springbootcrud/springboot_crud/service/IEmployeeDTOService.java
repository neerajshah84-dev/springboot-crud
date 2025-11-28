package com.springbootcrud.springboot_crud.service;

import com.springbootcrud.springboot_crud.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeDTOService {
    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO findById( Integer empId);

    List<EmployeeDTO> findAll();

    EmployeeDTO update (Integer empId ,  EmployeeDTO employeeDTO);

    void deleteById(Integer empId);

}
