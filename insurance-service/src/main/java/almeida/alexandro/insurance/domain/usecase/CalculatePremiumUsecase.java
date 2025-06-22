package almeida.alexandro.insurance.domain.usecase;

import almeida.alexandro.insurance.domain.model.InsuranceType;
import almeida.alexandro.insurance.domain.model.Premium;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatePremiumUsecase {
    public Premium execute(InsuranceType type) {
        final var monthly = switch (type) {
            case BRONZE -> BigDecimal.valueOf(100.00);
            case PRATA -> BigDecimal.valueOf(200.00);
            case OURO -> BigDecimal.valueOf(300.00);
        };
        BigDecimal total = monthly.multiply(BigDecimal.valueOf(12));
        return new Premium(monthly, total);
    }
}
