<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>getInventoryInfoList</title>
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
			$('#InventoryForm').submit();	
		});
	});
</script>
</head>
<body>
	<!-- mainMenu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<div class="container">
		<h1>getInventoryInfoList</h1>
		<div>
			<form id="InventoryForm" action="${pageContext.request.contextPath}/admin/getInventoryInfoList" method="get">
				title : 
				<input name="searchWord" type="text" value="${searchWord}">
				<button id="btn" type="button">검색</button>
			</form>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>FilmId</th>
					<th>Title</th>
					<th>StoreId</th>
					<th>총량</th>
					<th>대여중</th>
					<th>대여가능</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" begin="${beginRow}" end="${inventoryList.size()-1}">
					<tr>
						<c:if test="${inventoryList[i].filmId==inventoryList[i-1].filmId}">
							<td>${inventoryList[i].storeId}</td>
							<td>${inventoryList[i].cnt}</td>
							<td>${inventoryList[i].cnt-filmInStock[i]}</td>
							<td>${filmInStock[i]}</td>
						</c:if>
					</tr>
					<tr>
						<c:if test="${inventoryList[i].filmId!=inventoryList[i-1].filmId}">
							<td rowspan="2">${inventoryList[i].filmId}</td>
							<td rowspan="2"><a href="${pageContext.request.contextPath}/admin/getInventoryOne?filmId=${inventoryList[i].filmId}&title=${inventoryList[i].title}&currentPage=${currentPage}&serachWord=${searchWord}">${inventoryList[i].title}</a></td>
							<td>${inventoryList[i].storeId}</td>
							<td>${inventoryList[i].cnt}</td>
							<td>${inventoryList[i].cnt-filmInStock[i]}</td>
							<td>${filmInStock[i]}</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	    <ul class="pager">
	        <c:if test="${currentPage > 1}">
	            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getInventoryInfoList?currentPage=${currentPage-1}&serachWord=${searchWord}">이전</a></li>
	        </c:if>
	        <c:if test="${currentPage < lastPage}">
	            <li class="next"><a href="${pageContext.request.contextPath}/admin/getInventoryInfoList?currentPage=${currentPage+1}&serachWord=${searchWord}">다음</a></li>
	        </c:if>
	    </ul>
	    <div>
	        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addInventory">인벤토리 추가</a>
	    </div>
    </div>
</body>
</html>