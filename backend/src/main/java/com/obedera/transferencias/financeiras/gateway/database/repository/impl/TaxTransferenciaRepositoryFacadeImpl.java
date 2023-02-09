package com.obedera.transferencias.financeiras.gateway.database.repository.impl;

import com.obedera.transferencias.financeiras.exception.TaxTransferenciaNotFoundException;
import com.obedera.transferencias.financeiras.gateway.database.entity.TaxTransferenciaEntity;
import com.obedera.transferencias.financeiras.gateway.database.repository.TaxTransferenciaRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TaxTransferenciaRepositoryFacadeImpl implements TaxTransferenciaRepositoryFacade {

    @Autowired
    private final TaxTransferenciaRepository taxTransferenciaRepository;

    @Override
    public TaxTransferenciaEntity getTaxTransferencia(BigDecimal valorTransf, Integer diasTransf) {
        return taxTransferenciaRepository.getTaxTransferencia(valorTransf, diasTransf)
                .orElseThrow(() -> new TaxTransferenciaNotFoundException(String.format("Taxa inexistente para essa transferencia")));
    }





}
