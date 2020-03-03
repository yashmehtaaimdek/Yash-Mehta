<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Form</title>
</head>
<body>
	<div align="center">
		<h1>Employee Management</h1>
		<h2>
			<a href="new">Add New Employee</a> &nbsp;&nbsp;&nbsp; 
			<a href="list">List All Employee</a>
		</h2>
		<h2>
			<c:if test="${employee != null}">
				Edit Employee
			</c:if>
			<c:if test="${employee == null}">
				Add New Employee
			</c:if>
		</h2>
	</div>
	<div align="center">
		<c:if test="${employee != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${employee == null}">
			<form action="insert" method="post">
		</c:if>
				<table border="1">
					<c:if test="${employee != null}">
						<input type="hidden" name="id"
							value="<c:out value='${employee.id}' />" />
					</c:if>
					<tr>
						<th>Name:</th>
						<td><input type="text" name="name" size="45"
							value="<c:out value='${employee.name}' />" />
						</td>
					</tr>
					<tr>
						<th>Email:</th>
						<td><input type="text" name="email" size="45"
							value="<c:out value='${employee.email}' />" />
						</td>
					</tr>
					<tr>
						<th>Address:</th>
						<td><input type="text" name="address" size="100"
							value="<c:out value='${employee.address}' />" />
						</td>
					</tr>
					<tr>
						<th>Phone:</th>
						<td><input type="text" name="phone" size="12"
							value="<c:out value='${employee.phone}' />" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Save" /></td>
					</tr>
				</table>
		</form>
	</div>
</body>
</html>