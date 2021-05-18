<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>getBoardOne</title>
<!-- bootstrap�� ����ϱ� ���� CDN�ּ� -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("document ready!");
		$('#btn').click(function(){
			console.log("btn click!");
			
			// �� ��ȿ�� �˻�
			$('#modifyForm').submit();
		});
	});
</script>
</head>
<body>
<div class="container">
    <h1>Modify</h1>
    <form id="modifyForm" action="${pageContext.request.contextPath}/admin/modifyBoard" method="post">
	     <table class="table">
	         <tbody>
	            <tr>
	                <td>board_id :</td>
	                <td><input type="text" id="boardId" name="boardId" value="${modifyOne.boardId}" readonly="readonly" ></td>
	            </tr>
	            <tr>
	                   <td>board_pw :</td>
	                   <td><input type="password" id="boardPw" name="boardPw"></td>
	            </tr>
	            <tr>
	                   <td>board_title :</td>
	                   <td><input type="text" id="boardTitle" name="boardTitle" value="${modifyOne.boardTitle}"></td>
	            </tr>
	            <tr>
	                   <td>board_content :</td>
	                   <td><textarea rows="5" cols="80" id="boardContent" name="boardContent">${modifyOne.boardContent}</textarea></td>
	            </tr>
	            <tr>
	                   <td>user_name :</td>
	                   <td>${modifyOne.username}</td>
	            </tr>
	            <tr>
	                   <td>insert_date :</td>
	                   <td>${modifyOne.insertDate}</td>
	            </tr>
	        </tbody>
	    </table>
	    <button id="btn" type="button">����</button>
   	</form>
</div>
</body>
</html>