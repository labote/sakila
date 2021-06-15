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
<script>
	$(document).ready(function(){
		$('#btn').click(function(){
			$('#customerForm').submit();	
		});
	});
</script>
<title>addCustomer</title>
</head>
<body>
    <div class="container">
        <h1>addCustomer</h1>
        <form id="customerForm" action="${pageContext.request.contextPath}/admin/addCustomer" method="post">
        	<div>
        		<label for="storeId :">storeId :</label>
        		<select id="storeId">
        			<option value="1">1</option>
        			<option value="2">2</option>
        		</select>
        	</div>
            <div class="form-group">
                <label for="firstName">firstName :</label> <input
                    class="form-control" name="firstName" id="firstName" type="text" />
            </div>
            <div class="form-group">
                <label for="lastName">lastName :</label> <input
                    class="form-control" name="lastName" id="lastName" type="text" />
            </div>
            <div class="form-group">
                <label for="filmInfo">email :</label> <input
                    class="form-control" name="email" id="email" type="text" />
            </div>
            <div class="form-group">
                <label for="filmInfo">addressId :</label> <input
                    class="form-control" name="addressId :" id="addressId :" type="text" />
            </div>
            <div>
                <input class="btn btn-default" id="btn" type="button" value="고객입력" />
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getCustomerList">고객 목록</a>
            </div>
        </form>
    </div>
</body>
</html>