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
import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.google.common.io.ByteStreams;

import domain.Arquivo;
import domain.Categoria;
import domain.Quarto;


@Resource
public class QuartosController {
	
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private CategoriaRepositorio categoriaRepositorio;
	private ArquivosRepositorio arquivosRepositorio;
//	private Validator validator;

	public QuartosController(Result result, QuartoRepositorio quartoRepositorio, CategoriaRepositorio categoriaRepositorio, ArquivosRepositorio arquivosRepositorio) {
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
//		this.validator = validator;
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
	
	public void salva(Quarto quarto, UploadedFile foto) throws IOException{
		
		if (foto != null){
			URI imagem = arquivosRepositorio.grava(new Arquivo(foto.getFileName(), ByteStreams.toByteArray(foto.getFile()), foto.getContentType(), new Date()));
			quarto.setFoto(imagem);
		}
		
		if (quarto.getId() == null){
			quartoRepositorio.salva(quarto);
		}else{
			quartoRepositorio.atualiza(quarto);
		}
		
		result.include("mensagem", "Quarto salvo com sucesso!");
		result.redirectTo(this).list();
	}

	public void novo() {
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
		result.include("quarto", novoQuarto());
	}
	
	@Get
	@Path("/quartos/{id}/foto")
	public Download foto(Long id){
		Quarto quarto = quartoRepositorio.buscaPorId(id);
		Arquivo foto =  arquivosRepositorio.recupera(quarto.getFoto());
		
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
