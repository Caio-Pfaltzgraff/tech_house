package br.com.caioprojects.TechHouse.services;

import br.com.caioprojects.TechHouse.domain.movimento.*;
import br.com.caioprojects.TechHouse.domain.produto.DadosListagemProdutos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MovimentoService {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ItemMovimentoRepository itemMovimentoRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<DadosListagemProdutos> buscaProdutosEntrada() {
        return produtoService.findAll().stream().sorted(Comparator.comparing(DadosListagemProdutos::nome)).toList();
    }

    public List<DadosListagemProdutos> buscaProdutosSaida() {
        List<DadosListagemProdutos> produtos = new ArrayList<DadosListagemProdutos>();
        for (DadosListagemProdutos p : produtoService.findAll()) {
            if (p.quantidadeEstoque() > 0) {
                produtos.add(p);
            }
        }
        return produtos;
    }

    @Transactional
    @CacheEvict(value = "produtos", allEntries = true)
    public void save(Movimento movimento) {
        movimentoRepository.save(movimento);

        if (movimento.getTipo().equals(TipoMovimento.ENTRADA)) {
            for (ItemMovimento item : movimento.getItensMovimento()) {
                item.getId().setNumeroMovimento(movimento.getNumero());
                itemMovimentoRepository.save(item);
                produtoService.getReferenceById(item.getProduto().getId()).adicionaEstoque(item.getQuantidade());
            }
            return;
        }

        for (ItemMovimento item : movimento.getItensMovimento()) {
            item.getId().setNumeroMovimento(movimento.getNumero());
            itemMovimentoRepository.save(item);
            produtoService.getReferenceById(item.getProduto().getId()).saidaEstoque(item.getQuantidade());
        }
    }

}
