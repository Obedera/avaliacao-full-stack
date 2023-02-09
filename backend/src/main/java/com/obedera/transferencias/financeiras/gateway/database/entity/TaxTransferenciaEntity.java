package com.obedera.transferencias.financeiras.gateway.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="tax_transferencia")
@Data
public class TaxTransferenciaEntity {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="min_valor_transf")
    private BigDecimal minValorTransf;

    @Column(name="max_valor_transf")
    private BigDecimal maxValorTransf;

    @Column(name="min_dias_transf")
    private Integer minDiasTransf;

    @Column(name="max_dias_transf")
    private Integer maxDiasTransf;

    @Column(name="porcentagem_tax")
    private BigDecimal porcentagemTax;

    @Column(name="tax_fixa")
    private BigDecimal taxFixa;

}
