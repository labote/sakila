<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
 
<script>
    $(document).ready(function() {
        $('#addButton').click(function() {
        	
        	// 파일들 중 하나라도 첨부되지 않으면 ck = true;
        	let ck = false;
        	let boardfile = $('.boardfile'); // 배열
        	
        	for(let item of boardfile){ // break키워드를 사용하기 위해 for반복문 사용 <-- boardfile.each()메서드에서 사용 불가능
        		if($(item).val() == ''){
        			ck = true;
        			console.log('첨부되지 않은 파일이 있습니다.');
        			break;
        		}
        	}
/*         	$('.boardfile').each(function(index,item){
        		// item -> <input type="file" name="boardfile" class="boardfile">을 나타냄
        		if($(item).val() == '') { // $ 붙이면 jquery 사용가능, $(<input type="file" name="boardfile" class="boardfile">)
        			alert('첨부되지 않은 파일이 있습니다.');
        			ck = true;
        		}
        	}); */
        	if(ck){
        		alert('첨부되지 않은 파일이 있습니다.');
        	} else if ($('#boardPw').val().length < 4) {
                alert('boardPw는 4자이상 이어야 합니다');
                $('#boardPw').focus();
            } else if ($('#boardTitle').val() == '') {
                alert('boardTitle을 입력하세요');
                $('#boardTitle').focus();
            } else if ($('#boardContent').val() == '') {
                alert('boardContent을 입력하세요');
                $('#boardContent').focus();
            } else if ($('#staffId').val() == '') {
                alert('staffId을 입력하세요');
                $('#staffId').focus();
            } else {
                $('#addForm').submit();
            }
        });
        
        // inputFile에 input type="file" 마지막에 추가
        $('#addFileBtn').click(function(){
        	console.log('#addFileBtn click!');
        	$('#inputFile').append('<input type="file" name="boardfile" class="boardfile">') // 태그가 누적되도록 append, append 쓰면 마지막에 추가
        });
        
     	// inputFile에 input type="file" 삭제
        $('#delFileBtn').click(function(){
        	console.log('#delFileBtn click!');
        	$('#inputFile').children().last().remove();
        });
    });
</script>
<title>addBoard</title>
</head>
<body>
    <div class="container">
        <h1>addBoard</h1>
        <form id="addForm" action="${pageContext.request.contextPath}/admin/addBoard" method="post" enctype="multipart/form-data"> <!-- enctype 변경해줘야 파일을 넘길 수 있다 -->
        	<div>
        		<div>
        			<button id="addFileBtn" type="button">파일 추가</button>
        			<button id="delFileBtn" type="button">파일 삭제</button>
        		</div>
        		<div>
        			<div id="inputFile">
        			</div>
        		</div>
        	</div>
            <div class="form-group">
                <label for="boardPw">boardPw :</label> <input class="form-control"
                    name="board.boardPw" id="boardPw" type="password" />
            </div>
            <div class="form-group">
                <label for="boardPw">boardTitle :</label> <input
                    class="form-control" name="board.boardTitle" id="boardTitle" type="text" />
            </div>
            <div class="form-group">
                <label for="boardContent">boardContent :</label>
                <textarea class="form-control" name="board.boardContent" id="boardContent"
                    rows="5" cols="50"></textarea>
            </div>
            <div class="form-group">
                <label for="staffId">staffId :</label> <input
                    class="form-control" name="board.staffId" id="staffId" type="text" />
            </div>
            <div>
                <input class="btn btn-default" id="addButton" type="button"
                    value="글입력" /> <input class="btn btn-default" type="reset"
                    value="초기화" /> <a class="btn btn-default"
                    href="${pageContext.request.contextPath}/admin/getBoardList">글목록</a>
            </div>
        </form>
    </div>
</body>
</html>