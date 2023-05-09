package br.com.caioprojects.TechHouse.repository;

import br.com.caioprojects.TechHouse.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
