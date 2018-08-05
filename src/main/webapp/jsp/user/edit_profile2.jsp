<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Edit | ${user_data.username}</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>
      <jsp:include page = "/jsp/parts/header.jsp" />

      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
            <div class="navbar bg-light navbar-light rounded">
              <span class="navbar-brand">Edit</span>
              <ul class="navbar-nav">
                <li class="nav-item">
                  <a href="#" class="nav-link">Main</a>
                </li>
                <li class="nav-item">
                  <a href="#" class="nav-link">Password</a>
                </li>
              </ul>
            </div>
          </div>
          <div class="col-10 float-left">
            <form action="/controller" method="post">
              <input type="hidden" name="command" value="save_profile">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">Main information</h4>
                </div>
                <div class="card-body">
                  <div class="col-2 float-left">
                    <img src="../../img/no_avatar.png" alt="User photo" width="100px" height="100px">
                  </div>
                  <div class="col-10 float-left">
                    <table class="table">
                      <tbody>
                        <tr>
                          <td>Username:</td>
                          <td>@${user_data.username}</td>
                        </tr>
                        <tr>
                          <td>Email:</td>
                          <td>${user_data.email}</td>
                        </tr>
                        <tr>
                          <td>Name:</td>
                          <td>
                            <input class="form-control form-control-sm" type="text" name="edit_name" value="${user_data.name}">
                          </td>
                        </tr>
                        <tr>
                          <td>Surname:</td>
                          <td>
                            <input class="form-control form-control-sm" type="text" name="edit_surname" value="${user_data.surname}">
                          </td>
                        </tr>
                        <tr>
                          <td>Phone number:</td>
                          <td>
                            <input class="form-control form-control-sm" type="text" name="edit_phone" value="${user_data.phone}">
                          </td>
                        </tr>
                        <tr>
                          <td>Passport:</td>
                          <td>
                            <input class="form-control form-control-sm" type="text" name="edit_passport" value="${user_data.passport}">
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
                <div class="card-footer">
                  <div class="row float-right">
                    <button type="reset" class="btn btn-md btn-light border">Reset</button>
                    <button type="submit" class="btn btn-md btn-success ml-2">Save</button>
                  </div>
                </div>
              </div>
            </form>

          </div>
        </div>
      </main>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../../js/vendor/popper.min.js"></script>
      <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
