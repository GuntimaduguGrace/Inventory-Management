
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">Goods List</h1>
<table border="1px">
		<thead>
		<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Desc</th>
		<th>Price</th>
		<th>Actions</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach  var="item" items="${goods}">
			<tr>
				<td>${item.id}</td>
				<td>${item.name }</td>
				<td>${item.desc }</td>
				<td>${item.price }</td>
				<td><a href="HandleOpp?id=${item.id}&action=edit">Edit</a> 
				<a href="HandleOpp?id=${item.id}&action=delete">Delete</a></td>
			</tr>
		</c:forEach>
		
		</tbody>
</table>
	
</body>
</html>
