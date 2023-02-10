package com.obedera.transferencias.financeiras.usecase;

import com.obedera.transferencias.financeiras.gateway.database.repository.TaxTransferenciaRepositoryFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class TaxTransferenciaUseCase {

    @Autowired
    private TaxTransferenciaRepositoryFacade taxTransferenciaRepositoryFacade;

    public BigDecimal calcularTaxaTransferencia(BigDecimal valorTransferencia, LocalDate dataAgendamento, LocalDate dataTransferencia) {
        var taxTransfer = taxTransferenciaRepositoryFacade.getTaxTransferencia(valorTransferencia, Integer.valueOf((int) ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia)));
        if(taxTransfer.getPorcentagemTax() != null && taxTransfer.getPorcentagemTax() != new BigDecimal("0.00")){
            return ((valorTransferencia.multiply(taxTransfer.getPorcentagemTax())).divide(new BigDecimal("100"))).add(taxTransfer.getTaxFixa());
        }
        return  taxTransfer.getTaxFixa();
    }
}
