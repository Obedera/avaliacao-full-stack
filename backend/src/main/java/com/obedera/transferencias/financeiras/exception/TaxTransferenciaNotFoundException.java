package com.obedera.transferencias.financeiras.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaxTransferenciaNotFoundException extends EntityNotFoundException {

    public TaxTransferenciaNotFoundException(String message) {
        super(message);
    }

}
