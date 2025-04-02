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
	
	<h1>
	<c:choose>
		<c:when test="${empty goods }">Add</c:when>
		<c:otherwise> Update</c:otherwise>
	</c:choose> 
	 Inventory</h1>
	<form action="HandleAdd" method="post">
		<c:if test="${not empty goods }">
			<input type="hidden" name="id" value="${goods.id}"/>
		</c:if>
		<label>Item Name:</label><input type="text" name="itemname"  value="${goods.name}"/><br/>
		<label>Item Desc:</label><input type="text" name="itemdesc"  value="${goods.desc}"/><br/>
		<label>Item Price:</label><input type="number" name="itemprice"  value="${goods.price}"/><br/>
		
		<c:choose>
		<c:when test="${empty goods }"><input type="submit" value="Add item"/> </c:when>
		<c:otherwise> <input type="submit" value="Update item"/></c:otherwise>
	</c:choose> 
	</form>
	
	
</body>
</html>
