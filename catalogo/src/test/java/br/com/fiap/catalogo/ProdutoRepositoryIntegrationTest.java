package br.com.fiap.catalogo;

import br.com.fiap.catalogo.model.Produto;
import br.com.fiap.catalogo.repository.ProdutoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoRepositoryIntegrationTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Before
    public void setUp() {
        Flux<Produto> deleteAndInsert = produtoRepository.deleteAll()
                .thenMany(produtoRepository.saveAll(Flux.just(
                        new Produto(1, "Caderno", "Universitário", 23.45,200),
                        new Produto(2, "Caneta", "Bic - Vermelha", 2.45,150)
                )));

        StepVerifier
                .create(deleteAndInsert)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void saveTest() {
        Mono<Long> saveAndCount = produtoRepository.count()
                .doOnNext(System.out::println)
                .thenMany(produtoRepository
                        .saveAll(Flux.just(
                                new Produto(3, "Lapis", "Preto", 1.45,140),
                                new Produto(4, "Compasso", "Pro", 25.45,135)))
                ).last()
                .flatMap(v -> produtoRepository.count())
                .doOnNext(System.out::println);

        StepVerifier
                .create(saveAndCount)
                .expectNext(4L)
                .verifyComplete();
    }

    @Test
    public void filterTest() {
        StepVerifier
                .create(produtoRepository.findProdutoByPrecoIsLessThanEqual(3.50))
                .expectNextCount(1L)
                .verifyComplete();
    }
}
