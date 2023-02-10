package com.obedera.transferencias.financeiras.usecase;

import br.com.six2six.fixturefactory.Fixture;
import com.obedera.transferencias.financeiras.exception.TaxTransferenciaNotFoundException;
import com.obedera.transferencias.financeiras.gateway.database.entity.TaxTransferenciaEntity;
import com.obedera.transferencias.financeiras.gateway.database.repository.TaxTransferenciaRepositoryFacade;
import com.obedera.transferencias.financeiras.template.Templates;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static com.obedera.transferencias.financeiras.template.TaxTransferenciaTemplate.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxTransferenciaUseCaseTest {

    @BeforeClass
    public static void before() { loadTemplates(Templates.BASE_PACKAGE);}

    @Mock
    private TaxTransferenciaRepositoryFacade taxTransferenciaRepositoryFacade;

    @InjectMocks
    private TaxTransferenciaUseCase taxTransferenciaUseCase;

    @Test
    public void deveObterTaxaTransferenciaTipoA(){
        TaxTransferenciaEntity taxTransferEntity = Fixture.from(TaxTransferenciaEntity.class).gimme(VALID_TAX_TYPE_A);
        when(taxTransferenciaRepositoryFacade.getTaxTransferencia(any(), any())).thenReturn(taxTransferEntity);
        var taxValue = taxTransferenciaUseCase.calcularTaxaTransferencia(
                new BigDecimal("100"),
                LocalDate.of(2023, Month.JANUARY, 1),
                LocalDate.of(2023, Month.JANUARY, 1)
        );

        Assert.assertEquals(taxValue, new BigDecimal("6.00"));
    }


    @Test
    public void deveObterTaxaTransferenciaTipoB(){
        TaxTransferenciaEntity taxTransferEntity = Fixture.from(TaxTransferenciaEntity.class).gimme(VALID_TAX_TYPE_B);
        when(taxTransferenciaRepositoryFacade.getTaxTransferencia(any(), any())).thenReturn(taxTransferEntity);
        var taxValue = taxTransferenciaUseCase.calcularTaxaTransferencia(
                new BigDecimal("1500"),
                LocalDate.of(2023, Month.JANUARY, 1),
                LocalDate.of(2023, Month.JANUARY, 2)
        );

        Assert.assertEquals(taxValue, new BigDecimal("12.00"));
    }

    @Test
    public void deveObterTaxaTransferenciaTipoC1(){
        TaxTransferenciaEntity taxTransferEntity = Fixture.from(TaxTransferenciaEntity.class).gimme(VALID_TAX_TYPE_C_1);
        when(taxTransferenciaRepositoryFacade.getTaxTransferencia(any(), any())).thenReturn(taxTransferEntity);
        var taxValue = taxTransferenciaUseCase.calcularTaxaTransferencia(
                new BigDecimal("10000"),
                LocalDate.of(2023, Month.JANUARY, 1),
                LocalDate.of(2023, Month.JANUARY, 12)
        );
        Assert.assertEquals(taxValue, new BigDecimal("820.0"));
    }

    @Test(expected = TaxTransferenciaNotFoundException.class)
    public void deveGerarExcecaoTaxaTransferencia(){
        TaxTransferenciaEntity taxTransferEntity = Fixture.from(TaxTransferenciaEntity.class).gimme(VALID_TAX_TYPE_A);
        when(taxTransferenciaRepositoryFacade.getTaxTransferencia(any(), any())).thenThrow(new TaxTransferenciaNotFoundException(String.format("Taxa inexistente para essa transferencia")));
        taxTransferenciaUseCase.calcularTaxaTransferencia(
                new BigDecimal("900"),
                LocalDate.of(2023, Month.JANUARY, 1),
                LocalDate.of(2023, Month.JANUARY, 12)
        );
    }



}