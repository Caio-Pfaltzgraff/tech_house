package br.com.caioprojects.TechHouse.domain.movimento;

import br.com.caioprojects.TechHouse.domain.produto.ProdutoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Getter
public class EntradaProdutoService {

    private Movimento movimento = new Movimento();

    @Autowired
    private ProdutoRepository produtoRepository;

    public Movimento criaEntrada(LocalDate data) {
        this.movimento.setData(data);
        var entrada = new Movimento(data, BigDecimal.ZERO, TipoMovimento.ENTRADA);
        entrada.recebeItens(this.movimento.getItensMovimento());
        return entrada;
    }

    public void adicionaProduto(Long produtoId, Integer quantidade) {
        if(produtoId != null && quantidade != null) {
            this.movimento.adicionarItem(new ItemMovimento(quantidade, movimento, this.produtoRepository.getReferenceById(produtoId)));
        }
    }

    public void removeProduto(ItemMovimento itemMovimento) {
        this.movimento.getItensMovimento().remove(itemMovimento);
    }

    public List<ItemMovimento> listaDeProdutos() {
        return this.movimento.getItensMovimento();
    }
}
