package com.springbootcrud.springboot_crud.service;

import com.springbootcrud.springboot_crud.dto.EmployeeDTO;
import com.springbootcrud.springboot_crud.exception.RecordNotFoundException;
import com.springbootcrud.springboot_crud.mapper.EmployeeMapper;
import com.springbootcrud.springboot_crud.model.Employee;
import com.springbootcrud.springboot_crud.model.EmployeeLocation;
import com.springbootcrud.springboot_crud.model.Location;
import com.springbootcrud.springboot_crud.repository.EmployeeLocationRepository;
import com.springbootcrud.springboot_crud.repository.EmployeeRepository;
import com.springbootcrud.springboot_crud.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeDTOServiceImpl implements  IEmployeeDTOService{

    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;
    private final EmployeeLocationRepository employeeLocationRepository;


    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {

        // save employee
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee savedEmp = employeeRepository.save(employee);
        Location savedLocation = null;

        // associate location if provided
        if(employeeDTO.getLocationId() != null) {
            Location loc = locationRepository.findById(employeeDTO.getLocationId()).orElseThrow(
                            ()-> new RecordNotFoundException("Location not found for id:"+ employeeDTO.getLocationId())
                            );
            EmployeeLocation employeeLocation =  EmployeeMapper.toEmployeeLocation(savedEmp.getEmpId() , loc);
            employeeLocationRepository.save(employeeLocation);

        } else {
            Location location = new Location();
            location.setCity(employeeDTO.getLocationCity());
            location.setCountry(employeeDTO.getLocationCountry());
            savedLocation = locationRepository.save(location);
        }

        EmployeeLocation employeeLocation = EmployeeMapper.toEmployeeLocation(savedEmp.getEmpId() , savedLocation);
        employeeLocationRepository.save(employeeLocation);

        EmployeeLocation empLoc =  employeeLocationRepository.findById(savedEmp.getEmpId()).orElse(null);
        return EmployeeMapper.toEmpDTO(savedEmp , empLoc);

    }

    @Override
    public EmployeeDTO findById(Integer empId) {
         Employee emp = employeeRepository.findById(empId).orElseThrow( () -> new RecordNotFoundException("Record not found for id:" + empId));
         EmployeeLocation empLoc = employeeLocationRepository.findById(empId).orElse(null);

        return EmployeeMapper.toEmpDTO(emp , empLoc);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> allEmps = employeeRepository.findAll();
        return allEmps.stream().map( e -> {
            EmployeeLocation el = employeeLocationRepository.findById(e.getEmpId()).orElse(null);
            return EmployeeMapper.toEmpDTO(e , el);
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO update(Integer empId , EmployeeDTO employeeDTO) {
        Employee existing = employeeRepository.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee not found with id: " + empId));
        if (employeeDTO.getEmpName() != null) existing.setEmpName(employeeDTO.getEmpName());
        if (employeeDTO.getEmpSalary() != null) existing.setEmpSalary(employeeDTO.getEmpSalary());

        Employee saved = employeeRepository.save(existing);

        // update location association:
        if (employeeDTO.getLocationId() != null) {
            Location loc = locationRepository.findById(employeeDTO.getLocationId()).orElseThrow(
                    () -> new RecordNotFoundException("Location not found with id: " + employeeDTO.getLocationId())
            );
            EmployeeLocation el = EmployeeMapper.toEmployeeLocation(saved.getEmpId(), loc);
            employeeLocationRepository.save(el);
        } else {
            // if locationId null, remove association if present
            employeeLocationRepository.findById(empId).ifPresent(employeeLocationRepository::delete);
        }

        EmployeeLocation el = employeeLocationRepository.findById(saved.getEmpId()).orElse(null);
        return EmployeeMapper.toEmpDTO(saved, el);
    }

    @Override
    public void deleteById(Integer empId) {
        // delete mapping first (if exists)
        employeeLocationRepository.findById(empId).ifPresent(employeeLocationRepository::delete);
        employeeRepository.deleteById(empId);
    }
}
