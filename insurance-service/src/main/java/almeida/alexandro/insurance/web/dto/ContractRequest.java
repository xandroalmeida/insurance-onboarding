package almeida.alexandro.insurance.web.dto;

import almeida.alexandro.insurance.domain.model.InsuranceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class ContractRequest {
    @Schema(description = "CPF do cliente", example = "12345678901", required = true)
    @NotBlank
    @CPF
    private String cpf;

    @Schema(description = "Tipo de seguro a contratar", example = "OURO", required = true)
    @NotNull
    private InsuranceType type;
}
