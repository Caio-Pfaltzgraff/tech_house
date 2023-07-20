package br.com.caioprojects.TechHouse.domain.produto;

import java.math.BigDecimal;

public record DadosListagemProdutos(Long id, String nome, TipoProduto tipo, BigDecimal preco, Integer quantidadeEstoque) {

    public DadosListagemProdutos(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getTipo(), produto.getPreco(), produto.getQuantidadeEstoque());
    }

}
