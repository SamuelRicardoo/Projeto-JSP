<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="preLoadStart.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navBar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navBarMainMenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-head.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row"></div>

									</diV>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<!-- Basic Form Inputs card start -->
								<div class="card">
									<div class="card-header">
										<div class="card-block">
										
											<h4 class="sub-title">CADASTRO DE USUÁRIO</h4>
											
											<form class="form- material" enctype="multipart/form-data"  action="<%=request.getContextPath()%>/ServeletUsuarioController" method="post" id="formUser">
												
												<input type="hidden" name="acao" id="acao" value="">

												
												
												<div class="form-row">
												
													<div class="form-group col-md-1">
														<label for="id">ID:</label> <input type="text" class="form-control" id="id" name="id" readonly="readonly" value="${modolLogin.id}">
													</div>
													
													<div class="form-group form-default input group mb-5">
														<div class="input-group-prepend">
															<img alt="imagem User" id="fotobase64" src="" style="width: 100px; height: 100px;">
														</div>
														<input type="file" accept="image/*" id="fileFoto" name="fileFoto" onchange="visualizarImg('fotobase64','fileFoto')"  class="form-control-file" style="margin-top: 6px; margin-left: 5px">
													
													</div>	
												</div>
												
												<div class="form-row">
													
													<div class="form-group col-md-6">
														<label for="nome">NOME:</label> <input type="text"name="nome" class="form-control" id="nome" placeholder="NOME" required="required" value="${modolLogin.nome}">
													</div>
													
													<div class="form-group col-md-3">
														<label for="login">Login:</label> <input type="text" class="form-control" name="login" id="login" placeholder="Login do usuario" required="required" autocomplete="off" value="${modolLogin.login}">
													</div>
																																													
												</div>
														
												<div class="form-row">
													<div class="form-group col-md-6">
														<label for="id">EMAIL:</label> <input type="email"class="form-control" name="email" id="email" placeholder="EMAIL" required="required" autocomplete="off" value="${modolLogin.email}">
													</div>
													<div class="form-group col-md-3">
														<label for="senha">SENHA:</label> <input type="password" class="form-control" name="password" id="password" placeholder="SENHA" required="required" autocomplete="off" value="${modolLogin.password}">
													</div>											
												</div>												

													<div>
														<select class="form-group col-md-3" aria-label=".form-select-sm example" name="PerfilUser" id="perfilUser">
														  <option disabled="disabled">Selecione o Perfil</option>
										  
														  <option value="Admin" <%
														  
															ModelLogin model = (ModelLogin) request.getAttribute("modolLogin");
																													 

														  if(model != null && model.getPerfilUser().equals("Admin")) {		  
															  out.print(" ");
															 	out.print("selected=\"selected\"");
															  out.print(" ");											  
														  } %> >Admin</option>
														   
														  
														<option value="Secretario"
														 <%																  														
															
																if(model != null && model.getPerfilUser().equals("Secretario")) {		  
																  out.print("");
																 	out.print("selected=\"selected\"");
																  out.print("");											  
															 } 
														  %>
														>Secretario</option>														  
														  <option value="Auxiliar" 
														  	<%
														  	 
															  if(model != null && model.getPerfilUser().equals("Auxiliar")) {		  
																  out.print(" ");
																 	out.print("selected=\"selected\"");
																  out.print(" ");											  
															  } 
														 	 %>
														  >Auxiliar</option>
														  
														</select>
														
														
														
														<div class="form-group">
															<input type="radio" name="sexo" id="sexoId" value="FEMININO" style="left: 10cm"
																<%
														  
																model = (ModelLogin) request.getAttribute("modolLogin");
																System.out.print("modolLogin in JSP: " + model);														 

															    if(model != null && model.getSexo().equals("FEMININO")) {		  
																	  out.print(" ");
																	 	out.print("checked=\"checked\"");
																	  out.print(" ");											  
																  } %>
															
															
															> F</>

  															<input type="radio" name="sexo" id="sexoId" value="MASCULINO" style="left: 20cm"
  															
  															<%					
																model = (ModelLogin) request.getAttribute("modolLogin");
																System.out.print("modolLogin in JSP: " + model);														 

															    if(model != null && model.getSexo().equals("MASCULINO")) {		  
																	  out.print(" ");
																	 	out.print("checked=\"checked\"");
																	  out.print(" ");											  
																  } %>
  															> M</>								
														</div>
																									
														
													</div>
																						
												
													
												<p></p>
												
												<button class="btn waves-effect waves-light btn-grd-primary col-md-2" type="button" onclick="limparForm();" >NOVO</button>			
																		
												<button class="btn waves-effect waves-light btn-grd-primary col-md-2" type="submit">SALVAR</button>			
																					
												<button class="btn waves-effect waves-light btn-grd-primary col-md-2" type="button" onclick="deleteAjax();">EXCLUIR</button>
												
												<button type="button" class="btn waves-effect waves-light btn-grd-primary col-md-2" data-toggle="modal" data-target="#exampleModalUser"> PESQUISAR</button>
												
												<p></p>
												
											</form>
											<div>
												<h5 class="msg">${msg}</h5>
											</div>
												<div style="height: 300px; overflow: scroll">
				
													<table class="table table-hover" id="tabelaResultadoTodos">
													  <thead>
													    <tr>
													      <th scope="col">Id</th>
													      <th scope="col">Nome</th>
													      <th scope="col">Ver</th>
													    </tr>
													  </thead>
													  <tbody>
													  	<c:forEach items="${modelLogins}" var="modelResultado">
													  	
													  		<tr>
													  		<td><c:out value="${modelResultado.id}"></c:out></td>
													  		<td><c:out value="${modelResultado.nome}"></c:out></td>
													  		<td><a class="btn btn-primary" href="<%= request.getContextPath()%>/ServeletUsuarioController?acao=buscarEditar&id=${modelResultado.id}">Ver</a></td>
													  		</tr>	
													  	
													  	</c:forEach>
													  </tbody>
													</table>	
													</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="styleSelector"></div>

	<jsp:include page="jsFile.jsp"></jsp:include>
	
	
			<!-- Modal -->
		<div class="modal fade" id="exampleModalUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Pesquisar</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">


		      
		       <div class="input-group mb-3">
				  	<input type="text" class="form-control" id="buscaNome" placeholder="username" aria-label="Recipient's username" aria-describedby="basic-addon2">
				  <div class="input-group-append">
				    <button class="btn btn-primary" type="button" onclick="buscarUsuario();">Buscar</button>
				  </div>
				</div>
				
				<div style="height: 300px; overflow: scroll">
				
				<table class="table table-hover" id="tabelaResult">
				  <thead>
				    <tr>
				      <th scope="col">Id</th>
				      <th scope="col">Nome</th>
				      <th scope="col">Ver</th>
				    </tr>
				  </thead>
				  <tbody>
				  
				  </tbody>
				</table>	
				</div>
		      </div>
		      
		     <nav class="nav nav-pills nav-justified">
  				<a class="nav-item nav-link active" href="#"><span id="totalResult"></span></a>
		     </nav>
		     
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
		      </div>
		     
		     
		     
		     
				
						      
		    </div>
		  </div>
		</div>
	
	
	<script type="text/javascript">
	
	function visualizarImg(fotomobase64, filefoto) {
		
		var preview = document.getElementById(fotomobase64);//Campo IMG HTML
		var fileUser = document.getElementById(filefoto).files[0]; 
		var reader = new FileReader();
		
		reader.onloadend = function () {
			preview.src = reader.result;/*Carrega a foto na tela*/
			
		}
		
		if(fileUser){
			reader.readAsDataURL(fileUser); /*Preview da imagem*/
		}else{
			preview.src = '';
		}
		
	}
	
	
	
	
	
	function buscarUsuario(){
		
		var nomeUser = document.getElementById('buscaNome').value;
		
		if(nomeUser != null && nomeUser!= '' && nomeUser.trim() != ''){ //validando a busca
			
			var urlAction = document.getElementById('formUser').action;
			
			$.ajax({
			            method: "get",
			            url: urlAction,
			            data: "nomeUser=" + nomeUser + '&acao=buscaUserjax',
			            success: function (response) {
			            	
			            var json = JSON.parse(response);
			            $('#tabelaResult > tbody tr').remove();

			            	
			            for (var i = 0; i < json.length; i++) {
			            	 
			           		 $('#tabelaResult > tbody').append('<tr><td>' + json[i].id + '</td> <td>'+json[i].nome+'</td> <td><button class="btn btn-primary" type="button" onclick="verEditar('+json[i].id+');">ver</button></td> </tr>');
     	
			            }
						document.getElementById('totalResult').textContent = 'Resultados:'+ json.length;
			            	
			            	
			              
			            }
			        }).fail(function (xhr, status, errorThrown) {
			            alert('Erro ao buscar Usuario' + xhr.responseText);
			        });
			    }
		
	}
	
		
	function verEditar(id) {
		
		 var urlAction = document.getElementById('formUser').action;
		 
		 window.location.href = urlAction + '?acao=buscarEditar&id='+id;
		
	}
	
	
	
	
	
	
	
	function deleteAjax() {
	    if (confirm("Deseja Realmente excluir?")) {
	        var urlAction = document.getElementById('formUser').action;
	        var idUser = document.getElementById('id').value;

	        $.ajax({
	            method: "get",
	            url: urlAction,
	            data: "id=" + idUser + '&acao=deletarjax',
	            success: function (response) {
	            	
	            	limparForm();
	                alert(response);
	            }
	        }).fail(function (xhr, status, errorThrown) {
	            alert('Erro ao deletar Usuario por ID: ' + xhr.responseText);
	        });
	    }
	}
	
	
	
	
	
	
	function criarDelete() {
		
		if(confirm("Deseja Realmente excluir?")){
		  document.getElementById("formUser").method = 'get';
		  document.getElementById("acao").value = 'deletar';
		  document.getElementById("formUser").submit();
		}
	}
	
	
	
	
	
	
	
	
	
	function limparForm() {
		var elementos = document.getElementById("formUser").elements; //Retorna elementos html dentro do form
		
		for (p = 0; p < elementos.length; p++) {
			
			elementos[p].value = '';
			
		}
		
	}
	
	
	
	</script>
	
	
</body>
</html>
