package de.format.CarIdent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContractDatabaseException extends RuntimeException {

    public ContractDatabaseException(String message) {
        super(message);
    }
}
