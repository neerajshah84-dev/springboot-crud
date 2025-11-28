package com.springbootcrud.springboot_crud.service;

import com.springbootcrud.springboot_crud.dto.LocationDTO;
import com.springbootcrud.springboot_crud.exception.RecordNotFoundException;
import com.springbootcrud.springboot_crud.model.Location;
import com.springbootcrud.springboot_crud.repository.LocationRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationDTOServiceImpl implements ILocationService{

    private final LocationRepository locationRepository;

    @Override
    public LocationDTO save(LocationDTO locationDTO) {
        Location location = new Location();
        location.setCity(locationDTO.getCity());
        location.setCountry(locationDTO.getCountry());
        Location savedLoc = locationRepository.save(location);
        locationDTO.setLocationId(savedLoc.getLocationId());
        return locationDTO;
    }

    @Override
    public LocationDTO update(Integer id , LocationDTO locationDTO) {
        Location loc = locationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Location not found with id:" + id));
        if(locationDTO.getCity() != null ) loc.setCity(locationDTO.getCity());
        if(locationDTO.getCountry() != null )loc.setCountry(locationDTO.getCountry());

        Location savedLoc = locationRepository.save(loc);
        return new LocationDTO(savedLoc.getLocationId(), savedLoc.getCity(), savedLoc.getCountry());
    }

    @Override
    public LocationDTO findById(Integer locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RecordNotFoundException("Location not found with id:" + locationId));

        return new LocationDTO(location.getLocationId(), location.getCity(),location.getCountry());
    }

    @Override
    public List<LocationDTO> findAl() {
        return locationRepository.findAll().stream().map(
                l -> new LocationDTO(l.getLocationId(), l.getCity(), l.getCountry())).collect(Collectors.toList()
                        );
    }

    @Override
    public void deleteById(Integer locationId) {
        locationRepository.deleteById(locationId);
    }
}
