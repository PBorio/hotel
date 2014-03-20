package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hospedes_estadias")
public class HospedeDaEstadia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="estadia_id")
	private Estadia estadia;
	
	@ManyToOne
	@JoinColumn(name="hospede_id")
	private Hospede hospede;

	/**
	 * Someente para Hibernate
	 */
	@Deprecated
	public HospedeDaEstadia(){}
	
	public HospedeDaEstadia(Estadia estadia, Hospede hospede) {
		this.estadia = estadia;
		this.hospede = hospede;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estadia getEstadia() {
		return estadia;
	}

	public Hospede getHospede() {
		return hospede;
	}

}
