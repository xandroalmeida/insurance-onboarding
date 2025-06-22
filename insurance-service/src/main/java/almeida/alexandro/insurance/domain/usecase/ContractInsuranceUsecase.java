package almeida.alexandro.insurance.domain.usecase;

import almeida.alexandro.insurance.domain.exceptions.CustomerNotFoundException;
import almeida.alexandro.insurance.domain.model.InsurancePolicy;
import almeida.alexandro.insurance.domain.model.InsuranceType;
import almeida.alexandro.insurance.domain.model.Premium;
import almeida.alexandro.insurance.domain.repository.InsurancePolicyRepository;
import almeida.alexandro.insurance.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContractInsuranceUsecase {
    private final CustomerService customerService;
    private final CalculatePremiumUsecase calculatePremiumUsecase;
    private final InsurancePolicyRepository insurancePolicyRepository;

    public InsurancePolicy execute(String cpf, InsuranceType type) {
        if (!customerService.existsCusumerByCpf(cpf)) {
            throw new CustomerNotFoundException("Cliente não encontrado com o CPF: " + cpf);
        }

        Premium premium = calculatePremiumUsecase.execute(type);

        // Cria uma nova apólice de seguro com os dados contratados
        final var insurancePolicy = new InsurancePolicy();
        insurancePolicy.setCpf(cpf);
        insurancePolicy.setType(type);
        insurancePolicy.setMonthlyPremium(premium.monthlyPremium());
        insurancePolicy.setTotalPremium(premium.totalPremium());
        insurancePolicy.setStartDate(LocalDateTime.now());

        return insurancePolicyRepository.save(insurancePolicy);
    }
}
