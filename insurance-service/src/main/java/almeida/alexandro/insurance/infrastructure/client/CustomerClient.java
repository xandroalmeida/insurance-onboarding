package almeida.alexandro.insurance.infrastructure.client;

import almeida.alexandro.insurance.infrastructure.client.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service", url = "${app.customer.base-url}", dismiss404 = true)
public interface CustomerClient {
    @GetMapping("/api/customers/cpf/{cpf}")
    Optional<CustomerResponse> getByCpf(@PathVariable("cpf") String cpf);
}
