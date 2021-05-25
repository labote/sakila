<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>getFilmList</title>
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
		<h1>getFilmList</h1>
		<div>
			<!-- 검색 폼내용 -->
			<!-- 
				1. 카테고리별
				2. 가격별
				3. 등급별
				4. 제목,배우 검색
				5. 페이징
				6. 상세보기
			 -->
			 <form id="filmForm" action="${pageContext.request.contextPath}/admin/getFilmList" method="get">
			 	category :
			 	<select name="categoryName">
			 		<option value="">카테고리 선택</option>
				 	<c:forEach var="name" items="${categoryNameList}">
				 		<c:if test="${name == categoryName}">
				 			<option value="${name}" selected="selected">${name}</option>
				 		</c:if>
				 		<c:if test="${name != categoryName}">
				 			<option value="${name}">${name}</option>
				 		</c:if>
				 	</c:forEach>
			 	</select>
			 	
			 	rating :
			 	<select name="rating">
			 		<option value="">등급 선택</option>
				 	<c:forEach var="r" items="${ratingList}">
				 		<c:if test="${r == rating}">
				 			<option value="${r}" selected="selected">${r}</option>
				 		</c:if>
				 		<c:if test="${r != rating}">
				 			<option value="${r}">${r}</option>
				 		</c:if>
				 	</c:forEach>
			 	</select>
			 	
			 	price :
			 	<select name="price">
			 		<option value="0">가격 선택</option>
			 		<c:if test="${price == 0.99}">
			 			<option value="0.99" selected="selected">0.99</option>
			 		</c:if>
			 		<c:if test="${price != 0.99}">
			 			<option value="0.99">0.99</option>
			 		</c:if>
			 		<c:if test="${price == 2.99}">
			 			<option value="2.99" selected="selected">2.99</option>
			 		</c:if>
			 		<c:if test="${price != 2.99}">
			 			<option value="2.99">2.99</option>
			 		</c:if>
			 		<c:if test="${price == 4.99}">
			 			<option value="4.99" selected="selected">4.99</option>
			 		</c:if>
			 		<c:if test="${price != 4.99}">
			 			<option value="4.99">4.99</option>
			 		</c:if>
			 	</select>
			 	
			 	title :
			 	<input name="title" type="text" value="${title}">
			 	
			 	actor :
			 	<input name="actor" type="text" value="${actor}">
			 	<button id="btn" type="button">검색</button>
			</form>		 
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>FID</th>
					<th>title</th>
					<th>category</th>
					<th>price</th>
					<th>rating<th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="m" items="${filmList}">
					<tr>
						<td>${m.FID}</td>
						<td><a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${m.FID}&currentPage=${currentPage}&categoryName=${categoryName}&title=${title}&price=${price}&actor=${actor}&rating=${rating}">${m.title}</a></td>
						<td>${m.category}</td>
						<td>${m.price}</td>
						<td>${m.rating}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	    <ul class="pager">
	        <c:if test="${currentPage > 1}">
	            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&categoryName=${categoryName}&title=${title}&price=${price}&actor=${actor}&rating=${rating}">이전</a></li>
	        </c:if>
	        <c:if test="${currentPage < lastPage}">
	            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&categoryName=${categoryName}&title=${title}&price=${price}&actor=${actor}&rating=${rating}">다음</a></li>
	        </c:if>
	    </ul>
	    <div>
	        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addFilm">영화 추가</a>
	    </div>
    </div>
</body>
</html>