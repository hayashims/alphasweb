<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>商品テーブルリスト</h2>

<table>
<tr>
    <th>商品ID</th>
    <th>商品名</th>
    <th>価格</th>
</tr>
  <c:forEach items="${prodList}" var="prod">
    <tr>
      <td><c:out value="${prod.prodId}" /></td>
      <td><c:out value="${prod.prodName}" /></td>
      <td><c:out value="${prod.price}" /></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
