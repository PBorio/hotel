package repositorios;

import java.net.URI;

import domain.Arquivo;

public interface ArquivosRepositorio {
	
	URI grava(Arquivo arquivo);
	
	Arquivo recupera(URI uri);

}
