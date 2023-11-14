<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Primeira pagina JSP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<style type="text/css">
	form {
		position: absolute;
		top: 40%;
		left: 33%;
		right: 33%
	}
	
	h2 {
		position: absolute;
		top: 30%;
		left: 33%;
	}
	
	.msg{
		position: absolute;
		top: 100%;
		left: 25%;
		font-size: 15px;
		color: teal;
		background-color: green;
		border-color: aqua;
	}

</style>
</head>
<body>

	<h2>Bem vindo samucaixo</h2>

	<form action="<%= request.getContextPath() %>/ServeletLogin" method="post" class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%request.getParameter("url");%>" name="url">

		<div class="mb-3">
			<label class="form-label" for="login">Login:</label>
			<input class="form-control" name="login" id="login" type="text" required="required">	
			<div class="invalid-feedback"> INFORME O LOGIN!</div>
			<div class="valid-feedback"> OK!</div>
		</div>

		<div class="mb-3">
			<label class="form-label">Senha:</label> 
			<input class="form-control" name="senha" type="password" required="required">
			<div class="invalid-feedback"> INFORME A SENHA!</div>
			<div class="valid-feedback">OK!</div>
		</div>

		<div class="d-grid gap-2">
			<button class="btn btn-primary" type="submit">enviar</button>
		</div>
		
			<div>
 				<h5 class="msg">${msg}</h5>
			</div>
		
	</form>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script type="text/javascript">
	
	(function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
		})()
	
	</script>

</body>
</html>
