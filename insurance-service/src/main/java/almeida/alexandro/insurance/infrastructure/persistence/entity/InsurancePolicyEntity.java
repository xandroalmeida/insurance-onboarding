package almeida.alexandro.insurance.infrastructure.persistence.entity;

import almeida.alexandro.insurance.domain.model.InsuranceType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "insurance_policy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePolicyEntity {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * CPF do cliente que contratou a apólice.
     */
    @Column(name = "cpf", nullable = false, length = 20)
    private String cpf;

    /**
     * Tipo de seguro contratado (BRONZE, PRATA, OURO).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private InsuranceType type;

    /**
     * Data e hora de início da cobertura.
     */
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    /**
     * Valor do prêmio mensal calculado na simulação/contratação.
     */
    @Column(name = "monthly_premium", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyPremium;

    /**
     * Valor total do prêmio (e.g. mensal × 12).
     */
    @Column(name = "total_premium", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPremium;
}
