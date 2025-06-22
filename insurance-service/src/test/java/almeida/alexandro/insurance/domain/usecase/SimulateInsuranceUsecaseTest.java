package almeida.alexandro.insurance.domain.usecase;

import almeida.alexandro.insurance.domain.model.InsurancePolicy;
import almeida.alexandro.insurance.domain.model.InsuranceType;
import almeida.alexandro.insurance.domain.model.Premium;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulateInsuranceUsecaseTest {
    @Mock
    private CalculatePremiumUsecase calculatePremiumUsecase;

    @InjectMocks
    private SimulateInsuranceUsecase simulateInsuranceUsecase;

    @Test
    void execute_shouldReturnInsurancePolicyWithExpectedValues() {

        String cpf = "12345678900";
        InsuranceType type = InsuranceType.PRATA;

        Premium premium = mock(Premium.class);
        when(premium.monthlyPremium()).thenReturn(BigDecimal.valueOf(150.00));
        when(premium.totalPremium()).thenReturn(BigDecimal.valueOf(1800.00));

        when(calculatePremiumUsecase.execute(type)).thenReturn(premium);

        InsurancePolicy policy = simulateInsuranceUsecase.execute(cpf, type);

        // then
        assertNotNull(policy);
        assertEquals(type, policy.getType());
        assertEquals(BigDecimal.valueOf(150.00), policy.getMonthlyPremium());
        assertEquals(BigDecimal.valueOf(1800.00), policy.getTotalPremium());
        assertEquals(cpf, policy.getCpf());
        assertNull(policy.getId());
        assertNotNull(policy.getStartDate());
        assertTrue(policy.getStartDate().isBefore(LocalDateTime.now().plusSeconds(1)));

        verify(calculatePremiumUsecase, times(1)).execute(type);
        verifyNoMoreInteractions(calculatePremiumUsecase);
    }
}