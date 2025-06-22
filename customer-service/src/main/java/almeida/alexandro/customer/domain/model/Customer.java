package almeida.alexandro.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    Long id;
    String cpf;
    String name;
    String email;
    String phoneNumber;
    String address;
}
