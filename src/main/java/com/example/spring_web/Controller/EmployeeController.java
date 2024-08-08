package com.example.spring_web.Controller;

import com.example.spring_web.DTO.EmployeeDTO;
import com.example.spring_web.Entity.EmployeeEntity;
import com.example.spring_web.Repository.EmployeeRepository;
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

//    @GetMapping(path = "/getSecret")
//    private String getMsg(){
//        return "Spring is start with id:abcd@1234";
//    }

//    @GetMapping(path = "/employee/{empId}")
//    public EmployeeDTO getData(@PathVariable(name = "empId") Long id){
////        return new EmployeeDTO(id,"MD","md@gmail.com",21, LocalDate.of(2024,03,02),true);
//        return new EmployeeDTO(id,"MD","md@gmail.com",21, LocalDate.now(),true);
//    }
//    @PathVariable it is mandatory Data bcz path is contained with extra param .
//    so you have to provide otherwise it show error.
//    @PathVariable(name = "empId") mean you change name in code id but use as like empId.

//    @GetMapping(path = "/employee")
//    public String getAge(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
//        return "Age is "+age+" "+sortBy;
//    }
//    if you use RequestParam then the path is option
//    required = false mean if you not provide any one of these param then it not show any error.but by default it is true.

//    @PostMapping
//    public EmployeeDTO addData(@RequestBody EmployeeDTO input){
//        input.setId(1001L);
//        return input;
//    }

    @PutMapping
    public String updateData(){
        return "Update Data Successfully";
    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/employee/{empId}")
    public Optional<EmployeeEntity> getData(@PathVariable(name = "empId") Long id){
//        return employeeRepository.findById(id).orElse(null);
//        or
        return employeeRepository.findById(id);
    }

    @GetMapping(path = "/employee")
    public List<EmployeeEntity> getAll(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity addData(@RequestBody EmployeeEntity input){
        return employeeRepository.save(input);
    }






}
