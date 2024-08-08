package com.example.spring_web.DTO;

import java.time.LocalDate;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate date;
    private Boolean isActive;

    public EmployeeDTO(){

    }
    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate date, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.date = date;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
