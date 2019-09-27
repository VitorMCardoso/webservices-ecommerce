package br.com.fiap.carrinho.controller;

import br.com.fiap.carrinho.model.Produto;
import br.com.fiap.carrinho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("get-all-produtos")
    public List getAll() {
        return produtoService.findAllProdutos();
    }
}
