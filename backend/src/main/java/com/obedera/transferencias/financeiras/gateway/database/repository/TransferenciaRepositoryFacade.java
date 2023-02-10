package com.obedera.transferencias.financeiras.gateway.database.repository;

import com.obedera.transferencias.financeiras.gateway.database.entity.TransferenciaEntity;

import java.util.List;


public interface TransferenciaRepositoryFacade {

    public TransferenciaEntity save(TransferenciaEntity transferenciaEntity);

    public List<TransferenciaEntity> findAll();

}
