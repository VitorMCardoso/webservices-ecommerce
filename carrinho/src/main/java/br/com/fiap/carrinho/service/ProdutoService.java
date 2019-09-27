package br.com.fiap.carrinho.service;

import br.com.fiap.carrinho.model.Produto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ProdutoService {

    @HystrixCommand(fallbackMethod = "defaultListProdutos")
    public List findAllProdutos() {
        List restTemplate =  new RestTemplate()
                .getForObject("http://localhost:8080/produto/list",
                        List.class);

        return restTemplate;
    }

    private Flux<Produto> defaultListProdutos() {
        return Flux.just(new Produto());
    }
}
