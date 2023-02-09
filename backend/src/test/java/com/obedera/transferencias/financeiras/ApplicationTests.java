package com.obedera.transferencias.financeiras;

import com.obedera.transferencias.financeiras.gateway.database.repository.TaxTransferenciaRepositoryFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private TaxTransferenciaRepositoryFacade taxTransferenciaRepositoryFacade;

	@Test
	public void deveObterTaxaTransferencia(){
		taxTransferenciaRepositoryFacade.getTaxTransferencia(new BigDecimal("1000.1"), 10);
	}

}
