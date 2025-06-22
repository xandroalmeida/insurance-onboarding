package almeida.alexandro.insurance.infrastructure.services;

import almeida.alexandro.insurance.domain.service.CustomerService;
import almeida.alexandro.insurance.infrastructure.client.CustomerClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class CustomerServiceImpl implements CustomerService {

    private final CustomerClient customerClient;

    @CircuitBreaker(name = "customerService", fallbackMethod = "customerFallback")
    @Override
    public boolean existsCusumerByCpf(String cpf) {
        return customerClient.getByCpf(cpf)
                .map(customerResponse -> true)
                .orElse(false);
    }

    public boolean customerFallback(String cpf, Throwable ex) {
        log.severe("Fallback de buscarCliente para %s: %s".formatted(cpf, ex.toString()));
        // retorno alternativo ou relança de exceção customizada
        return false;
    }
}
