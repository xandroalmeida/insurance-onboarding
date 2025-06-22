package almeida.alexandro.insurance.web.handler;

import almeida.alexandro.insurance.domain.exceptions.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Intercepta a exceção de cliente não encontrado e monta uma resposta adequada para a API
 */
@RestControllerAdvice
public class CustomerNotFoundHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handle(CustomerNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
