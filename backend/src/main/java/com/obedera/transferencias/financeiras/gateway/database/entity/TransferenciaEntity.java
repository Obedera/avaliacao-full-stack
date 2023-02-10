package com.obedera.transferencias.financeiras.gateway.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="transferencias")
@Data
public class TransferenciaEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="conta_origem")
    private String contaOrigem;

    @Column(name="conta_destino")
    private String contaDestino;

    @Column(name="valor")
    private BigDecimal valor;

    @Column(name="taxa")
    private BigDecimal taxa;

    @Column(name="data_agendamento")
    private LocalDate dataAgendamento;

    @Column(name="data_transferencia")
    private LocalDate dataTransferencia;

}
