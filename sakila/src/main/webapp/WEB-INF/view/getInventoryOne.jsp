<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>inventoryOne</title>
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
		
		<h1>${title}(filmId = ${filmId})</h1>
		
		
		<h3>store1</h3>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>inventoryId</th>
					<th>대여</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" items="${inventoryOne}">
					<c:if test="${i.storeId == store1}">
						<tr>
							<td>${i.inventoryId}</td>
							<td>
								<c:if test="${i.rentalDate == null || i.returnDate != null}">대여 가능</c:if>
								<c:if test="${i.returnDate == null}">대여 중</c:if>
							</td>
							<td>
								<c:if test="${i.rentalDate == null || i.returnDate != null}">
									<a href="${pageContext.request.contextPath}/admin/removeInventory?inventoryId=${i.inventoryId}&title=${title}&filmId=${filmId}&currentPage=${currentPage}&serachWord=${searchWord}">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td><a href="${pageContext.request.contextPath}/admin/addInventory?storeId=${store1}&title=${title}&filmId=${filmId}&currentPage=${currentPage}&serachWord=${searchWord}"><button>인벤토리 추가</button></a></td>
				</tr>
			</tfoot>
		</table>
		
		<h3>store2</h3>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>inventoryId</th>
					<th>대여</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" items="${inventoryOne}">
					<c:if test="${i.storeId == store2}">
						<tr>
							<td>${i.inventoryId}</td>
							<td>
								<c:if test="${i.rentalDate == null || i.returnDate != null}">대여 가능</c:if>
								<c:if test="${i.renetalDate != null && i.returnDate == null}">대여 중</c:if>
							</td>
							<td>
								<c:if test="${i.rentalDate == null || i.returnDate != null}">
									<a href="${pageContext.request.contextPath}/admin/removeInventory?inventoryId=${i.inventoryId}&title=${title}&filmId=${filmId}&currentPage=${currentPage}&serachWord=${searchWord}">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td><a href="${pageContext.request.contextPath}/admin/addInventory?storeId=${store2}&title=${title}&filmId=${filmId}&currentPage=${currentPage}&serachWord=${searchWord}"><button>인벤토리 추가</button></a></td>
				</tr>
			</tfoot>
		</table>
    </div>
</body>
</html>