package br.com.caioprojects.TechHouse.model.entrada;

import br.com.caioprojects.TechHouse.dto.entrada.DadosCadastroEntrada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Entrada")
@Table(name = "entradas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "numero")
public class Entrada {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @Column(name = "data_entrada")
    private LocalDate data;

    public Entrada(DadosCadastroEntrada dados) {
        this.data = dados.dataEntrada();
        verificaDataNull();
    }

    private void verificaDataNull() {
        if (this.data == null) {
            this.data = LocalDate.now();
        }
    }

}
