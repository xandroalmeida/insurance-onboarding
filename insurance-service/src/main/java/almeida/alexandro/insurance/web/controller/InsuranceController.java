package almeida.alexandro.insurance.web.controller;

import almeida.alexandro.insurance.domain.usecase.ContractInsuranceUsecase;
import almeida.alexandro.insurance.domain.usecase.SimulateInsuranceUsecase;
import almeida.alexandro.insurance.web.dto.ContractRequest;
import almeida.alexandro.insurance.web.dto.InsurancePolicyContractResponse;
import almeida.alexandro.insurance.web.dto.SimulationRequest;
import almeida.alexandro.insurance.web.dto.SimulationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController implements InsuranceControllerApiDoc {

    private final SimulateInsuranceUsecase simulateInsuranceUsecase;
    private final ContractInsuranceUsecase contractInsuranceUsecase;

    @Override
    @PostMapping("/simulate")
    public ResponseEntity<SimulationResponse> simulate(
            @Valid @RequestBody SimulationRequest request) {
        final var simulated = simulateInsuranceUsecase.execute(request.getCpf(), request.getType());
        return ResponseEntity.ok(SimulationResponse.builder()
                .cpf(simulated.getCpf())
                .type(simulated.getType())
                .monthlyPremium(simulated.getMonthlyPremium())
                .totalPremium(simulated.getTotalPremium())
                .build());
    }

    @Override
    @PostMapping("/contract")
    public ResponseEntity<InsurancePolicyContractResponse> contract(
            @Valid @RequestBody ContractRequest request) {

        final var policy = contractInsuranceUsecase.execute(request.getCpf(), request.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                InsurancePolicyContractResponse.builder()
                        .id(policy.getId())
                        .cpf(policy.getCpf())
                        .type(policy.getType())
                        .monthlyPremium(policy.getMonthlyPremium().doubleValue())
                        .totalPremium(policy.getTotalPremium().doubleValue())
                        .startDate(policy.getStartDate())
                        .build()
        );
    }
}