<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
</head>
<body>
	<div align="center">
		<h1>Employee Management</h1>
		<h2>
			<a href="new">Add New Employee</a> &nbsp;&nbsp;&nbsp; 
			<a href="list">List All Employee</a>
		</h2>
		<h2>List of Employees</h2>
	</div>
	
	<div align="center">
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="employee" items="${listEmployee}">
				<tr>
					<td><c:out value="${employee.id}" /></td>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.email}" /></td>
					<td><c:out value="${employee.address}" /></td>
					<td><c:out value="${employee.phone}" /></td>
					<td><a href="edit?id=<c:out value='${employee.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?id=<c:out value='${employee.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>