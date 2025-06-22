package almeida.alexandro.insurance.infrastructure.persistence.repository;

import almeida.alexandro.insurance.domain.model.InsurancePolicy;
import almeida.alexandro.insurance.domain.repository.InsurancePolicyRepository;
import almeida.alexandro.insurance.infrastructure.persistence.mapper.InsurancePolicyDomainEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsurancePolicyRepositoryAdapter implements InsurancePolicyRepository {
    private final InsurancePolicyJpaRepository insurancePolicyJpaRepository;
    private final InsurancePolicyDomainEntityMapper insurancePolicyDomainEntityMapper;

    @Override
    public InsurancePolicy save(InsurancePolicy insurancePolicy) {
        final var entity = insurancePolicyDomainEntityMapper.toEntity(insurancePolicy);
        final var saved = insurancePolicyJpaRepository.save(entity);
        return insurancePolicyDomainEntityMapper.toDomain(saved);
    }
}
