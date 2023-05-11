package br.com.caioprojects.TechHouse.dto;

import br.com.caioprojects.TechHouse.model.produto.Produto;

public record DadosProdutosEmFalta(Long id, String nome, int quantidadeEstoque, int quantidadeMinima, int minComprar, int maxComprar) {
    public DadosProdutosEmFalta(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getQuantidadeEstoque(), produto.getQuantidadeMinima(),
                (produto.getQuantidadeMinima() - produto.getQuantidadeEstoque()),
                (produto.getQuantidadeMaxima() - produto.getQuantidadeEstoque())
        );
    }

}
