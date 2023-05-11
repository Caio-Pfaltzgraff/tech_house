package br.com.caioprojects.TechHouse.repository;

import br.com.caioprojects.TechHouse.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.quantidadeEstoque < p.quantidadeMinima")
    List<Produto> findAllByQuantidadeEstoqueLessThanQuantidadeMinima();
}
