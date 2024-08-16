package com.example.spring_web.Services;

import com.example.spring_web.DTO.DepartmentDTO;
import com.example.spring_web.DTO.EmployeeDTO;
import com.example.spring_web.Entity.DepartmentEntity;
import com.example.spring_web.Entity.EmployeeEntity;
import com.example.spring_web.Exception.ResourceNotFound;
import com.example.spring_web.Repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository,ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;
        this.modelMapper=modelMapper;
    }

    public DepartmentDTO addDepData(DepartmentDTO input) {
        DepartmentEntity departmentEntity = modelMapper.map(input, DepartmentEntity.class);
        DepartmentEntity saveEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(saveEntity,DepartmentDTO.class);
    }

    public Optional<DepartmentDTO> getById(Long id){
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        return departmentEntity.map(departmentEntity1 -> modelMapper.map(departmentEntity1,DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDep(){
        List<DepartmentEntity> departmentEntity = departmentRepository.findAll();

        return departmentEntity
                .stream()
                .map(departmentEntity1 -> modelMapper.map(departmentEntity1,DepartmentDTO.class))
                .collect(Collectors.toList());
    }


    public DepartmentDTO updateDep(Long id,DepartmentDTO departmentDTO){
        boolean isPresent = departmentRepository.existsById(id);
        if (!isPresent) throw new ResourceNotFound("Give Id is not present");

        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO,DepartmentEntity.class);

        departmentEntity.setDepId(id);

        DepartmentEntity saveUpdatedDep = departmentRepository.save(departmentEntity);

        return modelMapper.map(saveUpdatedDep,DepartmentDTO.class);
    }

    public void isExistData(Long empId){
        boolean isExist = departmentRepository.existsById(empId);
        if (!isExist) throw new ResourceNotFound("Not found Id which you want to Delete.");
    }
    public void deleteDepById(Long id){
        isExistData(id);
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Not found Id which you want to delete."));

        // Delete the department entity
        departmentRepository.deleteById(id);
    }





}
