package com.obedera.transferencias.financeiras.template;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.obedera.transferencias.financeiras.http.domain.response.HistoricoTransferenciaResponse;
import com.obedera.transferencias.financeiras.http.domain.response.TransferenciaResponse;

import java.util.UUID;

import static br.com.six2six.fixturefactory.Fixture.of;
import static com.obedera.transferencias.financeiras.template.TransferenciaResponseTemplate.VALID_TRANSFER_RESPONSE;

public class HistoricoTransferenciaResponseTemplate implements TemplateLoader {
    public static final String VALID_HISTORY_TRANSFER_RESPONSE = UUID.randomUUID().toString();

    @Override
    public void load() {
        of(HistoricoTransferenciaResponse.class).addTemplate(VALID_HISTORY_TRANSFER_RESPONSE, new Rule() {
            {
                add("historicoTransferencias", has(1).of(TransferenciaResponse.class, VALID_TRANSFER_RESPONSE));
            }
        });
    }
}
