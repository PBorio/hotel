package repositorios;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio {

	    public UserDetails loadUserByUsername(String username);
	
}
