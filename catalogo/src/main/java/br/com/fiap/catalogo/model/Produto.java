package br.com.fiap.catalogo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "produto")
public class Produto {

    @PrimaryKey
    private Integer id;
    private String nome;
    private String tipo;
    private Double preco;
    private Integer quantidade;
}
