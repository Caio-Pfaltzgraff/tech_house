package br.com.caioprojects.TechHouse.dto;

import br.com.caioprojects.TechHouse.model.produto.Produto;
import br.com.caioprojects.TechHouse.model.produto.TipoProduto;

import java.math.BigDecimal;

public record DadosListagemProdutos(Long id, String nome, TipoProduto tipo, BigDecimal preco, Integer quantidadeEstoque) {

    public DadosListagemProdutos(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getTipo(), produto.getPreco(), produto.getQuantidadeEstoque());
    }

}
