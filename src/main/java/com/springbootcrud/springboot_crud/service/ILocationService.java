package com.springbootcrud.springboot_crud.service;

import com.springbootcrud.springboot_crud.dto.LocationDTO;

import java.util.List;

public interface ILocationService {

   LocationDTO save(LocationDTO locationDTO);

   LocationDTO update(Integer id , LocationDTO locationDTO);

    LocationDTO findById(Integer locationId);

    List<LocationDTO> findAl();

    void deleteById(Integer locationId);

}

