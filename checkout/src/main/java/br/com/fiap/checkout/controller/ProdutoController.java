package br.com.fiap.checkout.controller;

import br.com.fiap.checkout.model.Produto;
import br.com.fiap.checkout.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "get-all-produtos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Produto> getAll() {
        return produtoService.findAllProdutos();
    }

    @PostMapping(value = "add")
    public void saveAll(@RequestBody List<Produto> produtos) {
        produtoService.addProduto(produtos);
    }
}
