package com.obedera.transferencias.financeiras.http.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoTransferenciaResponse {
    private List<TransferenciaResponse> historicoTransferencias;
}
