package com.example.spring_web.Services;

import com.example.spring_web.DTO.EmployeeDTO;
import com.example.spring_web.Entity.EmployeeEntity;
import com.example.spring_web.Exception.ResourceNotFound;
import com.example.spring_web.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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


    public Optional<EmployeeDTO> getDataById(Long id) {
//       Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//       return  employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));

//      or
       return employeeRepository.findById(id).map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));


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

    public EmployeeDTO updateEmployeeData(Long id, EmployeeDTO employeeDTO) {
        isExistData(id);
//        if given id of employee is not present
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
//        then create the employee
//        if present then update the employee data with set id.
        employeeEntity.setId(id);
//        save the data
        EmployeeEntity saveEmpEntity = employeeRepository.save(employeeEntity);
//        return
        return modelMapper.map(saveEmpEntity,EmployeeDTO.class);
    }


    public void isExistData(Long empId){
        boolean isExist = employeeRepository.existsById(empId);
        if (!isExist) throw new ResourceNotFound("Not found Id which you want to Delete.");
    }
    public boolean deleteEmployee(Long empId) {
        isExistData(empId);
        employeeRepository.deleteById(empId);
        return true;
    }

    public EmployeeDTO updatePartiallyData(Map<String, Object> updates, Long empId) {
        isExistData(empId);
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        updates.forEach((field,value)->{
//            ReflectionUtils is java class which is use for update any class fields.
//            find passing field and store in field object
            Field fieldToUpdate= ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
//            field is other class is private, so we have to convert public so that we can update that field data.
            fieldToUpdate.setAccessible(true);
//            then set the field data new value which is pass by user in json format.
            ReflectionUtils.setField(fieldToUpdate,employeeEntity,value);
        });

//        then simply save the data and convert to dto the return.

        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }
}
