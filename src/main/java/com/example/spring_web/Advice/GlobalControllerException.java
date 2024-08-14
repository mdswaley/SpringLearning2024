package com.example.spring_web.Advice;

import com.example.spring_web.Exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice // All controller exception handel
public class GlobalControllerException {

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Resource was Not found.", HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFound exception){
        ApiError api = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(api,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllException(Exception exception){
        ApiError api = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(api,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception){

        List<String> errors = exception
                .getBindingResult() //bind all error
                .getAllErrors() // get all error
                .stream()// convert in one collection
                .map(error->error.getDefaultMessage()) //default error msg
                .collect(Collectors.toList());// collect to the list

        ApiError api = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .subError(errors)
                .build();
        return new ResponseEntity<>(api,HttpStatus.BAD_REQUEST);
    }

}
