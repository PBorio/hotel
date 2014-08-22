package controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import domain.Usuario;

@Resource
public class LoginController {
	
	@Get
	@Path("/login/")
	public void login(Usuario usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
	}
	
	@Post("/login/logar/")
	public void logar(Usuario usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(usuario.getSenha().getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		System.out.println(hexString.toString());
	}
	

}
