package almeida.alexandro.insurance.domain.model;

import java.math.BigDecimal;

public record Premium(BigDecimal monthlyPremium, BigDecimal totalPremium) {
}
