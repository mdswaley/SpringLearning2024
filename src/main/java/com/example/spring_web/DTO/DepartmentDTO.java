package com.example.spring_web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Long depId;
    @NotNull
    @NotBlank
    @NotEmpty
    private String title;
    @JsonProperty(value = "isActive")
    private Boolean isActive;
    private String createdAt;
}
