<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AdminPage</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0-beta.2/angular.min.js"></script>
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

<script>
	function validate(){
		var book=document.forms["book"]["bookid"].value;
		if(book == ""){
				alert("Please enter correct book Id");
				return false;
			}	
		}
	
</script>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
		<div class="jumbotron" id="frame">
			<div id="textcenter">
				<h1>Library</h1>
				<p>Keep Calm And Read a Book!!</p>
			</div>
		</div>
	
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="${contextPath}/admin/addBooks">Add Books</a></li>
					<li><a href="${contextPath}/admin/viewRequests">View Book Requests</a></li>
					<li><a href="${contextPath}/admin/returnbook">Return a Book</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
							class="glyphicon glyphicon-user"></span> ${user.firstName}</a>
						<li><button class="btn btn-default"
							data-toggle="modal" data-target="#myModalHorizontal"><a href="${contextPath }/logout">Logout</button></a>
						</li>
					</li>
				</ul>	
			</div>			
		</div>	
	</nav>
		
			<c:if test="${not empty bookcirculationlist}">
			<h2>Books Issued</h2>
				<table border=2>
					<tr>
						<td><b>Circulation ID</b></td>
						<td><b>Book ID</b></td>
						<td><b>Book Title</b></td>
						<td><b>Issue Date</b></td>
					</tr>
					<c:forEach var="bcl" items="${bookcirculationlist}">
						<tr>
							<td>${bcl.circulationId}</td>
							<td>${bcl.book.bookId}</td>
							<td>${bcl.book.title}</td>
							<td>${bcl.issuedDate}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty bookcirculationlist}">
				<h3>No books in circulation</h3>
			</c:if>
		
		
		<form:form action="${contextPath}/admin/searchBookById" method="post" name="book" onsubmit="return validate()">
			<div ng-app>
				<h2>Search by Book Id:</h2>
			
				<label>Book Id:</label> <input type="text" id="bookid" name="bookid" ng-model="bookid"/><br>
			
					<tr>
					
						<td>Issued BookID: {{bookid}}</td>
					
					</tr>
			
				<br>
				<input type="submit" value="Search"/></br>
			</div>
		</form:form>
		
		<form:form action="${contextPath}/admin/report.pdf" method="post">
			
			<input type="submit" value="Generate Report"/>
		</form:form>
		
</body>
</html>