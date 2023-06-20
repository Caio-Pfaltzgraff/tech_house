package br.com.caioprojects.TechHouse.domain.produto;

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

    private Boolean ativo;

    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.quantidadeEstoque = 0;
        this.quantidadeMinima = Integer.parseInt(dados.quantidadeMinima());
        this.quantidadeMaxima = Integer.parseInt(dados.quantidadeMaxima());
        this.tipo = dados.tipo();
        this.ativo = true;
    }

    public void adicionaEstoque(Integer quantidade) {
        this.quantidadeEstoque += quantidade;
    }

    public void saidaEstoque(Integer quantidade) {
        this.quantidadeEstoque -= quantidade;
    }

    public void excluir() {
        this.ativo = false;
    }
}
