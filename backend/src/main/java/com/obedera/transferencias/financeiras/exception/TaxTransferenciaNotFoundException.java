package com.obedera.transferencias.financeiras.exception;

import jakarta.persistence.EntityNotFoundException;

public class TaxTransferenciaNotFoundException extends EntityNotFoundException {

    public TaxTransferenciaNotFoundException(String message) {
        super(message);
    }

}
