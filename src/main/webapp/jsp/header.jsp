<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <fmt:setLocale value = "${sessionScope.local}" />
    <fmt:setBundle basename = "local" var = "loc" />
    <fmt:message bundle = "${loc}" key = "local.header.button.en" var = "en_button" />
    <fmt:message bundle = "${loc}" key = "local.header.button.ru" var = "ru_button" />
    <fmt:message bundle = "${loc}" key = "local.header.text.title" var = "title" />
    <fmt:message bundle = "${loc}" key = "local.header.text.nav-home" var = "nav_home" />
    <fmt:message bundle = "${loc}" key = "local.header.text.nav-cars" var = "nav_cars" />
    <fmt:message bundle = "${loc}" key = "local.header.text.nav-contact" var = "nav_contact" />
    <fmt:message bundle = "${loc}" key = "local.header.text.nav-signin" var = "nav_signin" />
    <fmt:message bundle = "${loc}" key = "local.header.text.nav-signup" var = "nav_signup" />
    <fmt:message bundle = "${loc}" key = "local.header.text.nav-profile" var = "nav_profile" />
    <fmt:message bundle = "${loc}" key = "local.header.text.nav-logout" var = "nav_logout" />

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/cover.css" rel="stylesheet">
  </head>
  <body>
    <!-- <header class="masthead mb-auto">
      <div class="inner">
        <h3 class="masthead-brand">${title}</h3>
        <nav class="nav nav-masthead justify-content-center">
          <a class="nav-link active" href="main">${nav_home}</a>
          <a class="nav-link" href="#">${nav_cars}</a>
          <a class="nav-link" href="#">${nav_contact}</a>

          <c:if test = "${empty username}">
            <a class="nav-link" href="signin">${nav_signin}</a>
            <a class="nav-link" href="signup">${nav_signup}</a>
          </c:if>

          <c:if test = "${not empty username}">
            <a class="nav-link" href="profile">${nav_profile}</a>
            <a class="nav-link" href="/controller?command=logout">${nav_logout}</a>
          </c:if>

          <a class="nav-link" href="/controller?command=change_lang&local=en">${en_button}</a>
          <a class="nav-link" href="/controller?command=change_lang&local=ru">${ru_button}</a>

        </nav>
      </div>
    </header> -->

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../js/vendor/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
