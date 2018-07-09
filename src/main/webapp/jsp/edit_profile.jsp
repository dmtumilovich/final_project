<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>

    <c:if test="${empty user}">
      <c:redirect url = "main" />
    </c:if>

    <title>Edit profile</title>

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

    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
      <header class="masthead mb-auto">
        <div class="inner">
          <h3 class="masthead-brand">${title}</h3>
          <nav class="nav nav-masthead justify-content-center">
            <a class="nav-link" href="main">${nav_home}</a>
            <a class="nav-link" href="#">${nav_cars}</a>
            <a class="nav-link" href="#">${nav_contact}</a>

            <c:if test = "${empty user}">
              <a class="nav-link" href="signin">${nav_signin}</a>
              <a class="nav-link" href="signup">${nav_signup}</a>
            </c:if>

            <c:if test = "${not empty user}">
              <a class="nav-link active" href="profile">${nav_profile}</a>
              <a class="nav-link" href="/controller?command=logout">${nav_logout}</a>
            </c:if>

            <a class="nav-link" href="/controller?command=change_lang&local=en">${en_button}</a>
            <a class="nav-link" href="/controller?command=change_lang&local=ru">${ru_button}</a>

          </nav>
        </div>
      </header>


      <main role="main" class="inner cover">
        <div class="panel panel-info">
          <div class="panel-heading">
            <h3 class="panel-title text-center">Profile information</h3>
          </div>
          <div class="panel-body">
            <form method="post" action="/controller">
              <div class="row">

                <div class="col-md-3 col-lg-3" align="center">
                  <img src="../img/no_avatar.png" alt="Photo" class="img-circle" width="150px" height="150px">
                </div>


                  <div class="col-md-9 col-lg-9">
                      <input type="hidden" name="command" value="edit_profile">
                      <table class="table table-user-information">
                        <tbody>
                          <tr>
                            <td>Username:</td>
                            <td>@${user.username}</td>
                          </tr>
                          <tr>
                            <td>Email:</td>
                            <td>${user.email}</td>
                          </tr>
                          <tr>
                            <td>Name:</td>
                            <td> <input type="text" name="edit_name" value="${user.name}" class="form-control" /> </td>
                          </tr>
                          <tr>
                            <td>Surname:</td>
                            <td> <input type="text" name="edit_surname" value="${user.surname}" class="form-control" /> </td>
                          </tr>
                          <tr>
                            <td>Phone number:</td>
                            <td> <input type="text" name="edit_phone" class="form-control" /> </td>
                          </tr>
                          <tr>
                            <td>Passport:</td>
                            <td> <input type="text" name="edit_passport" value="${user.passport}" class="form-control" /> </td>
                          </tr>
                        </tbody>
                      </table>

                    </div>

                </div>

                <div class="panel-footer text-center">
                    <button class="btn btn-lg btn-secondary btn-block" type="submit">Save changes</button>
                </div>
              </form>
            </div>
        </div>
      </main>


      <footer class="mastfoot mt-auto text-center">
        <div class="inner">
          <p>&copy;einott lab</p>
        </div>
      </footer>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../js/vendor/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
