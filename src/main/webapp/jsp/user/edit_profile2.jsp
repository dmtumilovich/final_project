<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key = "local.edit-profile.text.title" var = "title" />
<fmt:message bundle="${loc}" key = "local.edit-profile.text.card-title" var = "card_title" />
<fmt:message bundle="${loc}" key = "local.edit-profile.text.card-username" var = "card_username" />
<fmt:message bundle="${loc}" key = "local.edit-profile.text.card-email" var = "card_email" />
<fmt:message bundle="${loc}" key = "local.edit-profile.text.card-name" var = "card_name" />
<fmt:message bundle="${loc}" key = "local.edit-profile.text.card-surname" var = "card_surname" />
<fmt:message bundle="${loc}" key = "local.edit-profile.text.card-phone" var = "card_phone" />
<fmt:message bundle="${loc}" key = "local.edit-profile.text.card-passport" var = "card_passport" />
<fmt:message bundle="${loc}" key = "local.edit-profile.button.save" var = "button_save" />
<fmt:message bundle="${loc}" key = "local.edit-profile.button.reset" var = "button_reset" />

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${title} | ${user_data.username}</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>

      <%@ include file="/jsp/parts/header.jsp" %>
      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
              <%@ include file="/jsp/user/parts/edit_profile_sidebar.jsp" %>
          </div>
          <div class="col-10 float-left">
            <form action="/controller" method="post">
              <input type="hidden" name="command" value="save_profile">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">${card_title}</h4>
                </div>
                <div class="card-body">
                  <div class="col-2 float-left text-center">
                    <img src="../../img/uploads/user/${user_data.photoUrl}" alt="User photo" width="150px" height="187px" class="img-thumbnail">
                  </div>
                  <div class="col-10 float-left">
                    <table class="table">
                      <tbody>
                        <tr>
                          <td>${card_username}:</td>
                          <td>@${user_data.username}</td>
                        </tr>
                        <tr>
                          <td>${card_email}:</td>
                          <td>${user_data.email}</td>
                        </tr>
                        <tr>
                          <td>${card_name}:</td>
                          <td>
                            <input class="form-control form-control-sm" type="text" name="edit_name" value="${user_data.name}">
                          </td>
                        </tr>
                        <tr>
                          <td>${card_surname}:</td>
                          <td>
                            <input class="form-control form-control-sm" type="text" name="edit_surname" value="${user_data.surname}">
                          </td>
                        </tr>
                        <tr>
                          <td>${card_phone}:</td>
                          <td>
                            <input class="form-control form-control-sm" type="text" name="edit_phone" value="${user_data.phone}">
                          </td>
                        </tr>
                        <tr>
                          <td>${card_passport}:</td>
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
                    <button type="reset" class="btn btn-md btn-light border">${button_reset}</button>
                    <button type="submit" class="btn btn-md btn-success ml-2">${button_save}</button>
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
