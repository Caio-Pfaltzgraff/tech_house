package br.com.caioprojects.TechHouse.domain.movimento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMovimentoRepository extends JpaRepository<ItemMovimento, ItemMovimentoId> {
}
