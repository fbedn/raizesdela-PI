package br.com.raizesdela.raizesDela.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.raizesdela.raizesDela.model.Usuario;
import br.com.raizesdela.raizesDela.repository.UsuarioRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UsuarioRepository userRepository;
	@Override
	public UserDetails loadUserByUsername (String userEmail) throws UsernameNotFoundException{
		Optional<Usuario> user = userRepository.findAllByEmailContainingIgnoreCase(userEmail);
		user.orElseThrow(() -> new UsernameNotFoundException(userEmail + " not found."));
		return user.map(UserDetailsImpl::new).get();
	}
}

