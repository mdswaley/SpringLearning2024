package com.example.spring_web.Exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message); // call super class which is RuntimeException with exception message.
    }
}
