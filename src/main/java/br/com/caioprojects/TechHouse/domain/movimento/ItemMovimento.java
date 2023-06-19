package br.com.caioprojects.TechHouse.domain.movimento;

import br.com.caioprojects.TechHouse.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "ItemMovimento")
@Table(name = "item_movimentos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemMovimento {

    @EmbeddedId
    private ItemMovimentoId id;

    private Integer quantidade;

    private BigDecimal valor = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "movimento_numero", insertable = false, updatable = false)
    private Movimento movimento;

    @ManyToOne
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private @Setter Produto produto;

    public ItemMovimento(Integer quantidade, Movimento movimento, Produto produto) {
        this.id = new ItemMovimentoId(movimento.getNumero(), produto.getId());
        this.quantidade = quantidade;
        this.movimento = movimento;
        this.produto = produto;
        this.valor = valor.add(produto.getPreco().multiply(new BigDecimal(quantidade)));
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
        this.id.setNumeroMovimento(movimento.getNumero());
    }

    @Override
    public String toString() {
        return "{nome = '" + produto.getNome() +"' , quantidade = '" + quantidade + "'}";
    }
}
