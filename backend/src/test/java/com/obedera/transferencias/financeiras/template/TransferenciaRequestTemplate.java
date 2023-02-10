package com.obedera.transferencias.financeiras.template;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.obedera.transferencias.financeiras.http.domain.request.TransferenciaRequest;

import java.math.BigDecimal;
import java.util.UUID;
import static br.com.six2six.fixturefactory.Fixture.of;


public class TransferenciaRequestTemplate implements TemplateLoader {

    public static final String VALID_TRANSFER_REQUEST = UUID.randomUUID().toString();

    @Override
    public void load() {
        of(TransferenciaRequest.class).addTemplate(VALID_TRANSFER_REQUEST, new Rule(){
            {
                add("contaOrigem", "123456");
                add("contaDestino", "123456");
                add("valor", new BigDecimal("100.00"));
                add("dataAgendamento", "2023-01-19");
                add("dataTransferencia", "2023-01-19");
            }
        });
    }

}
