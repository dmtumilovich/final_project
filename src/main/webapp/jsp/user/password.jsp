<%@ include file="/jsp/parts/bundle.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Change password</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>

      <%@ include file="/jsp/parts/header.jsp" %>
      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
            <jsp:include page = "/jsp/user/parts/edit_profile_sidebar.jsp" />
          </div>
          <div class="col-10 float-left">
            <form action="/controller" method="post">
              <input type="hidden" name="command" value="change_password">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">Change password</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <tbody>
                      <tr>
                        <td>Previous password:</td>
                        <td>
                          <input class="form-control form-control-sm" type="password" name="previous_password" value="" required>
                        </td>
                      </tr>
                      <tr>
                        <td>New password:</td>
                        <td>
                          <input class="form-control form-control-sm" type="password" name="new_password" value="" required>
                        </td>
                      </tr>
                      <tr>
                        <td>Confirm new password:</td>
                        <td>
                          <input class="form-control form-control-sm" type="password" name="confirm_new_password" value="" required>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="card-footer">
                  <div class="float-right">
                    <button type="submit" class="btn btn-md btn-success">Save password</button>
                  </div>
                </div>
              </div>
              <c:if test = "${not empty error_message}">
                <div class="alert alert-danger alert-dismissible show fade mt-2">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>${error}</strong> <fmt:message bundle = "${loc}" key = "${error_message}" />
                </div>
                <c:remove var = "error_message" scope = "session" />
              </c:if>
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
