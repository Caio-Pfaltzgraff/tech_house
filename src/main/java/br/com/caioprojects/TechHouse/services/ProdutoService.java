package br.com.caioprojects.TechHouse.services;

import br.com.caioprojects.TechHouse.domain.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<DadosListagemProdutos> findAll() {
        return produtoRepository.findAllByAtivoTrue().stream().map(DadosListagemProdutos::new).toList();
    }

    @Transactional(readOnly = true)
    public List<DadosProdutosEmFalta> findAllByQuantidadeEstoqueLessThanQuantidadeMinima() {
        return produtoRepository.findAllByQuantidadeEstoqueLessThanQuantidadeMinima().stream().map(DadosProdutosEmFalta::new).toList();
    }

    @Transactional
    @CacheEvict(value = "produtos", allEntries = true)
    public void save(Produto produto) {
        produtoRepository.save(produto);
    }

    @Transactional
    @CacheEvict(value = "produtos", allEntries = true)
    public void atualiza(DadosAlteracaoProduto dados) {
        produtoRepository.getReferenceById(dados.id()).atualizaDados(dados);
    }

    @Transactional
    @CacheEvict(value = "produtos", allEntries = true)
    public void delete(Long idProduto) {
        produtoRepository.getReferenceById(idProduto).excluir();
    }

    public Integer quantidadeProdutos() {
        Integer qtdProdutos = 0;
        for (DadosListagemProdutos p : findAll()) {
            qtdProdutos += p.quantidadeEstoque();
        }

        return qtdProdutos;
    }

    public List<TipoProduto> tipoProdutos() {
        return Arrays.stream(TipoProduto.values()).toList();
    }

    @Transactional(readOnly = true)
    public Produto getReferenceById(Long idProduto) {
        return produtoRepository.getReferenceById(idProduto);
    }

}
