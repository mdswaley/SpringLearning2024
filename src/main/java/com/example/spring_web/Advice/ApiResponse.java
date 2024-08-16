package com.example.spring_web.Advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ApiResponse<T>{ // Generics allow you to create classes, interfaces,
    // and methods that can operate on any type of data while maintaining type safety.
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse() { // default constructor
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this(); //default constructor call.
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this(); //default constructor call.
        this.error = error;
    }
}
