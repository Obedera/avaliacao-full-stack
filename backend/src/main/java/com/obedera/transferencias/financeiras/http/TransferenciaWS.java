package com.obedera.transferencias.financeiras.http;

import com.obedera.transferencias.financeiras.http.domain.request.TransferenciaRequest;
import com.obedera.transferencias.financeiras.http.domain.response.HistoricoTransferenciaResponse;
import com.obedera.transferencias.financeiras.http.domain.response.TransferenciaResponse;
import com.obedera.transferencias.financeiras.usecase.TransferenciaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.obedera.transferencias.financeiras.http.URLMapping.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = ROOT_API_WS_TRANSFERENCIA, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransferenciaWS {

    @Autowired
    private final TransferenciaUseCase transferenciaUseCase;

    @PostMapping(path = SAVE_TRANSFERENCIA_PATH, produces = APPLICATION_JSON_VALUE)
    public TransferenciaResponse registrarTransferencia(@RequestBody TransferenciaRequest request){
        return transferenciaUseCase.salvarTransferencia(request);
    }

    @PostMapping(path = RESUME_TRANSFERENCIA_PATH, produces = APPLICATION_JSON_VALUE)
    public TransferenciaResponse resumoTransferencia(@RequestBody TransferenciaRequest request){
        System.out.println(request);
        return transferenciaUseCase.resumoTransferencia(request);
    }

    @GetMapping(path = LIST_TRANSFERENCIA_PATH, produces = APPLICATION_JSON_VALUE)
    public HistoricoTransferenciaResponse historicoTransferencias(){
        return transferenciaUseCase.listarTransferencias();
    }
}
