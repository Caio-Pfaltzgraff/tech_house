package br.com.caioprojects.TechHouse.domain.movimento;

public enum TipoMovimento {

    ENTRADA("Entrada"),
    SAIDA("Saida");

    private String descricao;

    TipoMovimento(String descricao) {  this.descricao = descricao; }

    public String getDescricao() {
        return descricao;
    }
}
