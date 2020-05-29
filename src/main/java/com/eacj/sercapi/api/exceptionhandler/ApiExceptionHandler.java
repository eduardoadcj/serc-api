
package com.eacj.sercapi.api.exceptionhandler;

import com.eacj.sercapi.domain.exception.BusinessException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex,
            WebRequest request){
        
        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(ex.getMessage());
        error.setTime(OffsetDateTime.now());
        
        return handleExceptionInternal(ex, error, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
        
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        List<Error.Field> fields = new ArrayList<>();
        
        for(ObjectError objError : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) objError).getField();
            String message = objError.getDefaultMessage();
            fields.add(new Error.Field(name, message));
        }
        
        Error error = new Error();
        error.setStatus(status.value());
        error.setTitle("Um ou mais campos estão inválidos");
        error.setTime(OffsetDateTime.now());
        error.setFields(fields);
        
        return super.handleExceptionInternal(ex, error, headers, status, request);
        
    }
    
}
