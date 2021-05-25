<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getStaffOne</title>
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
    <h1>getStaffOne</h1>
    <table class="table table-striped">
        <tr>
        	<td>staffId</td>
            <td>${staffOne.staffId}</td>
        </tr>
        <tr>
        	<td>firstName</td>
            <td>${staffOne.firstName}</td>
        </tr>
        <tr>
        	<td>lastName</td>
            <td>${staffOne.lastName}</td>
        </tr>
        <tr>
        	<td>addressId</td>
            <td>${staffOne.addressId}</td>
        </tr>
        <tr>
        	<td>email</td>
            <td>${staffOne.email}</td>
        </tr>
        <tr>
        	<td>storeId</td>
            <td>${staffOne.storeId}</td>
        </tr>
        <tr>
        	<td>active</td>
            <td>${staffOne.active}</td>
        </tr>
         <tr>
        	<td>username</td>
            <td>${staffOne.username}</td>
        </tr>
         <tr>
        	<td>lastUpdate</td>
            <td>${staffOne.lastUpdate}</td>
        </tr>
    </table>
  	<div>
  		<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/updateStaff">직원 수정</a>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/deleteStaff">직원 삭제</a>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getStaffList">직원 리스트</a>
    </div>
</div>
</body>
</html>