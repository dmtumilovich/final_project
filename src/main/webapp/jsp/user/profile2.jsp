<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Profile | einott</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>
      <jsp:include page = "/jsp/parts/header.jsp" />

      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
            <jsp:include page = "/jsp/user/parts/profile_sidebar.jsp" />
          </div>
          <div class="col-10 float-left">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">Profile information</h4>
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
                        <td>${user_data.name}</td>
                      </tr>
                      <tr>
                        <td>Surname:</td>
                        <td>${user_data.surname}</td>
                      </tr>
                      <tr>
                        <td>Phone number:</td>
                        <td>${user_data.phone}</td>
                      </tr>
                      <tr>
                        <td>Passport:</td>
                        <td>${user_data.passport}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="card-footer">
                <div class="float-right">
                  <a href="/controller?command=edit_profile" class="btn btn-md btn-light border">Edit profile</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../../js/vendor/popper.min.js"></script>
      <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
