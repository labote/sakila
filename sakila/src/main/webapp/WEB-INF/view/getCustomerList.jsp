<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>getCustomerList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btn').click(function() {
			$('#customerForm').submit();
		});
	});
</script>
</head>
<body>
	<!-- mainMenu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<div class="container">
		<h1>getCustomerList</h1>
		<div>

			<!-- 검색 폼내용 -->
			<!-- 
				1. 가게별
				2. 활성화
				3. 이름 검색
			 -->
			<form id="customerForm" action="${pageContext.request.contextPath}/admin/getCustomerList" method="get">
				 store : <select name="storeId">
				 			<option value="">가게 선택</option>
							<c:if test="${storeId == 1}">
								<option value="1" selected="selected">1</option>
							</c:if>
							<c:if test="${storeId != 1}">
								<option value="1">1</option>
							</c:if>
							<c:if test="${storeId == 2}">
								<option value="2" selected="selected">2</option>
							</c:if>
							<c:if test="${storeId != 2}">
								<option value="2">2</option>
							</c:if>
						</select>
				 active : <select name="active">
				 			<option value="">활성화 여부 선택</option>
							<c:if test="${active == 0}">
								<option value="0" selected="selected">0</option>
							</c:if>
							<c:if test="${active != 0}">
								<option value="0">0</option>
							</c:if>
							<c:if test="${active == 1}">
								<option value="1" selected="selected">1</option>
							</c:if>
							<c:if test="${active != 1}">
								<option value="1">1</option>
							</c:if>
						</select> 
				name : <input name="name" type="text" value="${name}">
				<button id="btn" type="button">검색</button>
			</form>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>customerId</th>
					<th>storeId</th>
					<th>name</th>
					<th>email</th>
					<th>active</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${customerList}">
					<tr>
						<td>${c.customerId}</td>
						<td>${c.storeId}</td>
						<td><a href="${pageContext.request.contextPath}/admin/getCustomerOne?customerId=${c.customerId}&currentPage=${currentPage}&name=${name}&active=${active}&storeId=${storeId}">${c.name}</a></td>
						<td>${c.email}</td>
						<td>${c.active}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<ul class="pager">
			<c:if test="${currentPage > 1}">
				<li class="previous"><a
					href="${pageContext.request.contextPath}/admin/getCustomerList?currentPage=${currentPage-1}&name=${name}&active=${active}&storeId=${storeId}">이전</a></li>
			</c:if>
			<c:if test="${currentPage < lastPage}">
				<li class="next"><a
					href="${pageContext.request.contextPath}/admin/getCustomerList?currentPage=${currentPage+1}&name=${name}&active=${active}&storeId=${storeId}">다음</a></li>
			</c:if>
		</ul>
		<div>
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/admin/addCustomer">고객 추가</a>
		</div>
	</div>
</body>
</html>