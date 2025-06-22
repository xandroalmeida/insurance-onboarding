package almeida.alexandro.customer.domain.repository;

import almeida.alexandro.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByCpf(String cpf);

    void delete(Long id);

    boolean existsById(Long id);

}
