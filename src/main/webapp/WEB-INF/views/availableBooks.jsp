<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home</title>
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

<script>
	function validate(){
		var requestDate=document.forms["book1"]["request_Date"].value;
		var tillDate=document.forms["book1"]["till_Date"].value;
		var bookId=document.forms["book1"]["bookId"].value;
		if(requestDate == ""){
				alert("Please enter From Date");
				return false;
			}
		if(tillDate == ""){
			alert("Please enter End Date");
			return false;
		}
		if(bookId == ""){
			alert("Please select from available books");
			return false;
		}			
		}
	
</script>

	<c:set var = "contextPath" value="${pageContext.request.contextPath }"/>
	
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
					<li><a href="${contextPath}/user/addBookRequest">Request a book</a></li>
					<li><a href="${contextPath}/user/viewRaisedRequests">View Raised Requests</a></li>
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
	
	<div class="col-sm-7">
			<h3>Available Book</h3>
			</br>
			<div>
				<c:forEach var="book" items="${availableBooks}">
					<div class="col-md-3">
						
							<span>${book.title}</span>
						
						<small>by ${book.author}</small>

					</div>
				</c:forEach>
			</div>
	</div>	
	
	</br></br>
	
	<div class="col-sm-7">	
			<h3>Create a book issue request:<b></h3>
				<form:form action="${contextPath}/user/addBookRequest"
					method="post" commandName="reserveBook" name="book1" onsubmit="return validate()">
					
					<div class="form-group">
						<label class="col-sm-7">From Date:</label>
							<div class="col-sm-10">
								<input type="date" name="request_Date"/><br/>
								<font color="red"><form:errors path="requestDate"/></font><br/>
							</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-7">End Date:</label>
							<div class="col-sm-10">
								<input type="date" name="till_Date"/><br/>
								<font color="red"><form:errors path="tillDate"></form:errors></font><br/>
							</div>
					</div>
					 
					<div class="form-group">
						<label class="col-sm-7">Available Books:</label>
							<c:forEach var="ab" items="${availableBooks}">
								<div class="col-sm-7">
										<input name="bookId" type="radio" value="${ab.bookId}" >${ab.title}</input><br/>
								</div>
							</c:forEach>		
					</div>		
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-secondary btn-lg" value="Submit" />
						</div>
					</div>	 				 
							 					
				</form:form>
</div>
</body>
</html>