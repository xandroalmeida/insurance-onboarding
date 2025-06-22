package almeida.alexandro.insurance.web.controller;

import almeida.alexandro.insurance.web.dto.ContractRequest;
import almeida.alexandro.insurance.web.dto.InsurancePolicyContractResponse;
import almeida.alexandro.insurance.web.dto.SimulationRequest;
import almeida.alexandro.insurance.web.dto.SimulationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Insurance", description = "Endpoints para simulação e contratação de seguros")
public interface InsuranceControllerApiDoc {
    @Operation(
            summary = "Simular seguro",
            description = "Simula o valor do seguro (Bronze/Prata/Ouro) para um cliente existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Simulação realizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SimulationResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Parâmetros inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "BadRequestExample",
                                            summary = "Exemplo de erro 400",
                                            value = """
                                                    {
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "CPF inválido: formato esperado 000.000.000-00"
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "NotFoundExample",
                                            summary = "Exemplo de erro 404",
                                            value = """
                                                    {
                                                      "status": 404,
                                                      "error": "Not Found",
                                                      "message": "Cliente com CPF 123.456.789-00 não existe"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    })
    ResponseEntity<SimulationResponse> simulate(
            @Valid @RequestBody SimulationRequest request);

    @Operation(
            summary = "Contratar seguro",
            description = "Realiza a contratação do seguro para um cliente existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Contratação realizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InsurancePolicyContractResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Parâmetros inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "BadRequestExample",
                                            summary = "Exemplo de erro 400",
                                            value = """
                                                    {
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "CPF inválido: formato esperado 000.000.000-00"
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "NotFoundExample",
                                            summary = "Exemplo de erro 404",
                                            value = """
                                                    {
                                                      "status": 404,
                                                      "error": "Not Found",
                                                      "message": "Cliente com CPF 123.456.789-00 não existe"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    })
    ResponseEntity<InsurancePolicyContractResponse> contract(
            @Valid @RequestBody ContractRequest request);
}
