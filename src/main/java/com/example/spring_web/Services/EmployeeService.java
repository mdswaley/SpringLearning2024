package com.example.spring_web.Services;

import com.example.spring_web.Configs.ModelConfig;
import com.example.spring_web.DTO.EmployeeDTO;
import com.example.spring_web.Entity.EmployeeEntity;
import com.example.spring_web.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper ){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDTO getDataById(Long id) {
       EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
//       return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName());
        ModelMapper mapper = new ModelMapper();
        return mapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllData() {
//        first we collect all  data from employeeRep to List of Employee entity
        List<EmployeeEntity> employeeEntityes = employeeRepository.findAll();
        return employeeEntityes.stream()
                .map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class))
                .collect(Collectors.toList());
//        we have to return dto so convert employee entity to employee dto class using model class .
    }

    public EmployeeDTO addData(EmployeeDTO input) {
//        As we take parameter as DTO type so for save data we have to convert to entity type
        EmployeeEntity toSaveEntity = modelMapper.map(input,EmployeeEntity.class);
//        then we save our data into entity
        EmployeeEntity saveEmpEntity = employeeRepository.save(toSaveEntity);
//        Now we return again converting entity to DTO
        return modelMapper.map(saveEmpEntity,EmployeeDTO.class);
    }
}
