<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("document ready!");
		$('#btn').click(function(){
			console.log("btn click!");
			
			// 폼 유효성 검사
			$('#loginForm').submit();
		});
	});
</script>
</head>
<body>
	<h1>home</h1>
	<!-- 로그인 Off -->
	<c:if test="${loginStaff == null}">
		<form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
			<div>Email : </div>
			<div><input type="text" id="email" name="email"></div>
			<div>Password : </div>
			<div><input type="password" id="password" name="password"></div>
			<div>
				<button id="btn" type="button">login</button>
			</div>
		</form>
	</c:if>
	
	<!-- 로그인 On -->
	<c:if test="${loginStaff != null}">
		<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
		<a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
		<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
		<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
		<a href="${pageContext.request.contextPath}/admin/getInventoryInfoList">InventoryList</a>
		<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
		<a href="${pageContext.request.contextPath}/admin/logout">logout</a>
	</c:if>
</body>
</html>