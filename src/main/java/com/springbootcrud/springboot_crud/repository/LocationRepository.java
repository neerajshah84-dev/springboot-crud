package com.springbootcrud.springboot_crud.repository;

import com.springbootcrud.springboot_crud.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
}
