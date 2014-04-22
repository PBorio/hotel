<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservas</title>
</head>
<body>
    <div class="content-reservas">
				
		<form method="post" action='<c:url value="/reservas/salva"/>'>
					
					<div class="reservas-box01">
					
						<h3>Solicite Orçamento, Sem Compromisso</h3>
						
						<fieldset>
						    <p>
								<c:forEach var="error" items="${errors}">
								  <p class="mensagem">
										${error.message}
									</p>
							    </c:forEach>
							</p>
							<p>
								<select id="reservasView.idCategoria" name="reservasView.idCategoria" >  
				                    <option> Categorias...</option>  
				                    <c:forEach var="categoria" items="${categoriaList}">  
				                        <option value="${categoria.id}"> 
				                        	${categoria.descricao} 
				                        </option>  
				                    </c:forEach>  
				                </select>  	
							</p>
							
                            <p class="half">
								<input name="reservasView.chegada" type="text" id="reservasView.chegada" />		
							</p>
							
							<p class="half">
								<select name="reservasView.numeroCriancas0a5">
									<option value="">Nº de crianças (0-5)</option>
									<option value="0">0</option>
									<option value="1">01</option>
									<option value="2">02</option>
									<option value="3">03</option>
									<option value="4">04</option>
                                    <option value="5">05</option>
									<option value="6">06</option>
									<option value="7">07</option>
									<option value="8">08</option>
									<option value="9">09</option>
									<option value="10">10</option>
								</select>
							</p>
							
							<p class="half">
								<input name="reservasView.saida" type="text" id="reservasView.saida" />		
							</p>
							
							<p class="half">
								<select name="reservasView.numeroCriancas6a16">
									<option value="">Nº de crianças (6-16)</option>
									<option value="0">0</option>
									<option value="1">01</option>
									<option value="2">02</option>
									<option value="3">03</option>
									<option value="4">04</option>
                                    <option value="5">05</option>
									<option value="6">06</option>
									<option value="7">07</option>
									<option value="8">08</option>
									<option value="9">09</option>
									<option value="10">10</option>
								</select>
							</p>
							
							<p class="half">
								<select name="reservasView.numeroAdultos">
									<option value="">Nº de adultos</option>
									<option value="1">01</option>
									<option value="2">02</option>
									<option value="3">03</option>
									<option value="4">04</option>
                                    <option value="5">05</option>
									<option value="6">06</option>
									<option value="7">07</option>
									<option value="8">08</option>
									<option value="9">09</option>
									<option value="10">10</option>
								</select>
							</p>
							
							<p class="half">
								<select name="reservasView.numeroCriancas17a18">
									<option value="">Nº de crianças (16-18)</option>
									<option value="0">0</option>
									<option value="1">01</option>
									<option value="2">02</option>
									<option value="3">03</option>
									<option value="4">04</option>
                                    <option value="5">05</option>
									<option value="6">06</option>
									<option value="7">07</option>
									<option value="8">08</option>
									<option value="9">09</option>
									<option value="10">10</option>
								</select>
							</p>
							
							<div class="clear"></div>
												
						</fieldset>
						
						<div class="clear"></div>
										
					</div>
					
					<div class="reservas-box02">
					
						<h3>Meus Dados Pessoais</h3>
						
						<fieldset>
							<p>
							    <span>Nome: </span>
								<input name="reservasView.nomeHospede" type="text" id="reservasView.nomeHospede"  />		
							</p>
							<p>
								 <span>Sobrenome: </span>
								<input name=reservasView.sobrenomeHospede type="text" id="reservasView.sobrenomeHospede" />		
							</p>
							<p class="half">
								 <span>Email: </span>
								<input name="reservasView.emailHospede" type="text" id="reservasView.emailHospede" />		
							</p>
							<p class="half">
							    <span>Cidade: </span>
								<input name="reservasView.cidadeHospede" type="text" id="reservasView.cidadeHospede" />		
							</p>
							<p class="half">
								<span>Telefone: </span>
								<input name="reservasView.telefoneHospede" type="text" id="reservasView.telefoneHospede" />		
							</p>
							<p class="half">
								<span>Celular: </span>
								<input name="reservasView.celularHospede" type="text" id="reservasView.celularHospede" />		
							</p>
						</fieldset>
						
						<div class="clear"></div>
										
					</div>
					
					<div class="reservas-box03">
					
						<h3>Serviços Adicionais</h3>
						
						<fieldset>
							<h5>
								Travessias*
								<span>*preço único para até 5 pessoas.</span>
							</h5>
							
							<ul>
								<li>
									<span class="valor">R$ 260,00</span>
									<span><input name="campoServicos[]" type="checkbox" value="Travessia - Paranaguá" /> Paranaguá (ida):</span>
								</li>
								<li>
									<span class="valor">R$ 80,00</span>
									<span><input name="campoServicos[]" type="checkbox" value="Travessia - Pontal do Sul Dia" /> Pontal do Sul /dia (ida):</span>
								</li>
								<li>
									<span class="valor">R$ 100,00</span>
									<span><input name="campoServicos[]" type="checkbox" value="Travessia - Pontal do Sul Noite" /> Pontal do Sul /noite (ida):</span>
								</li>
							</ul>
							
							<h5>
								Passeios de Barco*
								<span>*preço único para até 5 pessoas.</span>
							</h5>
							
							<ul>
								<li>
									<span class="valor">R$ 260,00</span>
									<span><input name="campoServicos[]" type="checkbox" value="Passeio de Barco - Golfinhos" /> Golfinhos (ida e volta):</span>
								</li>
								<li>
									<span class="valor">R$ 80,00</span>
									<span><input name="campoServicos[]" type="checkbox" value="Passeio de Barco - Gruta das Encantadas" /> Gruta das Encantadas (ida e volta):</span>
								</li>
							</ul>
							
							<h5>
								Locações
							</h5>
							
							<ul>
								<li>
									<span class="valor">R$ 10,00</span>
									<span><input name="campoServicos[]" type="checkbox" value="Locação - Cadeiras de praia e guarda sol" /> Cadeiras de praia e guarda sol (valor por dia):</span>
								</li>
								<li>
									<span class="valor">R$ 30,00</span>
									<span><input name="campoServicos[]" type="checkbox" value="Locação - Cadeiras de praia e guarda sol" /> Bicicleta (valor por período):</span>
								</li>
							</ul>
						</fieldset>
						
						<div class="clear"></div>
										
					</div>
					
					<div class="reservas-box04">
					
						<h3>Dúvidas e Comentários</h3>
						
						<fieldset>
							<p>
								<textarea name="campoMensagem" cols="" rows="" id="reservas-mensagem">Mensagem</textarea>
							</p>
							<p>
								<input name="Enviar" type="submit" value="Enviar" />
								<!--<input name="campoNews" type="checkbox" checked /> <span>Quero receber notícias e promoções da pousada por e-mail.</span>-->
							</p>
							<div class="clear"></div>
							<span class="sucess">Enviado com sucesso!</span>
						</fieldset>
						
						<div class="clear"></div>
										
					</div>
					
					</form>
			</div>
</body>
</html>