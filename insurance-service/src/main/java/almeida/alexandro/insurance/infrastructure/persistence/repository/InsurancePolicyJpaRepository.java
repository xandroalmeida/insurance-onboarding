package almeida.alexandro.insurance.infrastructure.persistence.repository;

import almeida.alexandro.insurance.infrastructure.persistence.entity.InsurancePolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyJpaRepository extends JpaRepository<InsurancePolicyEntity, Long> {
}
