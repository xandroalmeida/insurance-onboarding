package almeida.alexandro.customer.web.dto;

import almeida.alexandro.customer.domain.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class CustomerRequest {

    @NotBlank
    @Size(min = 3, max = 200)
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 50)
    private String phoneNumber;

    @NotBlank
    @Size(min = 8, max = 50)
    private String address;

    /*
     * Não vamos user um mapper para converter de CustomerRequest para Customer
     * para não termos um acoplamento forte entre as camadas.
     */
    public Customer toDomain() {
        return Customer.builder()
                .name(this.getName())
                .cpf(this.getCpf())
                .email(this.getEmail())
                .phoneNumber(this.getPhoneNumber())
                .address(this.getAddress())
                .build();
    }
}
