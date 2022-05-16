<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>商品テーブルリスト</h2>


<form action="prodListop" method="post">
<table>
<tr>
    <th></th>
    <th>商品ID</th>
    <th>商品名</th>
    <th>価格</th>
</tr>
  <c:forEach items="${prodList}" var="prod">
    <tr>
      <td>
      	<input class="form-check-input" type="checkbox" name="check" id="check" value="${prod.prodId}">
      </td>
      <td><label class="form-check-label" for="check">${prod.prodId}</label></td>
      <td><c:out value="${prod.prodName}" /></td>
      <td><c:out value="${prod.price}" /></td>
    </tr>
  </c:forEach>
</table>
<br>

    <div class="row center-block text-center">
      <div class="col-1">
        <input type="submit" id="delete" class="btn btn-outline-primary btn-block" value="削除">
      </div>
      <div class="col-1">
        <button type="button" id="return" class="btn btn-outline-secondary btn-block" onclick="history.back()">戻る</button>
      </div>
    </div>

</form>
</body>
</html>
