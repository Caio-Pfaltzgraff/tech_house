package br.com.caioprojects.TechHouse.repository;

import br.com.caioprojects.TechHouse.model.entrada.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EntradaRepository  extends JpaRepository<Entrada, Long> {
    Entrada findByData(LocalDate localDate);
}
