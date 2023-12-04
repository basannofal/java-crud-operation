<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>

	<header>

		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="htts://www.javaguides.net" class="navbar-brand">User
					Management Application</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>

		</nav>

	</header>
	<br>

	<div class=" container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>

				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<h2>
					<c:if test="${user != null}">       
                                   Edit User
                              </c:if>
					<c:if test="${user == null}">
                                   Add New User
                              </c:if>
				</h2>

					<c:if test="${user != null }">
						<input type="hidden" name="id" value="<c:out value='${user.id}'/>" />
					</c:if>

					<fieldset class="form-group">
						<label>Name</label> <input type="text"
							value="<c:out value='${user.name}'/>" class="form-control"
							name="name" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Email</label> <input type="text"
							value="<c:out value='${user.email}'/>" class="form-control"
							name="email">
					</fieldset>

					<fieldset class="form-group">
						<label>Contact</label> <input type="text"
							value="<c:out value='${user.number}'/>" class="form-control"
							name="number">
					</fieldset>

					<fieldset class="form-group">
						<label>City</label> <input type="text"
							value="<c:out value='${user.city}'/>" class="form-control"
							name="city">
					</fieldset>

					<button type="submit" class="btn btn-success">Submit</button>

				</form>
			</div>
		</div>
	</div>


</body>
</html>