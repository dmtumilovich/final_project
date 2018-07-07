<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test = "${not empty username}">
  <c:redirect url = "/jsp/index.jsp" />
</c:if>

<!doctype html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <fmt:setLocale value = "${sessionScope.local}" />
    <fmt:setBundle basename = "local" var = "loc" />

    <fmt:message bundle = "${loc}" key = "local.signin.text.signin-header" var = "signin_header" />
    <fmt:message bundle = "${loc}" key = "local.signin.text.username" var = "hint_username" />
    <fmt:message bundle = "${loc}" key = "local.signin.text.password" var = "hint_password" />
    <fmt:message bundle = "${loc}" key = "local.signin.button.signin" var = "button_signin" />

    <title>rent-a-car signin</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/signin.css" rel="stylesheet">
  </head

  <body class="text-center">
    <form class="form-signin" method="post" action="/controller">
      <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">${signin_header}</h1>
      <input type="hidden" name="command" value="signin">

      <label for="inputLogin" class="sr-only">${hint_username}</label>
      <input type="text" name="username" id="inputLogin" class="form-control" placeholder="${hint_username}" required autofocus>

      <label for="inputPassword" class="sr-only">${hint_password}</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="${hint_password}" required>

      <!-- <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div> -->

      <button class="btn btn-lg btn-primary btn-block" type="submit">${button_signin}</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>

  </body>
</html>
