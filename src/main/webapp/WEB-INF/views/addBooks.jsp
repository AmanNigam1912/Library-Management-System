<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page: Add Books</title>
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
<!-- <script>
	function validate(){
		var bookGenre=document.forms["book"]["selectGenre"].value;
		var bookAvailability=document.forms["book"]["selectAvailability"].value;
		if(bookGenre == ""){
				alert("Please select value from Genre list");
				return false;
			}	
		if(bookAvailability == ""){
			alert("Please select value from Availability list");
			return false;
			}
		}	
</script> -->

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
	
	<h1>Add book:</h1>
	<div class="container">
		<form:form action="${contextPath}/admin/addBooks" method="post"
			commandName="book" enctype="multipart/form-data" >
			<!-- name="book" onsubmit="return validate()">  -->
			
			<div class="form-group">
				<label class="control-label col-sm-2">Title:</label>
					<div class="col-sm-10">
						<form:input type="text" path="title"/><br>
						<font color="red"><form:errors path="title"/></font><br/>
					</div>
			</div>
				
			<div class="form-group">
				<label class="control-label col-sm-2">Author:</label>
					<div class="col-sm-10">
						<form:input type="text" path="author"/><br>
						<font color="red"><form:errors path="author"/></font><br/>
					</div>
			</div>	
			
			<div class="form-group">
				<label class="control-label col-sm-2">Genre: </label>
					<div class="col-sm-10">
						<form:select name= "selectGenre" path="genre">
						<option></option>
						<option value="Suspense">Suspense</option>
						<option value="Thriller">Thriller</option> 
						<option value="Horror">Horror</option> 
						<option value="Drama">Drama</option> 
						<option value="Comedy">Comedy</option> 
						</form:select><br>
						<font color="red"><form:errors path="genre"/></font><br/>
					</div>
			</div>
				
			<div class="form-group">
				<label class="control-label col-sm-2">Language: </label>
					<div class="col-sm-10">
						<form:input type="text" path="language" /><br>
						<font color="red"><form:errors path="language"/></font><br/>
					</div>
			</div>	
				 
			<div class="form-group">
				<label class="control-label col-sm-2">ISBN: </label>
					<div class="col-sm-10">
						<form:input type="text" path="isbn" /><br>
						<font color="red"><form:errors path="isbn"/></font><br/>
					</div>
			</div>
				 
			<div class="form-group">
				<label class="control-label col-sm-2">Availability: </label>
					<div class="col-sm-10">
						<form:select name= "selectAvailability" path="availability">
						<option></option>
						<option value="Yes">Yes</option>
						<option value="No">No</option> 
						</form:select><br>
						<font color="red"><form:errors path="availability"/></font><br/>
					</div>
			</div>
				 		
            <div class="form-group">
				<label class="control-label col-sm-2">Upload Book coverpic:</label>
					<div class="col-sm-10">
						<input type="file" name="photo" /><br/>
					</div>
			</div>	
					
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-secondary btn-lg" value="Add" />
				</div>
			</div>	 
                     
		</form:form>
	</div>	
</body>
</html>