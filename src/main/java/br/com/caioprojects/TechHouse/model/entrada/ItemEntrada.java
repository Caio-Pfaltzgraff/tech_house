package br.com.caioprojects.TechHouse.model.entrada;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ItemEntrada")
@Table(name = "itens_entrada")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntrada {

    @EmbeddedId
    private ItemEntradaId id;

    @Column(name = "quantidade_produto")
    private Integer quantidadeProduto;

}
