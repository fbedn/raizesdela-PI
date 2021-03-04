package br.com.raizesdela.raizesDela.controller;

import java.util.Optional;
import br.com.raizesdela.raizesDela.model.UsuarioLogin;
import br.com.raizesdela.raizesDela.model.Usuario;
import br.com.raizesdela.raizesDela.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin>Autentication(@RequestBody Optional <UsuarioLogin> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		Optional<Usuario> user = Optional.ofNullable(usuarioService.CadastrarUsuario(usuario));
		try {
				return ResponseEntity.ok(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
}