package br.com.caioprojects.TechHouse.model.entrada;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemEntradaId {

    @Column(name = "numero_entrada")
    private Long numeroEntrada;

    @Column(name = "id_produto")
    private Long idProduto;

}
