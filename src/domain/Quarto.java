package domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import domain.nulos.ReservaNulo;

@Entity
@Table(name="quartos")
public class Quarto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	
	private String descricao;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;

 	@OneToMany(mappedBy="quarto")
  	@Where(clause="(checkin is null and cancelamento is null)")
  	private Set<Reserva> reservas = new HashSet<Reserva>();

	private String foto;
	
	private Integer capacidade = 0;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}

	public boolean possuiReservasNoMesmoPeriodo(Reserva proximaReserva) {
		for (Reserva r : this.reservas){
			if (r.equals(proximaReserva))
				continue;
			if (r.coincideCom(proximaReserva))
				return true;
		}
		return false;
	}
	
	public boolean possuiReservaNoDia(DateTime dia){
		for (Reserva r : this.reservas){
			if (r.contemAData(dia))
				return true;
		}
		return false;
	}
	
	public Reserva reservaNaData(DateTime dia){
		for (Reserva r : this.reservas){
			if (r.contemAData(dia))
				return r;
		}
		return new ReservaNulo();
	}

	@Override
	public int hashCode() {
		if (id == null) return super.hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quarto other = (Quarto) obj;
		if (id == null) 
			return false;
		if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setCaminhoFoto(URI foto) {
		this.foto = foto == null ? null : foto.toString();
	}

	public URI getCaminhoFoto() {
		if (foto == null) return null;
		return URI.create(foto);
	}

	@Deprecated
	/** 
	 * Somente para html
	 */
	public String getFoto() {
		return foto;
	}

	@Deprecated
	/** 
	 * Somente para html
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
}
