package com.example.spring_web.Advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
/*The supports method determines whether the advice applies to a particular controller method's return type.
By returning true, it indicates that this advice should be applied to all controller methods, regardless of their return type.*/

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        /*If the body is already wrapped in an ApiResponse object (which is a generic wrapper for responses),
        it returns the body as is.*/
        if (body instanceof ApiResponse<?>){
            return body;
        }

        return new ApiResponse<>(body);
    }
}
