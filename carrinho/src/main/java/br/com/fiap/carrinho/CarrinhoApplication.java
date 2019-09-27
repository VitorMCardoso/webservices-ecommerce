package br.com.fiap.carrinho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class CarrinhoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrinhoApplication.class, args);
    }

}
