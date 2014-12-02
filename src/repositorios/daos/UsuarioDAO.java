package repositorios.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import repositorios.UsuarioRepositorio;
import controllers.session.UserSession;

@Service
public class UsuarioDAO implements UsuarioRepositorio, UserDetailsService {


	@PersistenceContext
	protected EntityManager entityManager;

	public UserDetails loadUserByUsername(String username) {
	        System.out.println("Getting access details from employee dao !!");
	 
	        try{
//	        	Quarto u = entityManager.find(Quarto.class, 1l);
	        	System.out.println("chamou e não achou");
	        }catch(Throwable e){
	        	e.printStackTrace();
	        }
	        
	        
	        // Ideally it should be fetched from database and populated instance of
	        // #org.springframework.security.core.userdetails.User should be returned from this method
	        UserDetails user = new UserSession();
	        return user;
	}

}
