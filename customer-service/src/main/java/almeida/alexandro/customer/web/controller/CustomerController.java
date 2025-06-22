package almeida.alexandro.customer.web.controller;

import almeida.alexandro.customer.domain.usecase.ManagerCustomerUsecase;
import almeida.alexandro.customer.web.dto.CustomerRequest;
import almeida.alexandro.customer.web.dto.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer", description = "Operações de cadastro de clientes")
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ManagerCustomerUsecase managerCustomerUsecase;

    @Operation(summary = "Busca cliente por ID", description = "Retorna os dados de um cliente conforme seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> findAll() {
        final var response = managerCustomerUsecase.findAll().stream()
                .map(CustomerResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cria um novo cliente", description = "Endpoint para cadastrar um novo cliente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Parameter(description = "Dados do cliente para criação", required = true)
            @Valid @RequestBody CustomerRequest request) {
        final var entity = managerCustomerUsecase.createCustomer(request.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponse.fromDomain(entity));
    }

    @Operation(summary = "Busca cliente por ID", description = "Retorna os dados de um cliente conforme seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(
            @Parameter(description = "ID do cliente", example = "1", required = true)
            @PathVariable("id") Long id) {
        final var response = managerCustomerUsecase.getCustomerById(id).map(CustomerResponse::fromDomain);
        return ResponseEntity.of(response);
    }

    @Operation(summary = "Busca cliente por CPF", description = "Retorna os dados de um cliente conforme seu CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CustomerResponse> getByCpf(
            @Parameter(description = "CPF do cliente", example = "234.543.874-98", required = true)
            @PathVariable("cpf") String cpf) {
        final var response = managerCustomerUsecase.getCustomerByCpf(cpf).map(CustomerResponse::fromDomain);
        return ResponseEntity.of(response);
    }

    @Operation(summary = "Atualiza dados de um cliente", description = "Atualiza as informações de um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(
            @Parameter(description = "ID do cliente a ser atualizado", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "Dados para atualização do cliente", required = true)
            @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.of(managerCustomerUsecase.updateCustomer(id, request.toDomain())
                .map(CustomerResponse::fromDomain));
    }

    @Operation(summary = "Deleta um cliente", description = "Remove um cliente do sistema conforme seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do cliente a ser deletado", example = "1", required = true)
            @PathVariable("id") Long id) {
        managerCustomerUsecase.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
