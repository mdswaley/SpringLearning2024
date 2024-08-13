package com.example.spring_web.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "Employee")
@Getter //lombok dependencies
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Entity is use for create table inside our database. And it returns what ever data we have in table is give in json format.
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate date;
    @JsonProperty("isActive")//JsonProperty we use bcz of confusion like in isActive it confuse json object .
//    so we specify the json to isActive is our property whose name is isActive.
    private Boolean isActive;
    private String role;

    private Double salary;
}
