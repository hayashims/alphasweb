<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>注文データリスト</title>
  </head>

  <body>
  <h2 class="my-3 ml-3">注文データリスト</h2>

  <form action="orderListop" method="post">
  <div class="col-8 ml-3">
  <table class="table table-striped">
    <tr align="center">
      <th></th>
      <th>注文ID</th>
      <th>注文日時</th>
      <th>顧客名</th>
      <th>商品名</th>
      <th>価格</th>
      <th>数量</th>
      <th>金額</th>
      <th></th>
    </tr>
  <c:forEach items="${orderList}" var="order">
    <tr>
	  <td>
		<input type="checkbox" name="check" id="check" value="${order.orderId}">
 	  </td>    
      <td align="right"><c:out value="${order.orderId}" /></td>
      <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
      <td><c:out value="${order.customerName}" /></td>
      <td><c:out value="${order.prodName}" /></td>
      <td align="right"><c:out value="${order.price}" /></td>
      <td align="right"><c:out value="${order.qty}" /></td>
      <td align="right"><c:out value="${order.amount}" /></td>
      <td><a href="./order?orderId=${order.orderId}" class="btn btn-secondary btn-sm" role="button">更新</a></td>
    </tr>
  </c:forEach>
  </table>
  </div>

  <div class="row center-block text-center">
    <div class="col-1">
      <button type="button" id="add" class="btn btn-outline-primary btn-block">追加</button>
    </div>
    <div class="col-1">
      <input type="submit" id="delete" class="btn btn-outline-primary btn-block" value="削除">
    </div>
    <div class="col-1">
      <button type="button" id="return" class="btn btn-outline-secondary btn-block">戻る</button>
    </div>
  </div>

  </form>
  
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script type="text/javascript">
$('#add').on('click', function() {
    location.href="/alphasweb/order"
});
$('#return').on('click', function() {
    location.href="/alphasweb/"
});
</script>

  </body>
</html>
