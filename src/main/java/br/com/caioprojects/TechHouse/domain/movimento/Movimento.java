package br.com.caioprojects.TechHouse.domain.movimento;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Movimento")
@Table(name = "movimentos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "numero")
public class Movimento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Long numero;

    private @Setter LocalDate data;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipo;

    @OneToMany(mappedBy = "movimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMovimento> itensMovimento = new ArrayList<>();

    public Movimento(LocalDate data, BigDecimal valor, TipoMovimento tipo) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public void recebeItens(List<ItemMovimento> itensMovimento) {
        for (ItemMovimento i : itensMovimento) {
            this.adicionarItem(new ItemMovimento(i.getQuantidade(), this, i.getProduto()));
            this.valor = this.valor.add(i.getValor());
        }
    }

    public void adicionarItem(ItemMovimento item) {
        this.itensMovimento.add(item);
    }

}
