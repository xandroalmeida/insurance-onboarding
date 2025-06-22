package almeida.alexandro.insurance.web.dto;

import almeida.alexandro.insurance.domain.model.InsuranceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class InsurancePolicyContractResponse {
    @Schema(description = "Identificador da apólice gerada")
    private Long id;

    @Schema(description = "CPF do cliente", example = "12345678901")
    private String cpf;

    @Schema(description = "Tipo de seguro contratado", example = "OURO")
    private InsuranceType type;

    @Schema(description = "Data de início da apólice")
    private LocalDateTime startDate;

    @Schema(description = "Valor mensal do prêmio", example = "150.00")
    private Double monthlyPremium;

    @Schema(description = "Valor total do prêmio", example = "1800.00")
    private Double totalPremium;

}
