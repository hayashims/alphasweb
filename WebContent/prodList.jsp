<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>商品テーブルリスト</title>
  </head>
<body>
<h2>商品テーブルリスト</h2>


<form action="prodListop" method="post">
<table>
<tr>
    <th>商品ID</th>
    <th>商品名</th>
    <th>価格</th>
    <th></th>
</tr>
  <c:forEach items="${prodList}" var="prod">
    <tr>
      <td>
          <input type="checkbox" name="check" id="check" value="${prod.prodId}">
          &nbsp;&nbsp; ${prod.prodId}
      </td>
      <td><c:out value="${prod.prodName}" /></td>
      <td align="right"><c:out value="${prod.price}" /></td>
      <td><a href="./prod?prodId=${prod.prodId}" class="btn btn-secondary btn-sm" role="button">更新</a></td>
    </tr>
  </c:forEach>
</table>
<br>

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
    location.href="/alphasweb/prod"
});
$('#return').on('click', function() {
    location.href="/alphasweb/"
});

$('#delete').on('click', function() {
	let checked = document.querySelectorAll('input[type="checkbox"]:checked');
	if(checked.length == 0) {
		alert("何も選択されていません。");
		return false;
	} 
	return confirm("削除しますか？")
});
</script>

</body>
</html>


