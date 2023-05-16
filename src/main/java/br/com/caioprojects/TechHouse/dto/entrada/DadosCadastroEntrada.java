package br.com.caioprojects.TechHouse.dto.entrada;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroEntrada(LocalDate dataEntrada,@NotNull Long id, @NotNull Integer quantidadeProduto) {

}
