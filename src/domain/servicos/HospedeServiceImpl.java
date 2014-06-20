package domain.servicos;

import org.springframework.transaction.annotation.Transactional;

import repositorios.HospedeRepositorio;
import domain.Hospede;
import domain.exceptions.HotelException;
import domain.servicos.interfaces.HospedeService;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class HospedeServiceImpl implements HospedeService {
	
	private HospedeRepositorio hospedeRepositorio;

	public HospedeServiceImpl(HospedeRepositorio hospedeRepositorio){
		this.hospedeRepositorio = hospedeRepositorio;
	}

	@Transactional
	public Hospede buscarESalvarOuAtualizar(Hospede hospede) {
		if (hospede.getEmail() == null)
			throw new HotelException("Email não informado");
		
		Hospede hospedeExistente = hospedeRepositorio.buscaPorEmail(hospede.getEmail());
		
		if (hospedeExistente == null){
			hospedeExistente = new Hospede();
			hospedeExistente.setNome(hospede.getNome());
			hospedeExistente.setSobrenome(hospede.getSobrenome());
			hospedeExistente.setEmail(hospede.getEmail());
			hospedeExistente.setCidade(hospede.getCidade());
			hospedeExistente.setTelefone(hospede.getTelefone());
			hospedeExistente.setCelular(hospede.getCelular());
			hospedeRepositorio.salva(hospedeExistente);
		}else{
			hospedeExistente.setNome(hospede.getNome());
			hospedeExistente.setSobrenome(hospede.getSobrenome());
			hospedeExistente.setCidade(hospede.getCidade());
			hospedeExistente.setTelefone(hospede.getTelefone());
			hospedeExistente.setCelular(hospede.getCelular());
			hospedeRepositorio.atualiza(hospedeExistente);
		}
		return hospedeExistente;
	}

}
