package almeida.alexandro.insurance.domain.usecase;

import almeida.alexandro.insurance.domain.model.InsuranceType;
import almeida.alexandro.insurance.domain.model.Premium;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CalculatePremiumUsecaseTest {

    @InjectMocks
    private CalculatePremiumUsecase usecase;

    @Test
    void shouldCalculatePremiumForBronze() {
        Premium premium = usecase.execute(InsuranceType.BRONZE);

        assertAll("BRONZE",
                () -> assertEquals(BigDecimal.valueOf(100.00), premium.monthlyPremium()),
                () -> assertEquals(BigDecimal.valueOf(1200.00), premium.totalPremium())
        );
    }

    @Test
    void shouldCalculatePremiumForPrata() {
        Premium premium = usecase.execute(InsuranceType.PRATA);

        assertAll("PRATA",
                () -> assertEquals(BigDecimal.valueOf(200.00), premium.monthlyPremium()),
                () -> assertEquals(BigDecimal.valueOf(2400.00), premium.totalPremium())
        );
    }

    @Test
    void shouldCalculatePremiumForOuro() {
        Premium premium = usecase.execute(InsuranceType.OURO);

        assertAll("OURO",
                () -> assertEquals(BigDecimal.valueOf(300.00), premium.monthlyPremium()),
                () -> assertEquals(BigDecimal.valueOf(3600.00), premium.totalPremium())
        );
    }
}