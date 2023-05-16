package br.com.caioprojects.TechHouse.dto.entrada;

import br.com.caioprojects.TechHouse.model.produto.Produto;

public record DadosEntradaProduto(
        Long id,
        String nomeProduto,
        Integer quantidadeEstoque,
        Integer quantidadeMaxima) {

    public DadosEntradaProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getQuantidadeEstoque(), produto.getQuantidadeMaxima());
    }


}
