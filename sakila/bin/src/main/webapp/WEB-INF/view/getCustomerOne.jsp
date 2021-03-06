<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getCustomerOne</title>
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
    <h1>getCustomerOne</h1>
    <table class="table table-striped">
    	<tr>
    		<td>customerId</td>
    		<td>${getCustomerOne.customerId}</td>
    	</tr>
    	<tr>
    		<td>name</td>
    		<td>${getCustomerOne.name}</td>
    	</tr>
    	<tr>
    		<td>address</td>
    		<td>${getCustomerOne.address}</td>
    	</tr>
    	<tr>
    		<td>zipCode</td>
    		<td>${getCustomerOne.zipCode}</td>
    	</tr>
    	<tr>
    		<td>phone</td>
    		<td>${getCustomerOne.phone}</td>
    	</tr>
    	<tr>
    		<td>city</td>
    		<td>${getCustomerOne.city}</td>
    	</tr>
    	<tr>
    		<td>country</td>
    		<td>${getCustomerOne.country}</td>
    	</tr>
    	<tr>
    		<td>SID</td>
    		<td>${getCustomerOne.SID}</td>
    	</tr>
    	<tr>
    		<td>amount</td>
    		<td>${sum}$</td>
    	</tr>
    </table>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getCustomerList?&currentPage=${currentPage}&name=${name}&active=${active}&storeId=${storeId}">CustomerList</a>
    </div>
    
    <h3>RentalList</h3>
    <table class="table table-striped">
    	<tr>
    		<td>title</td>
    		<td>inventoryId</td>
    		<td>rentalDate</td>
    		<td>returnDate</td>
    		<td>staffId</td>
    		<td>반납</td>
    	</tr>
    	<c:forEach var="r" items="${rentalList}">
    		<tr>
    			<td>${r.title}</td>
    			<td>${r.inventoryId}</td>
    			<td>${r.rentalDate}</td>
    			<td>${r.returnDate}</td>
    			<td>${r.staffId}</td>
    			<td>
    				<c:if test="${r.returnDate == null}">
    					<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/modifyCustomer?inventoryId=${r.inventoryId}">반납</a>
    				</c:if>
    				<c:if test="${r.returnDate != null}">
    					반납완료
    				</c:if>
    			</td>
    		</tr>
    	</c:forEach>
    </table>
</div>
</body>
</html>