package com.springbootcrud.springboot_crud.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {


        private Integer empId;

        private String empName;

        private Double empSalary;

        private Integer locationId;

        private String locationCity;

        private String locationCountry;


}
