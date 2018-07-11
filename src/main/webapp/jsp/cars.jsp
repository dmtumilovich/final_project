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

    <fmt:message bundle = "${loc}" key = "local.cars.text.title" var = "page_title" />
    <fmt:message bundle = "${loc}" key = "local.cars.text.panel-year" var = "panel_year" />
    <fmt:message bundle = "${loc}" key = "local.cars.text.panel-volume" var = "panel_volume" />
    <fmt:message bundle = "${loc}" key = "local.cars.text.panel-price" var = "panel_price" />

    <title>${page_title}</title>

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
            <a class="nav-link active" href="/controller?command=show_cars">${nav_cars}</a>
            <a class="nav-link" href="#">${nav_contact}</a>

            <c:if test = "${empty user}">
              <a class="nav-link" href="signin">${nav_signin}</a>
              <a class="nav-link" href="signup">${nav_signup}</a>
            </c:if>

            <c:if test = "${not empty user}">
              <a class="nav-link" href="profile">${nav_profile}</a>
              <a class="nav-link" href="/controller?command=logout">${nav_logout}</a>
            </c:if>

            <a class="nav-link" href="/controller?command=change_lang&local=en">${en_button}</a>
            <a class="nav-link" href="/controller?command=change_lang&local=ru">${ru_button}</a>

          </nav>
        </div>
      </header>

      <jsp:include page = "/jsp/header.jsp"></jsp:include>

      <!--profile-->
      <main role="main" class="inner cover">

        <%-- <div class="container col-lg-12 search-form">
          <form>
            <div class="row">
              <div class="col-lg-6">
                <select name="brand" class="form-control form-control-sm">
                  <option value="">Choose brand</option>
                  <option value="">BMW</option>
                  <option value="">Hyndai</option>
                  <option value="">Mercedes</option>
                  <option value="">Porsche</option>
                </select>
              </div>
              <div class="row">
                <div class="col-lg3">
                  <input type="text" name="from" value="" class="form-control form-control-sm" placeholder="Volume from:">
                </div>
                <div class="col-lg3">
                  <input type="text" name="to" value="" class="form-control form-control-sm" placeholder="to:">
                </div>
              </div>
            </div>
            <div class="row" id="search-button">
              <button type="submit" class="btn btn-block btn-primary">Search</button>
            </div>
          </form>
        </div> --%>



        <c:forEach items="${requestScope.car_list}" var = "car" varStatus="loop">
          <div class="panel panel-info">
            <div class="panel-heading">
              <h5 class="panel-title text-center"><a href="/controller?command=show_selected_car&car_id=${car.id}">${car.brand} ${car.model}</a></h5>
            </div>


            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3" align="center">
                  <img src="../img/no_avatar_car.png" alt="Car photo" class="img-circle" width="100px" height="100px">
                </div>

                <div class="col-md-9 col-lg-9">
                  <table class="table table-user-information car-list-text">
                    <tbody>
                      <tr>
                        <td>${panel_year}:</td>
                        <td>${car.yearOfIssue}</td>
                      </tr>
                      <tr>
                        <td>${panel_volume}:</td>
                        <td>${car.engineVolume}</td>
                      </tr>
                      <tr>
                        <td>${panel_price}:</td>
                        <td>${car.price}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>

              </div>
            </div>

          </div>
        </c:forEach>
      </main>


      <footer class="mastfoot mt-auto text-center">
        <div class="inner">
          <p>&copy;einott lab</p>
        </div>
      </footer>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../js/vendor/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
