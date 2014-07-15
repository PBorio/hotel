package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="hospedes")
public class Hospede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String email;
	private String telefone;
	private String celular;
	
	private Long cpf;
	private String passaporte;
	private String rne;
	
	@Transient
	private String endereco;
	
	private String cidade;
	private String estado;
	private String pais;
	
	@Transient
	private Long rg;

	private String sobrenome;
	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCelular() {
		return celular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
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
		if (!(obj instanceof Hospede))
			return false;
		Hospede other = (Hospede) obj;
		if (id == null)
			return false;
		if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCpfComMascara() {
		return String.valueOf(cpf);
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public Long getCpf(){
		return this.cpf;
	}

	public String getRgComMascara() {
		return String.valueOf(rg);
	}

	public void setRg(Long rg) {
		this.rg = rg;
	}
	
	public Long getRg(){
		return this.rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public String getRne() {
		return rne;
	}

	public void setRne(String rne) {
		this.rne = rne;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
