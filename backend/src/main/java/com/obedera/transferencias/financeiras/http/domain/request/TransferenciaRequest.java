package com.obedera.transferencias.financeiras.http.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaRequest {

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private String dataAgendamento;
    private String dataTransferencia;
}
