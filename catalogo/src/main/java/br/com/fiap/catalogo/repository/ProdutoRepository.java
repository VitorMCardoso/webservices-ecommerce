package br.com.fiap.catalogo.repository;

import br.com.fiap.catalogo.model.Produto;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface ProdutoRepository extends ReactiveCassandraRepository<Produto, Integer> {

    @AllowFiltering
    Flux<Produto> findProdutoByPrecoIsLessThanEqual(Double preco);
}
