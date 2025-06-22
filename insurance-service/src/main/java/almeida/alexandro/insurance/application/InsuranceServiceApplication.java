package almeida.alexandro.insurance.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "almeida.alexandro.insurance")
@EnableTransactionManagement
public class InsuranceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsuranceServiceApplication.class, args);
    }
}