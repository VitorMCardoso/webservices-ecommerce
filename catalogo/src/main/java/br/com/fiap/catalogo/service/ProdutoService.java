package br.com.fiap.catalogo.service;

import br.com.fiap.catalogo.model.Produto;
import br.com.fiap.catalogo.repository.ProdutoRepository;
import br.com.fiap.catalogo.util.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();

    @Autowired
    ProdutoRepository produtoRepository;

    public Flux<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Mono<Produto> getProdutoById(Integer id) {
        return produtoRepository.findById(id);
    }

    public Flux<Produto> getProdutoFilterByPreco(Double preco) {
        return produtoRepository.findProdutoByPrecoIsLessThanEqual(preco);
    }

   /* public Flux<Produto> saveAll(Flux<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }*/

    public void initializeProdutos(List<Produto> produtoList) {
        Flux<Produto> savedProdutos = produtoRepository.saveAll(produtoList);
        savedProdutos.subscribe();
    }

    @JmsListener(destination = "retencao")
    public void onReceiverQueue(String str) throws IOException {
        List<Produto> produtoList = objectMapper.readValue(str, List.class);


        System.out.println(str);
    }

}
