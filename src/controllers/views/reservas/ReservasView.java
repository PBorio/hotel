package controllers.views.reservas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Quarto;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class ReservasView {
	
	private Long idCategoria;
	
	private Date chegada;
	
	private Date saida;
	
	private Integer numeroCriancas0a5;
	
	private Integer numeroCriancas6a16;
	
	private Integer numeroCriacas17a18;
	
	private Integer numeroAdultos;
	
	private String nomeHospede;
	
	private String sobrenomeHospede;
	
	private String emailHospede;
	
	private String cidadeHospede;
	
	private String telefoneHospede;
	
	private String celularHospede;
	
	private List<Quarto> quartos = new ArrayList<Quarto>();

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Date getChegada() {
		return chegada;
	}

	public void setChegada(Date chegada) {
		this.chegada = chegada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public Integer getNumeroCriancas0a5() {
		return numeroCriancas0a5;
	}

	public void setNumeroCriancas0a5(Integer numeroCriancas0a5) {
		this.numeroCriancas0a5 = numeroCriancas0a5;
	}

	public Integer getNumeroCriancas6a16() {
		return numeroCriancas6a16;
	}

	public void setNumeroCriancas6a16(Integer numeroCriancas6a16) {
		this.numeroCriancas6a16 = numeroCriancas6a16;
	}

	public Integer getNumeroCriacas17a18() {
		return numeroCriacas17a18;
	}

	public void setNumeroCriacas17a18(Integer numeroCriacas17a18) {
		this.numeroCriacas17a18 = numeroCriacas17a18;
	}

	public Integer getNumeroAdultos() {
		return numeroAdultos;
	}

	public void setNumeroAdultos(Integer numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}

	public String getSobrenomeHospede() {
		return sobrenomeHospede;
	}

	public void setSobrenomeHospede(String sobrenomeHospede) {
		this.sobrenomeHospede = sobrenomeHospede;
	}

	public String getEmailHospede() {
		return emailHospede;
	}

	public void setEmailHospede(String emailHospede) {
		this.emailHospede = emailHospede;
	}

	public String getCidadeHospede() {
		return cidadeHospede;
	}

	public void setCidadeHospede(String cdiadeHospede) {
		this.cidadeHospede = cdiadeHospede;
	}

	public String getTelefoneHospede() {
		return telefoneHospede;
	}

	public void setTelefoneHospede(String telefoneHospede) {
		this.telefoneHospede = telefoneHospede;
	}

	public String getCelularHospede() {
		return celularHospede;
	}

	public void setCelularHospede(String celularHospede) {
		this.celularHospede = celularHospede;
	}

	public List<Quarto> getQuartos() {
		return quartos;
	}

	public void addQuarto(Quarto quarto) {
		quartos.add(quarto);
	}

}
