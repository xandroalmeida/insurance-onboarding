package almeida.alexandro.customer.domain.usecase;

import almeida.alexandro.customer.domain.model.Customer;
import almeida.alexandro.customer.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerCustomerUsecaseTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ManagerCustomerUsecase usecase;

    private Customer existingCustomer;
    private Customer newCustomer;

    @BeforeEach
    void setUp() {
        existingCustomer = Customer.builder()
                .id(1L)
                .name("João")
                .cpf("637.933.680-09")
                .email("joao@example.com")
                .phoneNumber("555-1234")
                .address("Rua A, 123")
                .build();

        newCustomer = Customer.builder()
                .name("Maria")
                .cpf("637.933.680-09")
                .email("maria@example.com")
                .phoneNumber("555-5678")
                .address("Rua B, 456")
                .build();
    }

    @Test
    void findAll_shouldReturnAllCustomers() {
        List<Customer> mocked = Arrays.asList(existingCustomer, newCustomer);
        when(customerRepository.findAll()).thenReturn(mocked);

        List<Customer> result = usecase.findAll();

        assertEquals(2, result.size(), "Deve retornar exatamente 2 clientes");
        assertIterableEquals(mocked, result, "A lista retornada deve ser igual à mockada");
        verify(customerRepository).findAll();
    }

    @Test
    void createCustomer_shouldSaveAndReturnCustomer() {
        usecase.createCustomer(newCustomer);
        verify(customerRepository).save(newCustomer);
    }

    @Test
    void getCustomerById_whenExists_shouldReturnOptionalWithCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));

        Optional<Customer> result = usecase.getCustomerById(1L);

        assertTrue(result.isPresent());
        assertEquals(existingCustomer, result.get());
        verify(customerRepository).findById(1L);
    }

    @Test
    void getCustomerById_whenNotExists_shouldReturnEmptyOptional() {
        when(customerRepository.findById(42L)).thenReturn(Optional.empty());

        Optional<Customer> result = usecase.getCustomerById(42L);

        assertFalse(result.isPresent());
        verify(customerRepository).findById(42L);
    }

    @Test
    void updateCustomer_whenExists_shouldUpdateAndSave() {
        Customer updatedInfo = Customer.builder()
                .name("João Silva")
                .email("joao.silva@example.com")
                .phoneNumber("555-9999")
                .address("Rua C, 789")
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Customer> result = usecase.updateCustomer(1L, updatedInfo);

        assertTrue(result.isPresent());

        // capturar o objeto enviado para save
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());
        Customer toSave = captor.getValue();

        assertEquals(1L, toSave.getId());
        assertEquals("João Silva", toSave.getName());
        assertEquals("joao.silva@example.com", toSave.getEmail());
        assertEquals("555-9999", toSave.getPhoneNumber());
        assertEquals("Rua C, 789", toSave.getAddress());
    }

    @Test
    void updateCustomer_whenNotExists_shouldReturnEmptyAndNotSave() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Customer> result = usecase.updateCustomer(99L, newCustomer);

        assertFalse(result.isPresent());
        verify(customerRepository, never()).save(any());
    }

    @Test
    void deleteCustomer_shouldInvokeRepositoryDelete() {
        // método delete não retorna nada
        doNothing().when(customerRepository).delete(1L);

        usecase.deleteCustomer(1L);

        verify(customerRepository).delete(1L);
    }
}