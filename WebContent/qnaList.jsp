<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Sublime project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/cart.css">
<link rel="stylesheet" type="text/css" href="styles/cart_responsive.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src=js/jquery-3.2.1.min.js></script>
  
<body>

<div class="super_container">

  <jsp:include page="header.jsp" />
  
   
   <!-- Home -->

	<div class="home">
		<div class="home_container" >
			<div class="home_background" style="background-image:url(images/cart.jpg)"></div>
			<div class="home_content_container">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="home_content">
								<div class="breadcrumbs">
									<ul>
										<li><a href="index.html">Home</a></li>
										<li>Q&A</li>
										
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<!-- ------------------------------------------------------------------- -->
<!-- 여기서부터 -->
<!-- ------------------------------------------------------------------- -->
<!-- 여기서부터 -->
<!-- ------------------------------------------------------------------- -->
<!-- 여기서부터 -->

  
  <!-- Bootstrap -->
  <link href="css/css/bootstrap.min.css" rel="stylesheet">
  <style>
    #container {
      width: 70%;
      margin: 0 auto;     /* 가로로 중앙에 배치 */
      padding-top: 2%;   /* 테두리와 내용 사이의 패딩 여백 */
    }
     
    #list {
      text-align: center;
    }
   
    #write {
      text-align: right;
    }
     
    /* Bootstrap 수정 */
    .table > thead {
      background-color: #b3c6ff;
    }
    .table > thead > tr > th {
      text-align: center;
    }
    .table-hover > tbody > tr:hover {
      background-color: #e6ecff;
    }
    .table > tbody > tr > td {
      text-align: center;
    }
    .table > tbody > tr > #title {
      text-align: left;
    }
     
    div > #paging {
      text-align: center;
    }
     
    .hit {
      animation-name: blink;
      animation-duration: 1.5s;
      animation-timing-function: ease;
      animation-iteration-count: infinite;
      /* 위 속성들을 한 줄로 표기하기 */
      /* -webkit-animation: blink 1.5s ease infinite; */
    }
     
    /* 애니메이션 지점 설정하기 */
    /* 익스플로러 10 이상, 최신 모던 브라우저에서 지원 */
    @keyframes blink {
      from {color: white;}
      30% {color: yellow;}
      to {color: red; font-weight: bold;}
      /* 0% {color:white;}
      30% {color: yellow;}
      100% {color:red; font-weight: bold;} */
    }
  </style>
</head>
<body>
  <div id="container">
    <div align="right">
      <!-- Login 검증 -->
      <!-- jstl의 if문은 else가 없어서 따로 검증해야함. -->
      <c:if test="${id != null}">
        <%-- < %@include file="loginOk.jsp" %> --%>
      </c:if>
      <c:if test="${id == null}">
        <%-- <%@include file="login.jsp" %> --%>
      </c:if>
    </div>
   
    <div id="list">
      <b>게시판 (전체 글 ${totalCount})</b>
    </div>
    
    
    
                <h6>1:1 고객 게시판</h2>	
                <h8>고객님의 질문에 대해서 운영자가  1:1 답변을 드립니다.</h3>
    
     <div align=right>
     <form id="qnaWrite" action="note?command=proSortAll" method="post">
       <a href="qnaWrite.jsp">글쓰기</a>
    </form>
    </div>
     
     
     
     
     
     <!-- 
     //글조회
function fn_view(code){
    
    var form = document.getElementById("boardForm");
    var url = "<c:url value='/board/viewContent.do'/>";
    url = url + "?code=" + code;
    
    form.action = url;    
    form.submit(); 
}
      -->
      
      
    <div>
      <table class="table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th width="15%">답변상태</th>
            <th width="10%">번호</th>
            <th width="25%">제목</th>
            <th width="10%">작성자</th>
            <th width="15%">작성일</th>   
            <th width="15%">상품번호</th>         
            <th width="20%">조회</th>
          </tr>
        </thead>
        <tbody>
     	
        
          <c:forEach var="qna" items="${list}" varStatus="status">
              <form id="detail${status.count}" action="note?command=qnaDetail" method="post">
            <tr>
              <input type="hidden" id="pwd${status.count }" name="password" value="${qna.password }"> 
              <input type="hidden" id="qnaNo" name="qnaNo" value="${qna.qnaNo}"> 
              <td >${qna.answerState}</td>
              <td  value="${qna.qnaNo}">${qna.qnaNo}</td>
              <td id="subject"><a href="#" onclick="checkPwd(${status.count});">${qna.subject}</a></td>
              <td >${qna.userId}</td>             
              <td>${qna.createDate}</td>
              <td >${qna.product.serialNum}</td>
              <td >${qna.viewCnt}</td> 
               
               </tr>

            <tr>

<%--            <input type="hidden" name="qnaNo" value="${qna.qnaNo}"> --%>
<%--             <input type="hidden" name="password" value="${qna.password }"> --%>
<%--             <input type="hidden" name="createDate" value="${qna.createDate }"> --%>
<%--             <input type="hidden" name="userId" value="${qna.userId }"> --%>
            <input type="hidden" name="serialNum" id="serialNum" value="${qna.product.serialNum }">
            
<%--             <input type="hidden" name="subject" value="${qna.subject }"> --%>
<%--             <input type="hidden" name="content" value="${qna.content }"> --%>
        
			 </form> 
                
          </c:forEach>
        
          
        </tbody>
      </table>
      
<!-- <nav aria-label="..." style="text-align: center;"> -->
<!--  		 <ul class="pagination"> -->
<!--              <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li> -->
<!-- 	         <li class="page-item active"><a class="page-link" href="#">1</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">2</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">3</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">4</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">5</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">6</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">7</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">8</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">9</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">10</a></li> -->
<!-- 	         <li class="page-item"><a class="page-link" href="#">Next</a></li> 		  -->
<!--    		 </ul> -->
<!-- 	</nav> -->
 	
  
   
    </div>
  </div>
  
 <br>
 <br>
 <br>
 <br>
 <!--  -->
 <% String myId =(String)session.getAttribute("userId"); %>
 
 
 <!-- Footer -->
	
<jsp:include page="footer.jsp"/>

</div>

    <script>
          

 			function checkPwd(count){
 			var pass = $("#pwd"+count).val();
 			alert(pass);
 				
 				var pwd = prompt("비밀번호를 입력하세요.","비밀번호 ");
 				if(pwd != pass ){
 					alert("비밀번호가 잘못되었습니다.");
 					return;
 				}else{
 					document.getElementById('detail'+count).submit();
 				}
 			}
 	 
          </script>

<script>



// function do(){
// 	//var x = document.forms["detail"];
// 	//alert("value"+ x.elements["#pwd"].value);
// 	$("form[id=detail]").submit();	
// }

	
	
<%-- 	var myId = <%=myId%>; --%>
// 	console.log(myId);
// 	alert(myId);
// 	$("#subject").click(function(){
// 		alert(myId);
// 	})
// })

// $(document).ready(function(){
// 	  $("a").click(function(){
// 	    alert("Value: " + document.getElementById("qnaNo").value );
// 	  });
// 	});
	
	 $(document).ready(function(){

			function checkPwd(){
			var pass = $("#pwd").val();
				
				var pwd = prompt("비밀번호를 입력하세요.","비밀번호 ");
				if(pwd != pass ){
					alert("비밀번호가 잘못되었습니다.");
					return;
				}else{
					document.getElementById('detail${status.count}').submit();
				}
			}
	 }

	
		$("td[id=subject]").click(function(){
		var password = $('input[type=hidden][name=password]').val();
		alert(password);
		var pwd = prompt("비밀번호를 입력하세요.","비밀번호 ");
		if(pwd === password ){
			document.getElementById('detail${status.count}').submit();
		}
		else{
			alert("비밀번호가 잘못되었습니다.");
		}
	});
		
		



</script>


</body>
</html>