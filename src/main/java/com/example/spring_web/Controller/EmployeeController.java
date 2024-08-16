package com.example.spring_web.Controller;

import com.example.spring_web.Advice.ApiResponse;
import com.example.spring_web.DTO.EmployeeDTO;
import com.example.spring_web.Entity.EmployeeEntity;
import com.example.spring_web.Exception.ResourceNotFound;
import com.example.spring_web.Repository.EmployeeRepository;
import com.example.spring_web.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.*;

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
//                .orElse(ResponseEntity.notFound().build());
//                .orElseThrow(() -> new NoSuchElementException("not found"));
                  .orElseThrow(() -> new ResourceNotFound("Resource not found id : "+id)); //custom exception create.
    }


    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAll(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
       return ResponseEntity.ok(employeeService.getAllData());
    }

//    @Valid :- is use for checking validation first if all are good request then it go to the service layer.
    @PostMapping
    public ResponseEntity<EmployeeDTO> addData(@RequestBody @Valid EmployeeDTO input){
        EmployeeDTO employeeDTO = employeeService.addData(input);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

//    When a user attempts to update data but only provides some properties in the JSON payload
//    (not all the data), after the update, the returned result should include the ID of the updated record.
//    The response should show the updated fields with their new values, while the fields not provided in the
//    request should retain their original values instead of being set to null.
    @PutMapping(path="/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeData(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable(name = "empId") Long id){
        return ResponseEntity.ok(employeeService.updateEmployeeData(id,employeeDTO));
    }

    @DeleteMapping(path = "/{empId}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long empId){
        boolean isDelete = employeeService.deleteEmployee(empId);
        ApiResponse<String> api = new ApiResponse<>("Deleted successfully"+empId);
        if (isDelete) return new ResponseEntity<>(api,HttpStatus.OK);
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
