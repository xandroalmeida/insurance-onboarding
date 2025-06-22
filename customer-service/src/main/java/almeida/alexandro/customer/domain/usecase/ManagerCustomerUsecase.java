package almeida.alexandro.customer.domain.usecase;

import almeida.alexandro.customer.domain.model.Customer;
import almeida.alexandro.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Tradeoff: Vamos usar o @Service para simplificar a injeção de dependências violando a
// separação de camadas de domain e infrastructure. Sorry :-)
@Service
@RequiredArgsConstructor
public class ManagerCustomerUsecase {
    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(existing -> Customer.builder()
                        //Atualiza os campos conforme regras de negócio
                        .id(id)
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .phoneNumber(customer.getPhoneNumber())
                        .address(customer.getAddress())
                        .build())
                .map(customerRepository::save);
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }
}
