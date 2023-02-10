package com.obedera.transferencias.financeiras.usecase;

import com.obedera.transferencias.financeiras.gateway.database.entity.TransferenciaEntity;
import com.obedera.transferencias.financeiras.gateway.database.repository.TransferenciaRepositoryFacade;
import com.obedera.transferencias.financeiras.http.domain.builder.HistoricoTransferenciaBuilder;
import com.obedera.transferencias.financeiras.http.domain.builder.TransferenciaBuilder;
import com.obedera.transferencias.financeiras.http.domain.request.TransferenciaRequest;
import com.obedera.transferencias.financeiras.http.domain.response.HistoricoTransferenciaResponse;
import com.obedera.transferencias.financeiras.http.domain.response.TransferenciaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class TransferenciaUseCase {

    @Autowired
    private TransferenciaRepositoryFacade transferenciaRepositoryFacade;

    @Autowired
    private TaxTransferenciaUseCase taxTransferenciaUseCase;

    @Autowired
    private TransferenciaBuilder transferenciaBuilder;

    @Autowired
    private HistoricoTransferenciaBuilder historicoTransferenciaBuilder;

    public TransferenciaResponse salvarTransferencia(TransferenciaRequest transferenciaRequest){
        var transferenciaEntity = new TransferenciaEntity();
        transferenciaEntity.setDataTransferencia(convertToDate(transferenciaRequest.getDataTransferencia()));
        transferenciaEntity.setDataAgendamento(convertToDate(transferenciaRequest.getDataAgendamento()));
        transferenciaEntity.setValor(transferenciaRequest.getValor().setScale(2, RoundingMode.DOWN));
        transferenciaEntity.setContaOrigem(transferenciaRequest.getContaOrigem());
        transferenciaEntity.setContaDestino(transferenciaRequest.getContaDestino());
        transferenciaEntity.setTaxa(
                taxTransferenciaUseCase.calcularTaxaTransferencia(
                        transferenciaRequest.getValor(),
                        transferenciaEntity.getDataAgendamento(),
                        transferenciaEntity.getDataTransferencia()
                )
        );
        return transferenciaBuilder.build(transferenciaRepositoryFacade.save(transferenciaEntity));
    }

    public TransferenciaResponse resumoTransferencia(TransferenciaRequest transferenciaRequest){

        var transferenciaEntity = new TransferenciaEntity();
        transferenciaEntity.setDataTransferencia(convertToDate(transferenciaRequest.getDataTransferencia()));
        transferenciaEntity.setDataAgendamento(convertToDate(transferenciaRequest.getDataAgendamento()));
        transferenciaEntity.setValor(transferenciaRequest.getValor().setScale(2, RoundingMode.DOWN));
        transferenciaEntity.setContaOrigem(transferenciaRequest.getContaOrigem());
        transferenciaEntity.setContaDestino(transferenciaRequest.getContaDestino());



            transferenciaEntity.setTaxa(
                    taxTransferenciaUseCase.calcularTaxaTransferencia(
                            transferenciaRequest.getValor(),
                            transferenciaEntity.getDataAgendamento(),
                            transferenciaEntity.getDataTransferencia()
                    )
            );
            return transferenciaBuilder.build(transferenciaEntity);

    }

    public HistoricoTransferenciaResponse listarTransferencias(){
        return historicoTransferenciaBuilder.build(transferenciaRepositoryFacade.findAll());
    }

    private LocalDate convertToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
