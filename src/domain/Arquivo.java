package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="arquivos")
public class Arquivo {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	@Lob
	private byte[] conteudo;
	
	private String contentType;
	
	private Date dataModificacao;
	
	public Arquivo(String nome, byte[] conteudo, String contentType, Date dataModificacao){
		this.nome = nome;
		this.conteudo = conteudo;
		this.contentType = contentType;
		this.dataModificacao = dataModificacao;
	}

	public String getNome() {
		return nome;
	}

	public byte[] getConteudo() {
		return conteudo;
	}

	public String getContentType() {
		return contentType;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public Long getId() {
		return id;
	}
}
