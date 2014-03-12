package domain;

import org.junit.Assert;
import org.junit.Test;

public class ServicosPrestadosTest {
	
	@Test
	public void oServicoPrestadoTemOValorSugeridoVindoDoServico(){
		Servico lavagem = new Servico();
		lavagem.setDescricao("Descricao");
		lavagem.setValorSugerido(15.0);
		lavagem.setObservacao("Uma Observacao");
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.paraOServico(lavagem);
		
		Assert.assertEquals((Double)15.0, servicoPrestado.getValor());
	}

}
