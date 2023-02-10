package com.obedera.transferencias.financeiras.template;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.obedera.transferencias.financeiras.gateway.database.entity.TaxTransferenciaEntity;
import java.math.BigDecimal;
import java.util.UUID;
import static br.com.six2six.fixturefactory.Fixture.of;

public class TaxTransferenciaTemplate implements TemplateLoader {
    public static final String VALID_TAX_TYPE_A = UUID.randomUUID().toString();
    public static final String VALID_TAX_TYPE_B = UUID.randomUUID().toString();

    public static final String VALID_TAX_TYPE_C_1 = UUID.randomUUID().toString();

    @Override
    public void load() {
        of(TaxTransferenciaEntity.class)
                .addTemplate(VALID_TAX_TYPE_A, new Rule() {
                    {
                        add("minValorTransf", null);
                        add("maxValorTransf", new BigDecimal("1000"));
                        add("minDiasTransf", null);
                        add("maxDiasTransf", 0);
                        add("porcentagemTax", new BigDecimal("3.00"));
                        add("taxFixa", new BigDecimal("3.00"));
                    }
                });
        of(TaxTransferenciaEntity.class)
                .addTemplate(VALID_TAX_TYPE_B, new Rule() {
                    {
                        add("minValorTransf", new BigDecimal("1000"));
                        add("maxValorTransf", new BigDecimal("2000"));
                        add("minDiasTransf", null);
                        add("maxDiasTransf", 10);
                        add("porcentagemTax", new BigDecimal("0"));
                        add("taxFixa", new BigDecimal("12.00"));
                    }
                });

        of(TaxTransferenciaEntity.class)
                .addTemplate(VALID_TAX_TYPE_C_1, new Rule() {
                    {
                        add("minValorTransf", new BigDecimal("2000"));
                        add("maxValorTransf", null);
                        add("minDiasTransf", 10);
                        add("maxDiasTransf", 20);
                        add("porcentagemTax", new BigDecimal("8.2"));
                        add("taxFixa", new BigDecimal("0"));
                    }
                });
    }

}
