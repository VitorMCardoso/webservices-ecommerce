package br.com.fiap.catalogo.service;

import br.com.fiap.catalogo.model.Produto;
import br.com.fiap.catalogo.repository.ProdutoRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.jms.ConnectionFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ConnectionFactory connectionFactory;


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

    public void initializeProdutos(List<Produto> produtoList) {
        Flux<Produto> savedProdutos = produtoRepository.saveAll(produtoList);
        savedProdutos.subscribe();
    }

    @JmsListener(destination = "retencao")
    public void onReceiverQueue(String str) throws IOException {
        Type listType = new TypeToken<ArrayList<Produto>>() {
        }.getType();
        List<Produto> produtoList = new Gson().fromJson(str, listType);
        initializeProdutos(produtoList);
    }

}
