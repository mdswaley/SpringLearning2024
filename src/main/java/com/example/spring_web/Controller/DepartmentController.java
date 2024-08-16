package com.example.spring_web.Controller;

import com.example.spring_web.Advice.ApiResponse;
import com.example.spring_web.DTO.DepartmentDTO;
import com.example.spring_web.Exception.ResourceNotFound;
import com.example.spring_web.Services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<DepartmentDTO> addDepData(@RequestBody @Valid DepartmentDTO input){
        DepartmentDTO departmentDTO = departmentService.addDepData(input);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepById(@PathVariable(name="id") Long depId){
        Optional<DepartmentDTO> departmentDTO = departmentService.getById(depId);

        return departmentDTO
//                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .map(ResponseEntity::ok) //method reference
                .orElseThrow(()->new ResourceNotFound("Id is not present : "+depId));
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllData(){
        return ResponseEntity.ok(departmentService.getAllDep());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> updateDepData(@RequestBody @Valid DepartmentDTO departmentDTO,@PathVariable(name = "id") Long depId){
        return ResponseEntity.ok(departmentService.updateDep(depId,departmentDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDepById(@PathVariable(name="id") Long depId){
        departmentService.deleteDepById(depId);
        ApiResponse<String> api = new ApiResponse<>("Deleted Successfully id : "+depId);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }





}
