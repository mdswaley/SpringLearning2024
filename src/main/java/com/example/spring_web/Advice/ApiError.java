package com.example.spring_web.Advice;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//or
@Data
@Builder //to use easily.
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subError;

}
