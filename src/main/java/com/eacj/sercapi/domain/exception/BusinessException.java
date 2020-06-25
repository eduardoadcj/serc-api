
package com.eacj.sercapi.domain.exception;


public class BusinessException extends RuntimeException{

    private String error;
    
    public BusinessException(String error, String message) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
    
}
