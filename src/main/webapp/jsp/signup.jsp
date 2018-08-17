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

    <title>Sign Up</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
  </head>

  <body>

    <jsp:include page = "/jsp/parts/header.jsp" />

    <main role="main" class="container">
      <div class="col-4 mx-auto p-5">
        <form class="form-signin" method="post" action="/controller">
          <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
          <h1 class="h3 mb-3 font-weight-normal text-center">${signup_header}</h1>
          <input type="hidden" name="command" value="signup">

          <label for="inputLogin" class="sr-only">${hint_username}</label>
          <input type="text" name="username" id="inputLogin" class="form-control mb-2" placeholder="${hint_username}" required autofocus>

          <label for="inputPassword" class="sr-only">${hint_password}</label>
          <input type="password" name="password" id="inputPassword" class="form-control mb-1" placeholder="${hint_password}" required>

          <label for="inputPassword2" class="sr-only">${hint_confirm_password}</label>
          <input type="password" name="confirm_password" id="inputPassword2" class="form-control mb-2" placeholder="${hint_confirm_password}" required>

          <label for="inputEmail" class="sr-only">${hint_email}</label>
          <input type="email" name="email" id="inputEmail" class="form-control mb-2" placeholder="${hint_email}" required>

          <button class="btn btn-lg btn-secondary btn-block" type="submit">${button_signup}</button>

          <c:if test = "${not empty error_message}">
            <div class="alert alert-danger text-center mt-1">
              ${error_message}
            </div>
          </c:if>
        </form>
      </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../js/vendor/popper.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>

  </body>
</html>
