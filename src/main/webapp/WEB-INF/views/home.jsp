<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
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
		background-color: #f2f2f2f2;
		padding: 25px;
	}

	#frame {
		border: 1px solid grey;
		background-size: 100%;
		background-image: url(<c:url value="/resources/background.jpg"/>);
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
</body>
</html>
