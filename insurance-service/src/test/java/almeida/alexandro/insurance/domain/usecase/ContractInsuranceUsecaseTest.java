package almeida.alexandro.insurance.domain.usecase;


import almeida.alexandro.insurance.domain.exceptions.CustomerNotFoundException;
import almeida.alexandro.insurance.domain.model.InsurancePolicy;
import almeida.alexandro.insurance.domain.model.InsuranceType;
import almeida.alexandro.insurance.domain.model.Premium;
import almeida.alexandro.insurance.domain.repository.InsurancePolicyRepository;
import almeida.alexandro.insurance.domain.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractInsuranceUsecaseTest {
    @Mock
    private CustomerService customerService;

    @Mock
    private CalculatePremiumUsecase calculatePremiumUsecase;

    @Mock
    private InsurancePolicyRepository insurancePolicyRepository;

    @InjectMocks
    private ContractInsuranceUsecase usecase;

    @Test
    void execute_whenCustomerExists_thenSavesAndReturnsPolicy() {
        // Arrange
        String cpf = "12345678900";
        InsuranceType type = InsuranceType.PRATA;
        Premium premium = new Premium(BigDecimal.valueOf(100.0), BigDecimal.valueOf(1200.0));

        when(customerService.existsCusumerByCpf(cpf)).thenReturn(true);
        when(calculatePremiumUsecase.execute(type)).thenReturn(premium);

        // capturador para inspecionar o objeto salvo
        ArgumentCaptor<InsurancePolicy> captor = ArgumentCaptor.forClass(InsurancePolicy.class);
        InsurancePolicy savedPolicy = new InsurancePolicy();
        when(insurancePolicyRepository.save(captor.capture())).thenReturn(savedPolicy);

        InsurancePolicy result = usecase.execute(cpf, type);

        assertSame(savedPolicy, result, "Deve retornar a apólice salva");

        InsurancePolicy toSave = captor.getValue();
        assertEquals(cpf, toSave.getCpf());
        assertEquals(type, toSave.getType());
        assertEquals(premium.monthlyPremium(), toSave.getMonthlyPremium());
        assertEquals(premium.totalPremium(), toSave.getTotalPremium());
        assertNotNull(toSave.getStartDate());
    }

    @Test
    void execute_whenCustomerNotFound_thenThrowsException() {
        // Arrange
        String cpf = "00000000000";
        InsuranceType type = InsuranceType.BRONZE;

        when(customerService.existsCusumerByCpf(cpf)).thenReturn(false);

        CustomerNotFoundException ex = assertThrows(
                CustomerNotFoundException.class,
                () -> usecase.execute(cpf, type),
                "Deve lançar exceção quando cliente não existe"
        );
        assertTrue(ex.getMessage().contains(cpf), "Mensagem deve informar o CPF faltante");

        // Não deve salvar apólice se cliente não existe
        verify(insurancePolicyRepository, never()).save(any());
    }
}