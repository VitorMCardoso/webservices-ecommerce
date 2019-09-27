package br.com.fiap.carrinho.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    private Integer id;
    private String nome;
    private String tipo;
    private Double preco;
    private Integer quantidade;
}
