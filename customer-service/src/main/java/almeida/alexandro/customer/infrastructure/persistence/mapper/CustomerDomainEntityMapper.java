package almeida.alexandro.customer.infrastructure.persistence.mapper;

import almeida.alexandro.customer.domain.model.Customer;
import almeida.alexandro.customer.infrastructure.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDomainEntityMapper {
    CustomerEntity toEntity(Customer domain);

    Customer toDomain(CustomerEntity entity);
}
