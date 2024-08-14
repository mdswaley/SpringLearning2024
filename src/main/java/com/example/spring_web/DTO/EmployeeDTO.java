package com.example.spring_web.DTO;

import com.example.spring_web.annotations.EmployeeAgeValidation;
import com.example.spring_web.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Should pass name.") //when ever you pass data without NotNull validation it shows error status code.
    @NotEmpty(message = "should greater then Zero") // length of the string should be greater than 0.
    @NotBlank(message = "Should pass the String") // trimmed length should greater the 0 means no blank text.
    @Size(min = 3 , max = 10, message = "name should be in the range of [3,11]") //define size of the field.
    private String name;

    @Email(message = "email should be in valid format") //valid email pass only.
    @NotNull(message = "Should pass email.")
    @NotBlank(message = "Should pass the String")
    private String email;

    @Max(value = 60 , message = "age should be less than 60")
    @Min(value = 25,message = "age should be greater then 25")
    @NotNull
    @EmployeeAgeValidation
    private Integer age;

    @Past(message = "only past date is allow")
    @PastOrPresent(message = "only past or present date is allow")
    private LocalDate date;
    @JsonProperty("isActive") //JsonProperty we use bcz of confusion like in isActive it confuse json object .
//    so we specify the json to isActive is our property whose name is isActive.
    private Boolean isActive;

//    @Pattern(regexp = "^(USER|ADMIN)$",message = "Only USER or ADMIN is allow")
    @EmployeeRoleValidation //Custom annotation logic.
    private String role;

    @NotNull
    @Positive(message = "should be positive salary.")
    @Digits(integer = 5,fraction = 2,message = "salary should be in the form of max 5 Digit before fraction and after 2 number")
    @DecimalMin(value = "1000.89")
    @DecimalMax(value = "70000.66")
    private Double salary;

}
