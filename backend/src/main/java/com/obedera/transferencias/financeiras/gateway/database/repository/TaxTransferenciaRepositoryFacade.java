package com.obedera.transferencias.financeiras.gateway.database.repository;

import com.obedera.transferencias.financeiras.gateway.database.entity.TaxTransferenciaEntity;

import java.math.BigDecimal;
import java.util.Optional;

public interface TaxTransferenciaRepositoryFacade {

    public TaxTransferenciaEntity getTaxTransferencia(BigDecimal valorTransf, Integer diasTransf);

}
