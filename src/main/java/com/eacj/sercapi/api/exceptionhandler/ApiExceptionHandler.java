
package com.eacj.sercapi.api.exceptionhandler;

import com.eacj.sercapi.api.exception.ApiException;
import com.eacj.sercapi.domain.exception.BusinessException;
import com.eacj.sercapi.domain.exception.EntityNotFoundException;
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
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex,
            WebRequest request){
        
        Error error = new Error();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setError("api_error");
        error.setErrorDescription(ex.getMessage());
        error.setTime(OffsetDateTime.now());
        
        return handleExceptionInternal(ex, error, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
        
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex,
            WebRequest request){
        
        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(ex.getError());
        error.setErrorDescription(ex.getMessage());
        error.setTime(OffsetDateTime.now());
        
        return handleExceptionInternal(ex, error, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
        
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex,
            WebRequest request){
        
        Error error = new Error();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("entity_not_found");
        error.setErrorDescription(ex.getMessage());
        error.setTime(OffsetDateTime.now());
        
        return handleExceptionInternal(ex, error, new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
        
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
        error.setError("validation_error");
        error.setErrorDescription("Um ou mais campos estão inválidos");
        error.setTime(OffsetDateTime.now());
        error.setFields(fields);
        
        return super.handleExceptionInternal(ex, error, headers, status, request);
        
    }
    
}
