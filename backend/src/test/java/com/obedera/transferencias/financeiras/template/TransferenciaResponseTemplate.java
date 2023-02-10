package com.obedera.transferencias.financeiras.template;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.obedera.transferencias.financeiras.http.domain.request.TransferenciaRequest;
import com.obedera.transferencias.financeiras.http.domain.response.TransferenciaResponse;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.six2six.fixturefactory.Fixture.of;

public class TransferenciaResponseTemplate implements TemplateLoader {

    public static final String VALID_TRANSFER_RESPONSE = UUID.randomUUID().toString();
    public static final String VALID_RESUME_TRANSFER_RESPONSE = UUID.randomUUID().toString();

    @Override
    public void load() {
        of(TransferenciaResponse.class).addTemplate(VALID_TRANSFER_RESPONSE, new Rule(){
            {
                add("id", 1);
                add("contaOrigem", "123456");
                add("contaDestino", "123456");
                add("valor", new BigDecimal("100.00"));
                add("taxa", new BigDecimal("6.00"));
                add("dataAgendamento", "2023-01-19");
                add("dataTransferencia", "2023-01-19");
            }
        });

        of(TransferenciaResponse.class).addTemplate(VALID_RESUME_TRANSFER_RESPONSE, new Rule(){
            {
                add("id", null);
                add("contaOrigem", "123456");
                add("contaDestino", "123456");
                add("valor", new BigDecimal("100.00"));
                add("taxa", new BigDecimal("6.00"));
                add("dataAgendamento", "2023-01-19");
                add("dataTransferencia", "2023-01-19");
            }
        });
    }

}
