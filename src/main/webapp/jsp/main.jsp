<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>


<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key="local.main.text.title" var="title" />
<fmt:message bundle="${loc}" key="local.main.text.heading" var="heading" />
<fmt:message bundle="${loc}" key="local.main.text.heading-description" var="description" />
<fmt:message bundle="${loc}" key="local.main.button.see-all-cars" var="button_all_cars" />


<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${title}</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
  </head>
  <body>

      <%@ include file="/jsp/parts/header.jsp" %>
      <main role="main" class="container">
        <div class="jumbotron">
          <h2>${heading}</h2>
          <p class="lead">${description}</p>
          <p>
            <a href="/find" class="btn btn-lg btn-primary">${button_all_cars} &raquo;</a>
          </p>
        </div>
      </main>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../js/vendor/popper.min.js"></script>
      <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
