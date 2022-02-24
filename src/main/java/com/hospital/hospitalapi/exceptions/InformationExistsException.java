
package com.hospital.hospitalapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistsException extends RuntimeException{
    public InformationExistsException(String message){
        super(message);
    }
}