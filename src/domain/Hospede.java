package domain;

public class Hospede {

	private String nome;
	private String cidade;
	private String email;
	private String telefone;
	private String celular;

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

}
