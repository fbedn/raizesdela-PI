package br.com.raizesdela.raizesDela.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.raizesdela.raizesDela.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	public Optional<Usuario> findAllByEmailContainingIgnoreCase (String email);
}
