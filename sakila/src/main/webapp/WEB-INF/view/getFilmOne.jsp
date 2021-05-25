<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getFilmOne</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
<!-- mainMenu -->
<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
<div class="container">
    <h1>getFilmOne</h1>
    <table class="table table-striped">
    	<tr>
    		<td>filmId</td>
    		<td>${film.filmId}</td>
    	</tr>
    	<tr>
    		<td>title</td>
    		<td>${film.title}</td>
    	</tr>
    	<tr>
    		<td>description</td>
    		<td>${film.description}</td>
    	</tr>
    	<tr>
    		<td>releaseYear</td>
    		<td>${film.releaseYear}</td>
    	</tr>
    	<tr>
    		<td>languageId</td>
    		<td>${film.languageId}</td>
    	</tr>
    	<tr>
    		<td>originalLanguageId</td>
    		<td>${film.originalLanguageId}</td>
    	</tr>
    	<tr>
    		<td>rentalDuration</td>
    		<td>${film.rentalDuration}</td>
    	</tr>
    	<tr>
    		<td>rentalRate</td>
    		<td>${film.rentalRate}</td>
    	</tr>
    	<tr>
    		<td>length</td>
    		<td>${film.length}</td>
    	</tr>
    	<tr>
    		<td>replacementCost</td>
    		<td>${film.replacementCost}</td>
    	</tr>
    	<tr>
    		<td>rating</td>
    		<td>${film.rating}</td>
    	</tr>
    	<tr>
    		<td>specialFeatures</td>
    		<td>${film.specialFeatures}</td>
    	</tr>
    	<tr>
    		<td>lastUpdate</td>
    		<td>${film.lastUpdate}</td>
    	</tr>
    	<tr>
    		<td>Store1</td>
    		<td>${filmCount}</td>
    	</tr>
    	<tr>
    		<td>Store2</td>
    		<td>${filmCount2}</td>
    	</tr>
    </table>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getFilmList?&currentPage=${currentPage}&categoryName=${categoryName}&title=${title}&price=${price}&actor=${actor}&rating=${rating}">홈</a>
    </div>
</div>
</body>
</html>