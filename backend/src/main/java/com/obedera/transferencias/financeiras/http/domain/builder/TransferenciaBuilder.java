package com.obedera.transferencias.financeiras.http.domain.builder;

import com.obedera.transferencias.financeiras.gateway.database.entity.TransferenciaEntity;
import com.obedera.transferencias.financeiras.http.domain.request.TransferenciaRequest;
import com.obedera.transferencias.financeiras.http.domain.response.TransferenciaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransferenciaBuilder {

    public TransferenciaResponse build(TransferenciaEntity entity){
        return TransferenciaResponse.builder()
                .id(entity.getId())
                .dataAgendamento(entity.getDataAgendamento().toString())
                .dataTransferencia(entity.getDataTransferencia().toString())
                .taxa(entity.getTaxa())
                .contaDestino(entity.getContaDestino())
                .contaOrigem(entity.getContaOrigem())
                .valor(entity.getValor())
                .build();

    }
}
