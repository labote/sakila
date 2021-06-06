<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mainMenu</title>
</head>
<body>
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath}/">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/getInventoryInfoList">InventoryList</a></li>
		</ul>
	</div>
</body>
</html>