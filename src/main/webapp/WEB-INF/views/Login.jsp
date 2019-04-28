<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}

.jumbotron {
	margin-bottom: 0;
}

footer {
	background-color: #f2f2f2;
	padding: 25px;
}
#frame {
	border: 1px solid grey;
	background-size: 100%;
	background-image:
		url("C:\Users\aman9\Desktop\NEU\Web Tools\Project\library\background.jpg");
	background-repeat: no-repeat;
	background-position: center;
	background-color: white;
}
#textcenter{
	padding-left: 5%;
	color:  black;
}
</style>
</head>
<body>
	
	<c:set var = "contextPath" value="${pageContext.request.contextPath }"/>
	
	<div class="jumbotron" id="frame">
		<div id="textcenter">
			<h1>Library</h1>
			<p>Keep Calm And Read a Book!!</p>
		</div>
	</div>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
				</ul>
			</div>	
		</div>
	</nav>

	<h1>Enter Your credentials:</h1>
	<div class="container">
		
		<form action="${contextPath}/login" method="post">
			<div class="form-group">
					<label class="control-label col-sm-2">User Name:</label>
						<div class="col-sm-10">
							<input type="text" name="username"/><br/>
							<font color="red"><form:errors path="firstName"/></font><br/>
						</div>
			</div>
			
			<div class="form-group">
					<label class="control-label col-sm-2">Password:</label>
						<div class="col-sm-10">
							<input type="password" name="password"/><br/>
							<font color="red"><form:errors path="firstName"/></font><br/>
						</div>
			</div>
			
			<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-secondary btn-lg" value="Login" />
					</div>
			</div>				
		      
		</form>
	</div>
</body>
</html>