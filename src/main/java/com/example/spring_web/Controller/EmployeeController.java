package com.example.spring_web.Controller;

import com.example.spring_web.DTO.EmployeeDTO;
import com.example.spring_web.Entity.EmployeeEntity;
import com.example.spring_web.Repository.EmployeeRepository;
import com.example.spring_web.Services.EmployeeService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@Controller :- not contain @ResponseBody annotation
@RestController // contain @ResponseBody annotation
@RequestMapping(path="/employeeParent") //parent path if you add like this then in child method
// any request in that top first it add like getData method URL should be /employeeParent/employee/2 .
public class EmployeeController {



    private final EmployeeService employeeService;

//    we use service bcz we want our code look scalable and readable
//    if we use repository then it difficult to manage security and login . we write code multiple time
//    so service can handle all business login inside that.

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/{empId}")
    public EmployeeDTO getDataById(@PathVariable(name = "empId") Long id){
//        return employeeRepository.findById(id).orElse(null);
//        or
        return employeeService.getDataById(id);
    }

    @GetMapping()
    public List<EmployeeDTO> getAll(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return employeeService.getAllData();
    }

    @PostMapping
    public EmployeeDTO addData(@RequestBody EmployeeDTO input){
        return employeeService.addData(input);
    }

    @PutMapping
    public String updateData(){
        return "Update Data Successfully";
    }






}
