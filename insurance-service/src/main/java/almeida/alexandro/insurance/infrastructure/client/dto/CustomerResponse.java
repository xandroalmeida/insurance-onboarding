package almeida.alexandro.insurance.infrastructure.client.dto;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private String address;
}
