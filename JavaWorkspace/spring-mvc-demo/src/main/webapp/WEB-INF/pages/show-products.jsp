<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management System</title>
</head>
<body>
	<div>
		<h1>Product Management System</h1>
		<hr>
		<h2>List of all products</h2>
		
		<table border="1">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity per unit</th>
					<th>Unit price</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${products}" var="p">
				<tr>
					<td>${p.productName}</td>
					<td>${p.quantityPerUnit}</td>
					<td>${p.unitPrice}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
	</div>
</body>
</html>