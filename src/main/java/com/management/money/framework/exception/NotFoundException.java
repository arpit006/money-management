package com.management.money.framework.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author <a href = "mailto: iarpitsrivastava06@gmail.com"> Arpit Srivastava</a>
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Splitwise does not finds any mathcing records!.")
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
