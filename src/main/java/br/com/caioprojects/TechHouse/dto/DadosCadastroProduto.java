package br.com.caioprojects.TechHouse.dto;

import br.com.caioprojects.TechHouse.model.produto.TipoProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record DadosCadastroProduto(

        @NotBlank
        String nome,

        @NotNull
        BigDecimal preco,

        @NotBlank
        @Pattern(regexp = "\\d{1,4}")
        String quantidadeMinima,

        @NotBlank
        @Pattern(regexp = "\\d{1,4}")
        String quantidadeMaxima,

        @NotNull
        TipoProduto tipo) {

}
