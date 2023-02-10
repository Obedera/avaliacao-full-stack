package com.obedera.transferencias.financeiras.gateway.database.repository;

import com.obedera.transferencias.financeiras.gateway.database.entity.TaxTransferenciaEntity;

import java.math.BigDecimal;

public interface TaxTransferenciaRepositoryFacade {

    public TaxTransferenciaEntity getTaxTransferencia(BigDecimal valorTransf, Integer diasTransf);

}
