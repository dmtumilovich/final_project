<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Admin panel</title>

    <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/style.css">

  </head>

  <body>

    <jsp:include page = "/jsp/admin/parts/header.jsp" />

    <main role="main" class="container">
      <div class="jumbotron">
        <h2>This is an empty page</h2>
        <p class="lead">You can see other admin functions in users and cars sections</p>
        <div class="row">
          <div class="col">
            <a href="#" class="btn btn-lg btn-primary">See users info &raquo;</a>
          </div>
          <div class="col">
            <a href="/controller?command=show_car_table" class="btn btn-lg btn-primary">See cars info &raquo;</a>
          </div>
        </div>
      </div>
    </main>



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../js/vendor/popper.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
