<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
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
					<li class="active"><a href="#">Home</a></li>
					<li><button class="btn btn-default"
							data-toggle="modal" data-target="#myModalHorizontal"><a href="${contextPath }/login">Login</button></a></li>
					<li><button class="btn btn-default"
							data-toggle="modal" data-target="#myModalHorizontal"><a href="${contextPath }/addLibraryUser">Sign Up</button></a></li>	
				</ul>
			</div>
		</div>
	</nav>	
	

	<h1>Sign Up:</h1>
	<div class="container">
			<form:form action="${contextPath}/addLibraryUser" method="post"
				 commandName="libraryUser"> 
				 <div class="form-group">
					<label class="control-label col-sm-2">First Name:</label>
						<div class="col-sm-10">
							<form:input type="text" path="firstName"/>
							<font color="red"><form:errors path="firstName"/></font><br/>
						</div>
				</div>
					
				<div class="form-group">
					<label class="control-label col-sm-2">Last Name:</label>
						<div class="col-sm-10">
							<form:input type="text" path="lastName"/>
							<font color="red"><form:errors path="lastName"/></font><br/>
						</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2">Email:</label>
						<div class="col-sm-10">
							<form:input type="text" path="email" />
							<font color="red"><form:errors path="email"/></font><br/>
						</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2">User Name:</label>
						<div class="col-sm-10">
							<form:input type="text" path="username" />
							<font color="red"><form:errors path="username"/></font><br/>
						</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2">Password:</label>
						<div class="col-sm-10">
							<form:input type="password" path="password" />
							<font color="red"><form:errors path="password"/></font><br/>
						</div>
				</div>	
				</br>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-secondary btn-lg" value="Sign Up" />
					</div>
				</div>	          
			</form:form>
    </div>
</body>
</html>