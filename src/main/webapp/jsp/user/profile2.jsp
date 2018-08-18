<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key = "local.profile.text.title" var = "title" />
<fmt:message bundle="${loc}" key = "local.profile.text.card-title" var = "card_title" />
<fmt:message bundle="${loc}" key = "local.profile.text.card-username" var = "card_username" />
<fmt:message bundle="${loc}" key = "local.profile.text.card-email" var = "card_email" />
<fmt:message bundle="${loc}" key = "local.profile.text.card-name" var = "card_name" />
<fmt:message bundle="${loc}" key = "local.profile.text.card-surname" var = "card_surname" />
<fmt:message bundle="${loc}" key = "local.profile.text.card-phone" var = "card_phone" />
<fmt:message bundle="${loc}" key = "local.profile.text.card-passport" var = "card_passport" />
<fmt:message bundle="${loc}" key = "local.profile.button.edit" var = "button_edit" />
<fmt:message bundle="${loc}" key = "local.profile.button.browse" var = "button_browse" />
<fmt:message bundle="${loc}" key = "local.profile.button.upload" var = "button_upload" />
<fmt:message bundle="${loc}" key = "local.text.success" var = "success" />


<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${title} | einott</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>

      <%@ include file="/jsp/parts/header.jsp" %>
      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
            <jsp:include page = "/jsp/user/parts/profile_sidebar.jsp" />
          </div>
          <div class="col-10 float-left">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">${card_title}</h4>
              </div>
              <div class="card-body">
                <div class="col-2 float-left text-center">
                  <img src="../../img/uploads/user/${user_data.photoUrl}" alt="User photo" width="150px" height="187px" class="img-thumbnail">
                    <form action="/upload" method="post" enctype="multipart/form-data">
                      <input type="hidden" name="command" value="upload_user_photo">
                      <label class="btn btn-sm btn-light btn-block border mt-1">
                        ${button_browse}<input type="file" name="user_photo" style="display: none;" required/>
                      </label>
                      <button type="submit" class="btn btn-sm btn-block btn-secondary">${button_upload}</button>
                    </form>
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
                        <td>${user_data.name}</td>
                      </tr>
                      <tr>
                        <td>${card_surname}:</td>
                        <td>${user_data.surname}</td>
                      </tr>
                      <tr>
                        <td>${card_phone}:</td>
                        <td>${user_data.phone}</td>
                      </tr>
                      <tr>
                        <td>${card_passport}:</td>
                        <td>${user_data.passport}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="card-footer">
                <div class="float-right">
                  <a href="/controller?command=edit_profile" class="btn btn-md btn-light border">${button_edit}</a>
                </div>
              </div>
            </div>
            <c:if test = "${not empty success_message}">
              <div class="alert alert-success alert-dismissible show fade mt-2">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${success}</strong> <fmt:message bundle = "${loc}" key = "${success_message}" />
              </div>
              <c:remove var = "success_message" scope = "session" />
            </c:if>
          </div>
        </div>
      </main>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../../js/vendor/popper.min.js"></script>
      <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
