<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Rent yourself a car</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
  </head>
  <body>
      <jsp:include page = "/jsp/parts/header.jsp" />

      <main role="main" class="container">
        <div class="jumbotron">
          <h2>Rent yourself a car</h2>
          <p class="lead">We offer you the best cars on good terms. You can see the list of all available cars.</p>
          <p>
            <a href="/controller?command=show_cars" class="btn btn-lg btn-primary">See all cars &raquo;</a>
          </p>
        </div>
      </main>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../js/vendor/popper.min.js"></script>
      <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
