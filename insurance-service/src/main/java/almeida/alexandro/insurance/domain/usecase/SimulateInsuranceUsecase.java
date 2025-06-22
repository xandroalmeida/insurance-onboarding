package almeida.alexandro.insurance.domain.usecase;

import almeida.alexandro.insurance.domain.model.InsurancePolicy;
import almeida.alexandro.insurance.domain.model.InsuranceType;
import almeida.alexandro.insurance.domain.model.Premium;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SimulateInsuranceUsecase {
    private final CalculatePremiumUsecase calculatePremiumUsecase;

    public InsurancePolicy execute(String cpf, InsuranceType type) {
        Premium premium = calculatePremiumUsecase.execute(type);

        // Cria uma nova apólice de seguro com os dados simulados
        final var insurancePolicy = new InsurancePolicy();
        insurancePolicy.setType(type);
        insurancePolicy.setMonthlyPremium(premium.monthlyPremium());
        insurancePolicy.setTotalPremium(premium.totalPremium());
        insurancePolicy.setStartDate(LocalDateTime.now());
        insurancePolicy.setCpf(cpf); // Placeholder, deve ser definido corretamente
        insurancePolicy.setId(null); // ID deve ser gerado apena no momento da contratação

        return insurancePolicy;
    }
}
