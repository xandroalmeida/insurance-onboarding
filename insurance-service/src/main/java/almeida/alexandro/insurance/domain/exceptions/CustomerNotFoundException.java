package almeida.alexandro.insurance.domain.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String cpf) {
        super("Customer with CPF " + cpf + " not found.");
    }
}
