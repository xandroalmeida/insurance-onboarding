package almeida.alexandro.insurance.domain.repository;

import almeida.alexandro.insurance.domain.model.InsurancePolicy;

public interface InsurancePolicyRepository {

    InsurancePolicy save(InsurancePolicy insurancePolicy);
}
