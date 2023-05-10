package br.com.caioprojects.TechHouse.model.produto;

public enum TipoProduto {

    NOTEBOOK("Notebook"),
    TABLET("Tablet"),
    CELULAR("Celular"),
    ACESSORIOS("Acessórios"),
    MONITORES("Monitores");

    private String descricao;

    TipoProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
