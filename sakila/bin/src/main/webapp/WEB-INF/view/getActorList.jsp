<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>getActorList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn').click(function(){
			$('#filmForm').submit();	
		});
	});
</script>
</head>
<body>
	<!-- mainMenu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<div class="container">
		<h1>getActorList</h1>
		<div>
			 <form id="filmForm" action="${pageContext.request.contextPath}/admin/getActorList" method="get">
			 	actor :
			 	<input name="searchWord" type="text" value="${searchWord}">
			 	<button id="btn" type="button">검색</button>
			</form>		 
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ActorId</th>
					<th>name</th>
					<th>FilmInfo</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="a" items="${actorList}">
					<tr>
						<td>${a.actorId}</td>
						<td>${a.name}</td>
						<td><a href="">${a.filmInfo}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	    <ul class="pager">
	        <c:if test="${currentPage > 1}">
	            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getActorList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
	        </c:if>
	        <c:if test="${currentPage < lastPage}">
	            <li class="next"><a href="${pageContext.request.contextPath}/admin/getActorList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
	        </c:if>
	    </ul>
	    <div>
	        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addActor?currentPage=${currentPage}&searchWord=${searchWord}">배우 추가</a>
	    </div>
    </div>
</body>
</html>