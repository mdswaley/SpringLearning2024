package com.example.spring_web.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Table(name = "Department")
@Entity
@Data
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long depId;
    private String title;
    @JsonProperty(value = "isActive")
    private Boolean isActive;
    private String createdAt;
}
