package com.obedera.transferencias.financeiras.http.domain.builder;

import com.obedera.transferencias.financeiras.gateway.database.entity.TransferenciaEntity;
import com.obedera.transferencias.financeiras.http.domain.response.HistoricoTransferenciaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HistoricoTransferenciaBuilder {

    @Autowired
    private TransferenciaBuilder transferenciaBuilder;

    public HistoricoTransferenciaResponse build(List<TransferenciaEntity> entityList){
        return  HistoricoTransferenciaResponse.builder()
                .historicoTransferencias(entityList.stream().map(e -> transferenciaBuilder.build(e)).collect(Collectors.toList()))
                .build();
    }
}
