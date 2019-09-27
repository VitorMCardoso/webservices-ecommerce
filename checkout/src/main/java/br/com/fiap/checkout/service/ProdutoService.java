package br.com.fiap.checkout.service;

import br.com.fiap.checkout.model.Produto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.*;


@Service
public class ProdutoService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.queue.name}")
    private String queueName;

    @HystrixCommand(fallbackMethod = "defaultList")
    @Cacheable(value = "allProdutoCache", unless = "#result.size() == 0")
    public List<Produto> findAllProdutos() {

        Flux<Produto> produtoFlux = WebClient.create()
                .get()
                .uri("localhost:8080/produto/list")
                .retrieve()
                .bodyToFlux(Produto.class);

        return produtoFlux.collectList().block();
    }

    @HystrixCommand(fallbackMethod = "addMsg")
    @Caching(
            //put = {@CachePut(value = "produtoAddCache", key = "#produtos.get(1).id")},
            evict = {@CacheEvict(value = "allProdutoCache", allEntries = true)}
    )
    public void addProduto(List<Produto> produtos) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("http://localhost:8080/produto/add", produtos, List.class);
    }

    public void addMsg(List<Produto> produtos) {
        jmsTemplate.convertAndSend(queueName, Objects.requireNonNull(Arrays.toString(produtos.toArray())));
    }

    public List<Produto> defaultList() {
        return new ArrayList<Produto>();
    }
}

