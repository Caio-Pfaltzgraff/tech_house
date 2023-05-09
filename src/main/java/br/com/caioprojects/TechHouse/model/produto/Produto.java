package br.com.caioprojects.TechHouse.model.produto;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Produto")
@Table(name = "produtos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private BigDecimal preco;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    @Column(name = "quantidade_minima")
    private int quantidadeMinima;

    @Column(name = "quantidade_maxima")
    private int quantidadeMaxima;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_do_produto")
    private TipoProduto tipo;

}
