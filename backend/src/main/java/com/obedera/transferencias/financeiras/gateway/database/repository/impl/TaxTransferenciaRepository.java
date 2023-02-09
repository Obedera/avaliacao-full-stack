package com.obedera.transferencias.financeiras.gateway.database.repository.impl;

import com.obedera.transferencias.financeiras.gateway.database.entity.TaxTransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface TaxTransferenciaRepository extends JpaRepository<TaxTransferenciaEntity, Integer> {

    @Query(value = "select * from tax_transferencia\n" +
            "where \n" +
            "(:valorTransf > min_valor_transf or min_valor_transf is NULL) and \n" +
            "(:valorTransf <= max_valor_transf or max_valor_transf is NULL) and\n" +
            "(:diasTransf > min_dias_transf or min_dias_transf is NULL) and\n" +
            "(:diasTransf <= max_dias_transf or max_dias_transf is NULL) limit 1", nativeQuery = true)

    Optional <TaxTransferenciaEntity> getTaxTransferencia(BigDecimal valorTransf, Integer diasTransf);
}
