<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <h2>顧客</h2>

    <form action="customerSave" method="post">
    <table>
    <tr>
      <th>顧客ID</th>
      <td>
        <input type="text" name="customerId" size="5" value="<c:out value='${customer.customerId}' />"  readonly="readonly" />
      </td>
    </tr>
    <tr>
      <th>顧客名</th>
      <td>
        <input type="text" name="customerName" size="30" value="<c:out value='${customer.customerName}' />"  />
      </td>
    </tr>
    </table>
    <br>
    <div class="row center-block text-center">
      <div class="col-1">
        <input type="submit" id="update" class="btn btn-outline-primary btn-block" value="更新">
      </div>
      <div class="col-1">
        <a href="./customerList" class="btn btn-outline-secondary btn-block" role="button">戻る</a></td>       
      </div>
    </div>
    </form>

  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<script type="text/javascript">
$('#update').on('click', function() {
	return confirm("更新しますか？")
});
</script>

  </body>
</html>
