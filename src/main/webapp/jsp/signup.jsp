<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename = "local" var = "loc" />

    <fmt:message bundle = "${loc}" key = "local.signup.text.signup-header" var = "signup_header" />
    <fmt:message bundle = "${loc}" key = "local.signup.text.username" var = "hint_username" />
    <fmt:message bundle = "${loc}" key = "local.signup.text.password" var = "hint_password" />
    <fmt:message bundle = "${loc}" key = "local.signup.text.confirm-password" var = "hint_confirm_password" />
    <fmt:message bundle = "${loc}" key = "local.signup.text.email" var = "hint_email" />
    <fmt:message bundle = "${loc}" key = "local.signup.button.signup" var = "button_signup" />
    <fmt:message bundle = "${loc}" key = "local.signin.text.error" var = "message_error" />

    <title>rent-a-car signup</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/signup.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <form class="form-signin" method="post" action="/controller">
      <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">${signup_header}</h1>
      <input type="hidden" name="command" value="signup">

      <label for="inputLogin" class="sr-only">${hint_username}</label>
      <input type="text" name="username" id="inputLogin" class="form-control" placeholder="${hint_username}" required autofocus>

      <label for="inputPassword" class="sr-only">${hint_password}</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="${hint_password}" required>

      <label for="inputPassword2" class="sr-only">${hint_confirm_password}</label>
      <input type="password" name="password2" id="inputPassword2" class="form-control" placeholder="${hint_confirm_password}" required>

      <label for="inputEmail" class="sr-only">${hint_email}</label>
      <input type="email" name="email" id="inputEmail" class="form-control" placeholder="${hint_email}" required>

      <button class="btn btn-lg btn-primary btn-block" type="submit">${button_signup}</button>

      <c:if test = "${signup_failed eq true}">
        <div class="alert alert-danger text-center">
          <strong>${message_error}</strong>
          <fmt:message bundle = "${loc}" key = "${error_message_key}" />
        </div>
      </c:if>
    </form>

  </body>
</html>
