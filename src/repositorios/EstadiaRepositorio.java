package repositorios;

import java.util.List;

import domain.Consumo;
import domain.Estadia;
import domain.HospedeDaEstadia;
import domain.ServicoPrestado;

public interface EstadiaRepositorio {
	
	List<Estadia> buscaTodos();
	
	public Estadia buscaPorId(Long id);
	
	void salva(Estadia hospede);
	
	void atualiza(Estadia hospede);

	List<Estadia> estadiasAbertas();

	HospedeDaEstadia buscaHospedeDaEstadiaPorId(Long id);

	void excluirHospede(HospedeDaEstadia hospedeDaEstadia);

	void salvarServicoPrestado(ServicoPrestado servicoPrestado);

	void salvarConsumo(Consumo consumo);

	Consumo buscarConsumoPorId(Long id);

	void deletaConsumo(Consumo consumo);

	ServicoPrestado buscarServicoPrestadoPorId(Long id);

	void deletaServicoPrestado(ServicoPrestado servicoPrestado);

}
