package com.example.spring_web.Controller;

import com.example.spring_web.DTO.EmployeeDTO;
import com.example.spring_web.Entity.EmployeeEntity;
import com.example.spring_web.Repository.EmployeeRepository;
import com.example.spring_web.Services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    @GetMapping(path = "/{empId}") //ResponseEntity is use for if you give id is not present in database then notify us.
    // using status code
    public ResponseEntity<EmployeeDTO> getDataById(@PathVariable(name = "empId") Long id){
//        return employeeRepository.findById(id).orElse(null);
//        or
        Optional<EmployeeDTO> employeeDTO =  employeeService.getDataById(id);
        return employeeDTO
                .map((employeeDTO1 -> ResponseEntity.ok(employeeDTO1)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAll(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
       return ResponseEntity.ok(employeeService.getAllData());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addData(@RequestBody EmployeeDTO input){
        EmployeeDTO employeeDTO = employeeService.addData(input);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

//    when user try to update data, but they only provide in json data some property not send all the data to update
//    then after update or click send data in result the id which data you updated some data you provide only
//    that data show or update and rest of field data showing null value.
    @PutMapping(path="/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeData(@RequestBody EmployeeDTO employeeDTO,@PathVariable(name = "empId") Long id){
        return ResponseEntity.ok(employeeService.updateEmployeeData(id,employeeDTO));
    }

    @DeleteMapping(path = "/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long empId){
        boolean isDelete = employeeService.deleteEmployee(empId);
        if (isDelete) return ResponseEntity.ok("deleted id "+empId);
        return ResponseEntity.notFound().build();
    }

//    PatchMapping is use for partially update the data.
//    we use Map bcz we don't want same like as PutMapping.
//    Map<String, Objects> String is our key or property and object is our value bcz it is any type
    @PatchMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> updatePartiallyData(@RequestBody Map<String, Object> updates,@PathVariable Long empId){
        EmployeeDTO employeeDTO =  employeeService.updatePartiallyData(updates,empId);
        if (employeeDTO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(employeeDTO);
        }
    }








}
