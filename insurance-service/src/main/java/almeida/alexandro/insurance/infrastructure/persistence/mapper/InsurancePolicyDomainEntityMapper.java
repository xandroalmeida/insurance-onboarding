package almeida.alexandro.insurance.infrastructure.persistence.mapper;

import almeida.alexandro.insurance.domain.model.InsurancePolicy;
import almeida.alexandro.insurance.infrastructure.persistence.entity.InsurancePolicyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsurancePolicyDomainEntityMapper {
    InsurancePolicyEntity toEntity(InsurancePolicy domain);

    InsurancePolicy toDomain(InsurancePolicyEntity entity);
}
