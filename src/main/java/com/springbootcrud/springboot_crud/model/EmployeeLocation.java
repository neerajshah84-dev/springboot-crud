package com.springbootcrud.springboot_crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee_Location")
public class EmployeeLocation {

    /*
        Employee_Location will store one row per employee that has a location. Using empId as PK keeps one-to-one mapping.
    */

    @Id
    private Integer empId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

}
