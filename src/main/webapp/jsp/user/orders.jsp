<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key = "local.user-orders.text.title" var = "title" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.card-title" var = "card_title" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.card-car" var = "card_car" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.card-date-start" var = "card_date_start" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.card-date-end" var = "card_date_end" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.card-price" var = "card_price" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.card-status" var = "card_status" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-rent" var = "status_rent" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-confirmed" var = "status_confirmed" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-rejected" var = "status_rejected" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-awaits" var = "status_awaits" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-over" var = "status_over" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-returned" var = "status_returned" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-canceled" var = "status_canceled" />
<fmt:message bundle="${loc}" key = "local.user-orders.text.status-unknown" var = "status_unknown" />
<fmt:message bundle="${loc}" key = "local.user-orders.button.check" var = "button_check" />
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
      <jsp:include page = "/jsp/parts/header.jsp" />

      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
            <jsp:include page = "/jsp/user/parts/profile_sidebar.jsp" />
          </div>
          <div class="col-10 float-left">
            <c:if test = "${not empty success_message}">
              <div class="alert alert-success alert-dismissible show fade mb-2">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${success}</strong> <fmt:message bundle = "${loc}" key = "${success_message}" />
              </div>
              <c:remove var = "success_message" scope = "session" />
            </c:if>
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">${card_title}</h4>
              </div>
              <div class="card-body">
                <table class="table">
                  <thead>
                    <th>#</th>
                    <th>${card_car}</th>
                    <th>${card_date_start}</th>
                    <th>${card_date_end}</th>
                    <th>${card_price}</th>
                    <th>${card_status}</th>
                    <th></th>
                  </thead>
                  <tbody>
                    <c:forEach items="${requestScope.order_list}" var = "order_item" varStatus = "loop">
                      <tr>
                        <td>${(loop.index + 1) + (page - 1) * 10}</td>
                        <td>
                          <a href="/controller?command=show_selected_car&car_id=${order_item.order.carID}">${order_item.car.brand} ${order_item.car.model}</a>
                        </td>
                        <td><fmt:formatDate value = "${order_item.order.dateStart}" pattern = "dd.MM.yyyy HH:mm" /></td>
                        <td><fmt:formatDate value = "${order_item.order.dateEnd}" pattern = "dd.MM.yyyy HH:mm" /></td>
                        <td>${order_item.order.totalPrice}$</td>
                        <td>
                          <c:choose>
                            <c:when test = "${order_item.order.status eq 'RENT'}">
                              <span class="text-info">${status_rent}</span>
                            </c:when>
                            <c:when test = "${order_item.order.status eq 'CONFIRMED'}">
                              <span class="text-success">${status_confirmed}</span>
                            </c:when>
                            <c:when test = "${order_item.order.status eq 'REJECTED'}">
                              <span class="text-danger">${status_rejected}</span>
                            </c:when>
                            <c:when test = "${order_item.order.status eq 'AWAITS'}">
                              <span class="text-important">${status_awaits}</span>
                            </c:when>
                            <c:when test = "${order_item.order.status eq 'OVER'}">
                              <span class="text-warning">${status_over}</span>
                            </c:when>
                            <c:when test = "${order_item.order.status eq 'RETURNED'}">
                              <span class="text-secondary">${status_returned}</span>
                            </c:when>
                            <c:when test = "${order_item.order.status eq 'CANCELED'}">
                              <span class="text-danger">${status_canceled}</span>
                            </c:when>
                            <c:otherwise>
                              <%-- ??? --%>
                              <span>${status_unknown}</span>
                            </c:otherwise>
                          </c:choose>
                        </td>
                        <td>
                          <a href="/controller?command=user_order&order_id=${order_item.order.id}" class="btn btn-sm btn-light border">${button_check}</a>
                        </td>
                      </tr>
                    </c:forEach>
                    <%-- <tr>
                      <td>1</td>
                      <td>
                        <a href="#">BMW 520i</a>
                      </td>
                      <td>05.08.2018 12:00</td>
                      <td>06.08.2018 14:00</td>
                      <td>160$</td>
                      <td><span class="text-important">Awaits</span></td>
                      <td>
                        <button type="button" class="btn btn-sm btn-light border">Check</button>
                      </td>
                    </tr>
                    <tr>
                      <td>2</td>
                      <td>
                        <a href="#">Chevrolet Cobalt</a>
                      </td>
                      <td>04.08.2018 12:00</td>
                      <td>06.08.2018 12:00</td>
                      <td>90$</td>
                      <td><span class="text-success">Confirmed</span></td>
                      <td>
                        <button type="button" class="btn btn-sm btn-light border">Check</button>
                      </td>
                    </tr> --%>
                  </tbody>
                </table>
              </div>
            </div>

            <div class="mt-1">
                <paginator:display currentPage="${page}" totalPages="${page_count}" maxLinks="10" url="/controller?command=orders&page=##" />
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
