package br.com.caioprojects.TechHouse.domain.movimento;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemMovimentoId {

    @Column(name = "numero_movimento")
    private @Setter Long numeroMovimento;

    @Column(name = "id_produto")
    private Long idProduto;

}
