package com.springbootcrud.springboot_crud.repository;

import com.springbootcrud.springboot_crud.model.EmployeeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLocationRepository extends JpaRepository<EmployeeLocation, Integer> {
}
