package br.com.fiap.checkout.model;

import lombok.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String tipo;
    private Double preco;
    private Integer quantidade;
}
