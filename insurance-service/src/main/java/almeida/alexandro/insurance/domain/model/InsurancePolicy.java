package almeida.alexandro.insurance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InsurancePolicy {
    private Long id;
    private String cpf;
    private InsuranceType type;
    private LocalDateTime startDate;
    private BigDecimal monthlyPremium;
    private BigDecimal totalPremium;
}
