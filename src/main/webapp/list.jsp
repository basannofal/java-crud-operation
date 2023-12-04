<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management Application</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
           crossorigin="anonymous">
</head>
<body>

 <header>
          
          <nav class="navbar navbar-expand-md navbar-dark"
               style="background-color: tomato">
               <div>
                    <a href="htts://www.javaguides.net" class="navbar-brand">User management</a>
               </div>
               
               <ul class= "navbar-nav">
                   <li><a href="<%=request.getContextPath() %>/list"
                   class="nav-link">Users</a></li>
               </ul>
          
          </nav>
          
    </header>
    <br>
    
     <div class="row">
     
           <div class="container">
           
                 <h3 class="text-center">List of users</h3>
                 <hr>
                 
                 <div class="container text-left">
                 
                       <a href="<%=request.getContextPath() %>/new" class="btn btn-success">Add New User</a>
                 
                 </div>
                 <br>
                 
                 <table class="table table-bordered">
                 
                       <thead>
                             <tr>
                                  <th>ID</th>
                                  <th>NAME</th>
                                  <th>EMAIL</th>
                                  <th>CONTACT</th>
                                  <th>CITY</th>
                                  <th>ACTION</th>
                             </tr>
                        </thead>
                        <tbody>
                        
                               <c:forEach var="user"  items="${users}">
                               
                               <tr>
                                   <td><c:out value = "${user.id}"/></td>
                                   <td><c:out value="${user.name}"/></td>
                                   <td><c:out value="${user.email}"/></td>
                                   <td><c:out value="${user.number}"/></td>
                                   <td><c:out value="${user.city}"/></td>
                                   <td><a href="edit?id=<c:out value='${user.id}'/>">Edit</a>
                                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       <a href="delete?id=<c:out value='${user.id}'/>">Delete</a>
                                   </td>
                               </tr>
                               </c:forEach>
                        
                        </tbody>
                 
                 </table>
           
           </div>
     
     </div>

</body>
</html>