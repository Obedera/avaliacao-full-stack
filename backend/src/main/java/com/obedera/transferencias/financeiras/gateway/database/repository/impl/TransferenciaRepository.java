package com.obedera.transferencias.financeiras.gateway.database.repository.impl;

import com.obedera.transferencias.financeiras.gateway.database.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    TransferenciaEntity save(TransferenciaEntity transferenciaEntity);

}
