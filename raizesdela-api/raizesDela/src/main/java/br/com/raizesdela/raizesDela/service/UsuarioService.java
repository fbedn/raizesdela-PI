package br.com.raizesdela.raizesDela.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import br.com.raizesdela.raizesDela.model.UsuarioLogin;
import br.com.raizesdela.raizesDela.model.Usuario;
import br.com.raizesdela.raizesDela.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public Usuario CadastrarUsuario(Usuario usuario) {
		if (repository.findAllByEmailContainingIgnoreCase(usuario.getEmail()).isPresent() && usuario.getId() == 0) {
			return null;
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findAllByEmailContainingIgnoreCase(user.get().getEmail());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encondedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encondedAuth);
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setTipoVendedor(usuario.get().getTipoVendedor());
				user.get().setId(usuario.get().getId());

				return user;

			}
		}

		return null;
	}
}