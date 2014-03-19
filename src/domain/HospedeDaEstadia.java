package domain;

public class HospedeDaEstadia {

	private Long id;
	private Estadia estadia;
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
