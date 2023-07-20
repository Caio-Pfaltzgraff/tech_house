package br.com.caioprojects.TechHouse.domain.produto;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PesquisaProduto {
    private List<DadosListagemProdutos> produtos;
    private String pesquisa;

    public PesquisaProduto(List<DadosListagemProdutos> produtos, String pesquisa) {
        this.produtos = produtos;
        this.pesquisa = pesquisa;
    }

    public List<DadosListagemProdutos> realizarPesquisa() {
        Set<String> palavrasChave = Arrays.stream(pesquisa.toLowerCase().split(" "))
                .collect(Collectors.toSet());

        return produtos.stream()
                .filter(p -> contemPalavrasChave(p, palavrasChave) || temDistanciaLevenshteinAceitavel(p, pesquisa))
                .collect(Collectors.toList());
    }

    private Boolean contemPalavrasChave(DadosListagemProdutos produto, Set<String> palavrasChave) {
        String nomeProduto = produto.nome().toLowerCase();
        String tipoProduto = produto.tipo().getDescricao().toLowerCase();

        return palavrasChave.stream()
                .anyMatch(palavra -> nomeProduto.contains(palavra) || tipoProduto.contains(palavra));
    }

    private Boolean temDistanciaLevenshteinAceitavel(DadosListagemProdutos produto, String pesquisaLowerCase) {
        String nomeProduto = produto.nome().toLowerCase();
        String tipoProduto = produto.tipo().getDescricao().toLowerCase();

        return StringUtils.getLevenshteinDistance(nomeProduto, pesquisaLowerCase) <= 2 ||
                StringUtils.getLevenshteinDistance(tipoProduto, pesquisaLowerCase) <= 2;
    }

}
