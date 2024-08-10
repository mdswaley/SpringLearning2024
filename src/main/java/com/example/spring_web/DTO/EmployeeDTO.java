package com.example.spring_web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//DTO is use for take data from client in json format.
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate date;
    @JsonProperty("isActive") //JsonProperty we use bcz of confusion like in isActive it confuse json object .
//    so we specify the json to isActive is our property whose name is isActive.
    private Boolean isActive;

}
