package br.com.fiap.catalogo.controller;

import br.com.fiap.catalogo.model.Produto;
import br.com.fiap.catalogo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

//    @PostConstruct
//    public void saveProdutos() {
//        List<Produto> produtoList = new ArrayList<>();
//        produtoList.add(new Produto(1, "Caderno", "Universitário", 23.45));
//        produtoList.add(new Produto(2, "Caneta", "Bic - Vermelha", 2.45));
//        produtoList.add(new Produto(3, "Lapis", "Preto", 1.45));
//        produtoList.add(new Produto(4, "Compasso", "Pro", 25.45));
//        produtoService.initializeProdutos(produtoList);
//    }

    @PostMapping("/add")
    public int saveAll(@RequestBody List<Produto> produtos) {
        produtoService.initializeProdutos(produtos);
        return HttpServletResponse.SC_CREATED;
    }

    @GetMapping("/list")
    public Flux<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @GetMapping("{id}")
    public Mono<Produto> getProdutoById(@PathVariable Integer id) {
        return produtoService.getProdutoById(id);
    }

    @GetMapping("/filterpreco/{preco}")
    public Flux<Produto> getProdutoFilterByPreco(@PathVariable Double preco) {
        return produtoService.getProdutoFilterByPreco(preco);
    }
}
