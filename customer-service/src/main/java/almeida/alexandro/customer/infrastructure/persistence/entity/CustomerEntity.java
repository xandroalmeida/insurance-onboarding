package almeida.alexandro.customer.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String cpf;


    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 50)
    private String phoneNumber;

    @Column(nullable = false, length = 200)
    private String address;
}
