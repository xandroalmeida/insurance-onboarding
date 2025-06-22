package almeida.alexandro.insurance.web.dto;

import almeida.alexandro.insurance.domain.model.InsuranceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SimulationResponse {
    @Schema(description = "CPF do cliente", example = "12345678901")
    private String cpf;

    @Schema(description = "Tipo de seguro simulado", example = "PRATA")
    private InsuranceType type;

    @Schema(description = "Valor do prêmio mensal", example = "150.75")
    private BigDecimal monthlyPremium;

    @Schema(description = "Valor total do prêmio", example = "1809.00")
    private BigDecimal totalPremium;
}
