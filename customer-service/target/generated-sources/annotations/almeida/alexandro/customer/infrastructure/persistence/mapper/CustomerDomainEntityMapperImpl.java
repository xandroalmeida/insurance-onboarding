package almeida.alexandro.customer.infrastructure.persistence.mapper;

import almeida.alexandro.customer.domain.model.Customer;
import almeida.alexandro.customer.infrastructure.persistence.entity.CustomerEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-22T11:52:36-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
)
@Component
public class CustomerDomainEntityMapperImpl implements CustomerDomainEntityMapper {

    @Override
    public CustomerEntity toEntity(Customer domain) {
        if ( domain == null ) {
            return null;
        }

        CustomerEntity.CustomerEntityBuilder customerEntity = CustomerEntity.builder();

        customerEntity.id( domain.getId() );
        customerEntity.name( domain.getName() );
        customerEntity.cpf( domain.getCpf() );
        customerEntity.email( domain.getEmail() );
        customerEntity.phoneNumber( domain.getPhoneNumber() );
        customerEntity.address( domain.getAddress() );

        return customerEntity.build();
    }

    @Override
    public Customer toDomain(CustomerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( entity.getId() );
        customer.cpf( entity.getCpf() );
        customer.name( entity.getName() );
        customer.email( entity.getEmail() );
        customer.phoneNumber( entity.getPhoneNumber() );
        customer.address( entity.getAddress() );

        return customer.build();
    }
}
