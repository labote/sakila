<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<title>getActorListByFilm</title>
</head>
<body>
	<h1>영화(filmId : ${filmId}) 출연배우 수정</h1>
	<form action="${pageContext.request.contextPath}/admin/modifyFilmActor" method="post">
		<input type="hidden" name="filmId" value="${filmId}">
		<div>
			<c:forEach var="m" items="${filmActorList}">
			<c:if test="${m.filmId == null}">
				<input type="checkbox" name="ck" value="${m.actorId}">
			</c:if>
			<c:if test="${m.filmId != null}">
				<input type="checkbox" name="ck" checked="checked" value="${m.actorId}">
			</c:if>
				<span style="color:red;">${m.name.substring(0,1)}</span>${m.name.substring(1)}&nbsp;
			</c:forEach>
		</div>
		<div>
			<button type="submit">출연배우 추가</button>
		</div>
	</form>
</body>
</html>