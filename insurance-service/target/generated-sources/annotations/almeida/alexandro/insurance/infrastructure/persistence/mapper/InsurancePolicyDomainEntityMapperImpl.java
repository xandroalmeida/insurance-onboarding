package almeida.alexandro.insurance.infrastructure.persistence.mapper;

import almeida.alexandro.insurance.domain.model.InsurancePolicy;
import almeida.alexandro.insurance.infrastructure.persistence.entity.InsurancePolicyEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-22T17:23:10-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
)
@Component
public class InsurancePolicyDomainEntityMapperImpl implements InsurancePolicyDomainEntityMapper {

    @Override
    public InsurancePolicyEntity toEntity(InsurancePolicy domain) {
        if ( domain == null ) {
            return null;
        }

        InsurancePolicyEntity.InsurancePolicyEntityBuilder insurancePolicyEntity = InsurancePolicyEntity.builder();

        insurancePolicyEntity.id( domain.getId() );
        insurancePolicyEntity.cpf( domain.getCpf() );
        insurancePolicyEntity.type( domain.getType() );
        insurancePolicyEntity.startDate( domain.getStartDate() );
        insurancePolicyEntity.monthlyPremium( domain.getMonthlyPremium() );
        insurancePolicyEntity.totalPremium( domain.getTotalPremium() );

        return insurancePolicyEntity.build();
    }

    @Override
    public InsurancePolicy toDomain(InsurancePolicyEntity entity) {
        if ( entity == null ) {
            return null;
        }

        InsurancePolicy insurancePolicy = new InsurancePolicy();

        insurancePolicy.setId( entity.getId() );
        insurancePolicy.setCpf( entity.getCpf() );
        insurancePolicy.setType( entity.getType() );
        insurancePolicy.setStartDate( entity.getStartDate() );
        insurancePolicy.setMonthlyPremium( entity.getMonthlyPremium() );
        insurancePolicy.setTotalPremium( entity.getTotalPremium() );

        return insurancePolicy;
    }
}
