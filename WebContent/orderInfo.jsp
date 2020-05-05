<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Cart</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Sublime project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/cart.css">
<link rel="stylesheet" type="text/css" href="styles/cart_responsive.css">
<link rel="stylesheet" type="text/css" href="styles/cart_info.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/main.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/cart.js"></script>

</head>

<style>
 
 #test_btn{ border-top-right-radius: 100px; border-bottom-right-radius: 100px; margin-left:30px;margin-top:5px;
  background-color: #5D5D5D;
 } 
 #btn_group button{ border: 1px solid skyblue; background-color: rgba(0,0,0,0); color: skyblue; padding: 5px; }
#aco{
background-color: #EAEAEA ;
font-size: 15px;

.cart_info_col
{
   float: left;
   font-size: 14px;
   font-weight: 400;
   color: #1b1b1b;
}
.cart_info_col_my
{
   width:50%;
}

}
   </style>



<body>
<%@include file="myHeader.jsp" %>
<div class="super_container">

   
   <!-- Menu -->


   <br><br><br><br>
   <!-- Cart Info -->

<section id="one" class="wrapper style1" style="z-index:2;">
   
   <div class="cart_info">
   <h3 align="center">주문내역보기</h3>
      <div class="container">
         <div class="row">
            <div class="col">
               <!-- Column Titles -->

				<table>
					<tr>
					<th>주문번호</th>
					<th>유저아이디</th>
					<th>구매일</th>
					<th>배송상태</th>
					<th>환불요청</th>
					<th>배송지</th>
					<th>총금액</th>
					<th>주문취소(환불)</th>
					</tr>
					
					        	 <c:forEach var="orderInfo" items="${requestScope.list}">
        	 <tr>
        	 <td><button type="button" name="orderDetail" value="${orderInfo.orderNo}">${orderInfo.orderNo}</button></td>
        	 <td><c:out value="${orderInfo.userId}"></c:out></td>
        	 <td><c:out value="${orderInfo.purchaseDate}"></c:out></td>
        	 <td>
        	   <c:choose>

					<c:when test="${orderInfo.deliveryState == 1}">					
						배송전
					</c:when>	
					<c:when test="${orderInfo.deliveryState == 2}">	
						배송중
					</c:when>				
					<c:otherwise>					
						배송완료	
					</c:otherwise>				
				</c:choose>
        	 </td>

        	 <td>
        	 	  <c:choose>

					<c:when test="${orderInfo.refundState == 0}">					
						미신청
					</c:when>	
					<c:when test="${orderInfo.refundState == 1}">	
						환불신청
					</c:when>				
					<c:otherwise>					
						환불완료	
					</c:otherwise>				
				</c:choose>
        	 
        	 </td>
        	 <td><a href="note?command=purDeliveryForm&orderNo=${orderInfo.orderNo}">${orderInfo.addrDelivery}</a></td>
        	 <td><c:out value="${orderInfo.totalPrice}"></c:out></td>
        	 <td>
        	 	<c:choose>

					<c:when test="${orderInfo.deliveryState ne 1}">					
						<button id="test_btn" name="delete" hidden="" >취소하기</button>
					</c:when>	
					<c:when test="${orderInfo.refundState == 1}">	
						<button id="test_btn" name="delete" hidden="" >취소하기</button>
					</c:when>				
					<c:otherwise>					
						<button id="test_btn" name="delete" value="${orderInfo.orderNo}">취소하기</button>		
					</c:otherwise>				
				</c:choose>
        	 </td>
        	 </tr>
        	 </c:forEach>
				</table>
            </div>
         </div>
         
         
         <div class="row cart_items_row">
        	 <table>

        	 </table>
         </div>
         
               
         </div>
      </div>   
       </section> 
   </div>
    
<%@include file="footer.jsp" %>

<script>
$("button[name=delete]").click(function(){
	
   alert("삭제되었습니다.");
   var orderNo=$(this).val();
   location="note?command=purRefundState&request=1&orderNo="+orderNo;
});

$("button[name=orderDetail]").click(function(){
   alert("상품디테일");
   var orderNo = $(this).val();
   
   location = "note?command=purDetail&orderNo="+orderNo;
});
</script>

</body>
</html>