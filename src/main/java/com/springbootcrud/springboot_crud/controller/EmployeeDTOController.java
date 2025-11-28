package com.springbootcrud.springboot_crud.controller;


import com.springbootcrud.springboot_crud.dto.EmployeeDTO;
import com.springbootcrud.springboot_crud.model.Employee;
import com.springbootcrud.springboot_crud.service.IEmployeeDTOService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/dto/employees")
@RequiredArgsConstructor
public class EmployeeDTOController {

    private final IEmployeeDTOService  employeeDTOService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(employeeDTOService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById (@PathVariable Integer id) {
        return ResponseEntity.ok(employeeDTOService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> save (@RequestBody EmployeeDTO dto) {
        EmployeeDTO saved = employeeDTOService.save(dto);
        URI location = URI.create("/api/dto/employees/" + saved.getEmpId());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Integer id , @RequestBody EmployeeDTO dto) {
        EmployeeDTO updated = employeeDTOService.update(id , dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete (@PathVariable Integer id) {
        employeeDTOService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
