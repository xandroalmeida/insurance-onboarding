package almeida.alexandro.customer.infrastructure.persistence.repository;

import almeida.alexandro.customer.domain.model.Customer;
import almeida.alexandro.customer.domain.repository.CustomerRepository;
import almeida.alexandro.customer.infrastructure.persistence.mapper.CustomerDomainEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDomainEntityMapper customerDomainEntityMapper;

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll().stream()
                .map(customerDomainEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Customer save(Customer customer) {
        final var entity = customerDomainEntityMapper.toEntity(customer);
        final var saved = customerJpaRepository.save(entity);
        return customerDomainEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id)
                .map(customerDomainEntityMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return customerJpaRepository.findByCpf(cpf)
                .map(customerDomainEntityMapper::toDomain);
    }

    @Override
    public void delete(Long id) {
        customerJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return customerJpaRepository.existsById(id);
    }

}
