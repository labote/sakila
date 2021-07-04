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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("시작");
		$.ajax({
			type: 'get',
			url: '/filmCategory',
			success:function(json){
				console.log("json 확인");
				$('#category').append('<option value="">category</option>');
				$(json).each(function(index, item){
					$('#category').append('<option value="'+item.name+'">'+item.name+"</option>");
				});
			}
		});
		
		$('#category').change(function(){
			if($('#category').val() != ''){
				console.log("category not null");
				$.ajax({
					type: 'get',
					url: '/filmTitle',
					data: {
						categoryName : $('#category').val()
					},
					success:function(json){
						console.log("json 확인");
						$('#film').empty();
						$('#film').append('<option value="">Title</option>');
						$(json).each(function(index, item){
							$('#film').append('<option value="'+item.filmId+'">'+item.title+"</option>");
						});
					}
				});
			}
		});
		
		$('#film').change(function(){
			if($('#film').val() != ''){
				console.log("film not null");
				$.ajax({
					type: 'get',
					url: '/inventory',
					data: {
						filmId : $('#film').val()
					},
					success:function(json){
						console.log("josn 확인");
						$('#inventory').empty();
						$('#inventory').append('<option value="">inventory</option>');
						$(json).each(function(index, item){
							$('#inventory').append('<option value="'+item.inventoryId+'">'+item.inventoryId+"</option>");
						});
					}
				});
			}
		});
		
		$('#rentalBtn').click(function(){
			console.log("click 확인");
			$('#rentalForm').submit();
		});
	});
</script>
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
    
    <br>
    
    <div>
    	<form id="rentalForm" action="${pageContext.request.contextPath}/admin/addRental" method="post">
    		<input type="hidden" name="currentPage" value="${currentPage}">
    		<input type="hidden" name="name" value="${name}">
    		<input type="hidden" name="active" value="${active}">
    		<input type="hidden" name="sum" value="${sum}">
    		<input type="hidden" name="storeId" value="${storeId}">
    		<input type="hidden" name="customerId" value="${getCustomerOne.customerId}">
    		<input type="hidden" name="sum" value="${sum}">
    		
    		<table class="table table-striped">
    			<tr>
    				<td>Category</td>
    				<td>Film</td>
    				<td>Inventory</td>
    				<td>대여</td>
    			</tr>
    			<tr>
    				<td><select name="categoryName" id="category"></select></td>
    				<td><select name="filmId" id="film"><option value="">Title</option></select></td>
    				<td><select name="inventoryId" id="inventory"><option value="">inventory</option></select></td>
    				<td><button type="button" id="rentalBtn">대여</button></td>
    			</tr>
    		</table>
    	</form>
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