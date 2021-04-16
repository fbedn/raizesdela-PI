package br.com.raizesdela.raizesDela.repository;

import java.util.List;

import br.com.raizesdela.raizesDela.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

	public List<Comentario> findAllByTextoContainingIgnoreCase(String texto);

}
