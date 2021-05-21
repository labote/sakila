<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBoardfile</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("ready!");
		$('#btn').click(function(){
			console.log("click!");
			$('#addForm').submit();
		});
	});
</script>
</head>
<body>
	<h1>파일 추가</h1>
	<form id="addForm" action="${pageContext.request.contextPath}/admin/addBoardfile" method="post" enctype="multipart/form-data">
		<div>
			boardId : 
			<input type="text" id="boardId" name="boardId" value="${boardId}" readonly="readonly">
		</div>
		<div>
			<input type="file" id="multipartFile" name="multipartFile">
		</div>
		<div>
			<button id="btn" type="button">추가</button>
		</div>
	</form>
</body>
</html>