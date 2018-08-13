<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle = "${loc}" key = "local.signin.text.title" var="title" />
<fmt:message bundle = "${loc}" key = "local.signin.text.signin-header" var = "signin_header" />
<fmt:message bundle = "${loc}" key = "local.signin.text.username" var = "hint_username" />
<fmt:message bundle = "${loc}" key = "local.signin.text.password" var = "hint_password" />
<fmt:message bundle = "${loc}" key = "local.signin.button.signin" var = "button_signin" />
<fmt:message bundle = "${loc}" key = "local.signin.button.registration" var = "button_registration" />
<fmt:message bundle = "${loc}" key = "local.signin.text.error" var = "message_error" />
<fmt:message bundle = "${loc}" key = "local.signin.text.incorrect" var = "message_incorrect" />

<!doctype html>
<html>
  <head>


    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
  </head>

  <body>

    <%@ include file="/jsp/parts/header.jsp" %>

    <main role="main" class="container">
      <div class="col-4 mx-auto p-5">
        <form class="form-signin" method="post" action="/controller">
          <h1 class="h3 mb-3 font-weight-normal text-center">${signin_header}</h1>
          <input type="hidden" name="command" value="signin">

          <label for="inputLogin" class="sr-only">${hint_username}</label>
          <input type="text" name="username" id="inputLogin" class="form-control mb-2" placeholder="${hint_username}" required autofocus>

          <label for="inputPassword" class="sr-only">${hint_password}</label>
          <input type="password" name="password" id="inputPassword" class="form-control mb-2" placeholder="${hint_password}" required>

          <button class="btn btn-lg btn-secondary btn-block" type="submit">${button_signin}</button>

          <c:if test = "${incorrect_data eq true}">
            <div class="alert alert-danger text-center mt-1">
              <strong>${message_error}</strong> ${message_incorrect}
            </div>
          </c:if>

        </form>
        <div class="float-right mt-1 mr-1">
          <a href="/signup">${button_registration}</a>
        </div>
      </div>

    </main>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../js/vendor/popper.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
