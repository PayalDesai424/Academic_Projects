<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View User</title>


<style>
a
{
color:white;
}
body {
  font-size: 12px;
  font-family: sans-serif;
  background: #2c3e50;
  align:center;
}
table
{
color:white;
}
nav {
  margin: 27px auto 0;

  position: relative;
  width: 220px;
  height: 50px;
  background-color: #34495e;
  border-radius: 8px;
  font-size: 0;
}
nav a {
  line-height: 50px;
  height: 100%;
  font-size: 15px;
  display: inline-block;
  position: relative;
  z-index: 1;
  text-decoration: none;
  text-transform: uppercase;
  text-align: center;
  color: white;
  cursor: pointer;
}
nav .animation {
  position: absolute;
  height: 100%;
  top: 0;
  z-index: 0;
  transition: all .5s ease 0s;
  border-radius: 8px;
}
a:nth-child(1) {
  width: 100px;
}
a:nth-child(2) {
  width: 110px;
}

nav .start-home, a:nth-child(1):hover~.animation {
  width: 100px;
  left: 0;
  background-color: #1abc9c;
}
nav .start-about, a:nth-child(2):hover~.animation {
  width: 110px;
  left: 100px;
  background-color: #e74c3c;
}



h1 {
  text-align: center;
  margin: 40px 0 40px;
  text-align: center;
  font-size: 30px;
  color: #ecf0f1;
  text-shadow: 2px 2px 4px #000000;
  font-family: 'Cherry Swash', cursive;
}

p {
    position: absolute;
    bottom: 20px;
    width: 100%;
    text-align: center;
    color: #ecf0f1;
    font-family: 'Cherry Swash',cursive;
    font-size: 16px;
}

span {
    color: #2BD6B4;
}

</style>

</head>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	
					
				
				
<div align="center">
		<h1>USERS</h1>
		<table border="2" >
			<tr>
				<th>Account no</th>
				<th>Username</th>
				<th>Account type</th>
				<th>Account Balance</th>
		
			</tr>
			<c:forEach var="login" items="${list}">
				<tr>
					<td>${login.accountno}</td>
					<td>${login.username}</td>
					<td>${login.accounttype}</td>
					<td>${login.accountbalance}</td>
					
				</tr>
			</c:forEach>
		</table>
			<a href="index.jsp">Logout</a>
			</div>
</body>
</html>