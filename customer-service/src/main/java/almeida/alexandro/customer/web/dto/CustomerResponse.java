package almeida.alexandro.customer.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private String address;

    public static CustomerResponse fromDomain(almeida.alexandro.customer.domain.model.Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }
}
