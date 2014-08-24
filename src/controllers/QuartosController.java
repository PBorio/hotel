package controllers;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

import repositorios.ArquivosRepositorio;
import repositorios.CategoriaRepositorio;
import repositorios.QuartoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.google.common.io.ByteStreams;

import domain.Arquivo;
import domain.Categoria;
import domain.Quarto;
import domain.exceptions.HotelException;


@Resource
public class QuartosController {
	
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private CategoriaRepositorio categoriaRepositorio;
	private ArquivosRepositorio arquivosRepositorio;
	private Validator validator;

	public QuartosController(Result result, 
							 QuartoRepositorio quartoRepositorio,
							 Validator validator,
							 CategoriaRepositorio categoriaRepositorio, 
							 ArquivosRepositorio arquivosRepositorio) {
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
		this.validator = validator;
		this.categoriaRepositorio = categoriaRepositorio;
		this.arquivosRepositorio = arquivosRepositorio;
	}
	
	@Get
	public List<Quarto> list() {
		return quartoRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/quartos/{id}")
	public void edit(Long id) {
		
		Quarto quarto = quartoRepositorio.buscaPorId(id);
		List<Categoria> categoriaList = categoriaRepositorio.buscaTodos();
		
		result.include("categoriaList", categoriaList);
		result.include("quarto", quarto);
		result.of(this).novo();
	}
	
	public void salva(Quarto quarto, UploadedFile fotografia) throws IOException{
		
		validarQuarto(quarto);
		if (validator.hasErrors()){
			result.include("quarto", quarto);
			result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			result.include("foto", fotografia);
		    validator.onErrorUsePageOf(this).novo();
		}
		
		try{
			if (fotografia != null){
				URI imagem = arquivosRepositorio.grava(new Arquivo(fotografia.getFileName(), ByteStreams.toByteArray(fotografia.getFile()), fotografia.getContentType(), new Date()));
				quarto.setCaminhoFoto(imagem);
			}
			if (quarto.getId() == null){
				quartoRepositorio.salva(quarto);
			}else{
				quartoRepositorio.atualiza(quarto);
			}
			
			result.include("mensagem", "Quarto salvo com sucesso!");
			result.redirectTo(this).list();
		}catch(HotelException e){
			e.printStackTrace();
			result.include(quarto);
			result.include("categoriaList", categoriaRepositorio.buscaTodos());
			result.include("fotografia", fotografia);
			validator. add(new ValidationMessage(e.getMessage(),"erro.no.quarto",e.getMessage()));
			validator.onErrorUsePageOf(this).novo();
		}
	}

	private void validarQuarto(Quarto quarto) {
		if (quarto.getNumero() == null || quarto.getNumero().trim().equals(""))
			validator.add(new ValidationMessage("Número é obrigatorio", "quarto"));

		if (quarto.getDescricao() == null || quarto.getDescricao().trim().equals(""))
			validator.add(new ValidationMessage("Descrição do quarto é obrigatória", "quarto"));
		
		if (quarto.getCategoria() == null || quarto.getCategoria().getId().longValue() == -1){
			validator.add(new ValidationMessage("Categoria do quarto é obrigatória", "quarto"));
		}
	}

	public void novo() {
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
		result.include("quarto", novoQuarto());
	}
	
	@Get
	@Path("/quartos/{id}/foto")
	public Download foto(Long id){
		Quarto quarto = quartoRepositorio.buscaPorId(id);
		Arquivo foto =  arquivosRepositorio.recupera(quarto.getCaminhoFoto());
		
		if (foto == null){
			result.notFound();
			return null;
		}
		
		return new ByteArrayDownload(foto.getConteudo(), foto.getContentType(), foto.getNome());
	}

	private Quarto novoQuarto() {
		return new Quarto();
	}

}
