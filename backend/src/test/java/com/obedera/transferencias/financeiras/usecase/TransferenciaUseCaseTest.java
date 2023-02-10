package com.obedera.transferencias.financeiras.usecase;

import br.com.six2six.fixturefactory.Fixture;
import com.obedera.transferencias.financeiras.gateway.database.entity.TaxTransferenciaEntity;
import com.obedera.transferencias.financeiras.gateway.database.entity.TransferenciaEntity;
import com.obedera.transferencias.financeiras.gateway.database.repository.TaxTransferenciaRepositoryFacade;
import com.obedera.transferencias.financeiras.gateway.database.repository.TransferenciaRepositoryFacade;
import com.obedera.transferencias.financeiras.http.domain.builder.HistoricoTransferenciaBuilder;
import com.obedera.transferencias.financeiras.http.domain.builder.TransferenciaBuilder;
import com.obedera.transferencias.financeiras.http.domain.request.TransferenciaRequest;
import com.obedera.transferencias.financeiras.http.domain.response.HistoricoTransferenciaResponse;
import com.obedera.transferencias.financeiras.http.domain.response.TransferenciaResponse;
import com.obedera.transferencias.financeiras.template.Templates;
import com.obedera.transferencias.financeiras.template.TransferenciaResponseTemplate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static com.obedera.transferencias.financeiras.template.TaxTransferenciaTemplate.VALID_TAX_TYPE_A;
import static com.obedera.transferencias.financeiras.template.TransferenciaRequestTemplate.VALID_TRANSFER_REQUEST;
import static com.obedera.transferencias.financeiras.template.TransferenciaResponseTemplate.VALID_TRANSFER_RESPONSE;
import static com.obedera.transferencias.financeiras.template.TransferenciaResponseTemplate.VALID_RESUME_TRANSFER_RESPONSE;
import static com.obedera.transferencias.financeiras.template.HistoricoTransferenciaResponseTemplate.VALID_HISTORY_TRANSFER_RESPONSE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferenciaUseCaseTest {

    @BeforeClass
    public static void before() { loadTemplates(Templates.BASE_PACKAGE);}

    @Mock
    private TransferenciaRepositoryFacade transferenciaRepositoryFacade;

    @Mock
    private TaxTransferenciaUseCase taxTransferenciaUseCase;

    @Mock
    private TransferenciaBuilder transferenciaBuilder;

    @Mock
    private HistoricoTransferenciaBuilder historicoTransferenciaBuilder;

    @InjectMocks
    private TransferenciaUseCase transferenciaUseCase;


    @Test
    public void deveSalvarTransferencia(){
        when(transferenciaBuilder.build(any())).thenReturn(Fixture.from(TransferenciaResponse.class).gimme(VALID_TRANSFER_RESPONSE));
        when(taxTransferenciaUseCase.calcularTaxaTransferencia(any(), any(), any())).thenReturn(new BigDecimal("6.00"));

        TransferenciaRequest transferenciaRequest = Fixture.from(TransferenciaRequest.class).gimme(VALID_TRANSFER_REQUEST);
        var transferenciaResponse = transferenciaUseCase.salvarTransferencia(transferenciaRequest);

        verify(taxTransferenciaUseCase, times(1)).calcularTaxaTransferencia(any(), any(), any());
        verify(taxTransferenciaUseCase).calcularTaxaTransferencia(any(BigDecimal.class), any(LocalDate.class), any(LocalDate.class));
        verify(transferenciaRepositoryFacade, times(1)).save(any(TransferenciaEntity.class));
        verify(transferenciaBuilder, times(1)).build(any());

        Assert.assertNotNull(transferenciaResponse.getId());
        Assert.assertNotNull(transferenciaResponse.getDataTransferencia());
        Assert.assertNotNull(transferenciaResponse.getDataAgendamento());
        Assert.assertNotNull(transferenciaResponse.getValor());
        Assert.assertNotNull(transferenciaResponse.getTaxa());
        Assert.assertNotNull(transferenciaResponse.getContaOrigem());
        Assert.assertNotNull(transferenciaResponse.getContaDestino());
    }

    @Test
    public void deveGerarResumoTransferencia(){
        when(transferenciaBuilder.build(any())).thenReturn(Fixture.from(TransferenciaResponse.class).gimme(VALID_RESUME_TRANSFER_RESPONSE));
        when(taxTransferenciaUseCase.calcularTaxaTransferencia(any(), any(), any())).thenReturn(new BigDecimal("6.00"));

        TransferenciaRequest transferenciaRequest = Fixture.from(TransferenciaRequest.class).gimme(VALID_TRANSFER_REQUEST);
        var transferenciaResponse = transferenciaUseCase.resumoTransferencia(transferenciaRequest);

        verify(taxTransferenciaUseCase, times(1)).calcularTaxaTransferencia(any(), any(), any());
        verify(taxTransferenciaUseCase).calcularTaxaTransferencia(any(BigDecimal.class), any(LocalDate.class), any(LocalDate.class));
        verify(transferenciaRepositoryFacade, times(0)).save(any(TransferenciaEntity.class));
        verify(transferenciaBuilder, times(1)).build(any());

        Assert.assertNull(transferenciaResponse.getId());
        Assert.assertNotNull(transferenciaResponse.getDataTransferencia());
        Assert.assertNotNull(transferenciaResponse.getDataAgendamento());
        Assert.assertNotNull(transferenciaResponse.getValor());
        Assert.assertNotNull(transferenciaResponse.getTaxa());
        Assert.assertNotNull(transferenciaResponse.getContaOrigem());
        Assert.assertNotNull(transferenciaResponse.getContaDestino());
    }

    @Test
    public void deveGerarHistorivoTransferencias(){
        when(historicoTransferenciaBuilder.build(any())).thenReturn(Fixture.from(HistoricoTransferenciaResponse.class).gimme(VALID_HISTORY_TRANSFER_RESPONSE));

        var historicoTransferenciaResponse = transferenciaUseCase.listarTransferencias();

        verify(transferenciaRepositoryFacade, times(1)).findAll();
        verify(historicoTransferenciaBuilder, times(1)).build(any());

        Assert.assertNotNull(historicoTransferenciaResponse.getHistoricoTransferencias());
    }


}
