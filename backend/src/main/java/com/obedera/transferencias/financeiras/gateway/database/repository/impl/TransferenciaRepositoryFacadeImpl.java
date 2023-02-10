package com.obedera.transferencias.financeiras.gateway.database.repository.impl;

import com.obedera.transferencias.financeiras.gateway.database.entity.TransferenciaEntity;
import com.obedera.transferencias.financeiras.gateway.database.repository.TransferenciaRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferenciaRepositoryFacadeImpl implements TransferenciaRepositoryFacade {

    @Autowired
    private final TransferenciaRepository transferenciaRepository;

    @Override
    public TransferenciaEntity save(TransferenciaEntity transferenciaEntity){
        return transferenciaRepository.save(transferenciaEntity);
    }

}
