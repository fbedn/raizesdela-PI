package br.com.raizesdela.raizesDela.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.raizesdela.raizesDela.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
	public List<Categoria> findAllByNomeContainingIgnoreCase (String nome);
}
