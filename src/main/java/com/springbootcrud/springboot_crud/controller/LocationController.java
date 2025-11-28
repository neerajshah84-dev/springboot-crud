package com.springbootcrud.springboot_crud.controller;

import com.springbootcrud.springboot_crud.dto.LocationDTO;
import com.springbootcrud.springboot_crud.service.ILocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/dto/locations")
@RequiredArgsConstructor
public class LocationController {
    private final ILocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAll() {
        return ResponseEntity.ok(locationService.findAl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(locationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LocationDTO> create(@RequestBody LocationDTO dto) {
        LocationDTO saved = locationService.save(dto);
        URI location = URI.create("/api/dto/locations/" + saved.getLocationId());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> update(@PathVariable Integer id, @RequestBody LocationDTO dto) {
        return ResponseEntity.ok(locationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
