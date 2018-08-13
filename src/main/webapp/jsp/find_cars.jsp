<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>

<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key="local.find.text.title" var="title" />
<fmt:message bundle="${loc}" key="local.find.text.header-pick-dates" var="pick_dates" />
<fmt:message bundle="${loc}" key="local.find.text.label-pickup" var="label_pickup" />
<fmt:message bundle="${loc}" key="local.find.text.label-dropoff" var="label_dropoff" />
<fmt:message bundle="${loc}" key="local.find.button.search" var="button_search" />

<!DOCTYPE html>
<html>
  <head>

    <title>${title}</title>

    <link rel="stylesheet" href="../plugin/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
  </head>
  <body>

      <%@ include file="/jsp/parts/header.jsp" %>
      <main role="main" class="container">
        <div class="row">
          <div class="col-8 mx-auto">
            <form action="/controller" method="get">
              <input type="hidden" name="command" value="show_cars">
              <input type="hidden" name="page" value="1">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">${pick_dates}</h4>
                </div>
                <div class="card-body">
                  <%-- datetime picker --%>
                  <div class="form-group">
                    <label for="pickup">${label_pickup}:</label>
                    <div class="input-group input-group-sm">
                      <div class="input-group-prepend">
                        <span class="input-group-text">
                          <i class="far fa-calendar-alt"></i>
                        </span>
                      </div>
                      <input id="pickup" type="text" name="date_start" class="form-control form-control-sm">
                    </div>
                  </div>

                  <div class="form-group mt-1">
                    <label for="dropoff">${label_dropoff}:</label>
                    <div class="input-group input-group-sm">
                      <div class="input-group-prepend">
                        <span class="input-group-text">
                          <i class="far fa-calendar-alt"></i>
                        </span>
                      </div>
                      <input id="dropoff" type="text" name="date_end" class="form-control form-control-sm">
                    </div>
                  </div>
                </div>

                <div class="card-footer">
                  <div class="float-right">
                    <button type="submit" class="btn btn-md btn-success">${button_search}</button>
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
      <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>
      <script src="../../plugin/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
      <script src="../../js/script.js"></script>
  </body>
</html>
