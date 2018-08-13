<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key = "local.user-order.text.title" var = "title" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-car-characteristics" var = "card_characteristics" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-car-class" var = "card_class" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-car-color" var = "card_color" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-car-year" var = "card_year" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-car-seats" var = "card_seats" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-car-volume" var = "card_volume" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-car-price" var = "card_price" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-order-title" var = "card_order_title" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-order-id" var = "card_order_id" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-order-date-start" var = "card_date_start" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-order-date-end" var = "card_date_end" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-order-total-cost" var = "card_total_cost" />
<fmt:message bundle="${loc}" key = "local.user-order.text.card-order-status" var = "card_status" />
<fmt:message bundle="${loc}" key = "local.user-order.button.cancel" var = "button_cancel" />
<fmt:message bundle="${loc}" key = "local.user-order.text.cancel-modal-title" var = "cancel_modal_title" />
<fmt:message bundle="${loc}" key = "local.user-order.text.cancel-modal-body" var = "cancel_modal_body" />
<fmt:message bundle="${loc}" key = "local.user-order.button.yes" var = "button_yes" />
<fmt:message bundle="${loc}" key = "local.user-order.button.no" var = "button_no" />


<fmt:message bundle="${loc}" key = "local.status.rent" var = "status_rent" />
<fmt:message bundle="${loc}" key = "local.status.confirmed" var = "status_confirmed" />
<fmt:message bundle="${loc}" key = "local.status.rejected" var = "status_rejected" />
<fmt:message bundle="${loc}" key = "local.status.awaits" var = "status_awaits" />
<fmt:message bundle="${loc}" key = "local.status.over" var = "status_overt" />
<fmt:message bundle="${loc}" key = "local.status.returned" var = "status_returned" />
<fmt:message bundle="${loc}" key = "local.status.canceled" var = "status_canceled" />
<fmt:message bundle="${loc}" key = "local.status.unknown" var = "status_unknown" />



<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${title} #${order_info.order.id}</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>

      <%@ include file="/jsp/parts/header.jsp" %>
      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
            <%@ include file="/jsp/user/parts/profile_sidebar.jsp" %>
          </div>
          <div class="col-10 float-left">
            <div class="col-5 float-left">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">${order_info.car.brand} ${order_info.car.model}</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <thead class="table-head">
                      <h5>${card_characteristics}</h5>
                    </thead>
                    <tbody>
                      <tr>
                        <td>${card_class}:</td>
                        <td>${order_info.car.carClass}</td>
                      </tr>
                      <tr>
                        <td>${card_color}:</td>
                        <td>${order_info.car.color}</td>
                      </tr>
                      <tr>
                        <td>${card_year}:</td>
                        <td>${order_info.car.yearOfIssue}</td>
                      </tr>
                      <tr>
                        <td>${card_seats}:</td>
                        <td>${order_info.car.numberOfSeats}</td>
                      </tr>
                      <tr>
                        <td>${card_volume}:</td>
                        <td>${order_info.car.engineVolume}</td>
                      </tr>
                      <tr>
                        <td>${card_price}:</td>
                        <td>${order_info.car.price}$</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            <div class="col-7 float-left">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">${card_order_title}</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <tbody>
                      <tr>
                        <td>${card_order_id}:</td>
                        <td>${order_info.order.id}</td>
                      </tr>
                      <tr>
                        <td>${card_date_start}:</td>
                        <td><fmt:formatDate value = "${order_info.order.dateStart}" pattern = "dd.MM.yyyy HH:mm" /></td>
                      </tr>
                      <tr>
                        <td>${card_date_end}:</td>
                        <td><fmt:formatDate value = "${order_info.order.dateEnd}" pattern = "dd.MM.yyyy HH:mm" /></td>
                      </tr>
                      <tr>
                        <td>${card_total_cost}:</td>
                        <td>${order_info.order.totalPrice}$</td>
                      </tr>
                      <tr>
                        <td>${card_status}:</td>
                        <td>
                          <c:choose>
                            <c:when test = "${order_info.order.status eq 'RENT'}">
                              <span class="text-info">${status_rent}</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'CONFIRMED'}">
                              <span class="text-success">${status_confirmed}</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'REJECTED'}">
                              <span class="text-danger">${status_rejected}</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'AWAITS'}">
                              <span class="text-important">${status_awaits}</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'OVER'}">
                              <span class="text-warning">${status_over}</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'RETURNED'}">
                              <span class="text-secondary">${status_returned}</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'CANCELED'}">
                              <span class="text-danger">${status_canceled}</span>
                            </c:when>
                            <c:otherwise>
                              <%-- ??? --%>
                              <span>${status_unknown}</span>
                            </c:otherwise>
                          </c:choose>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="card-footer">
                  <div class="row float-right">
                    <c:choose>
                      <c:when test = "${order_info.order.status eq 'AWAITS'}">
                        <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#cancel_order">${button_cancel}</button>
                      </c:when>
                    </c:choose>

                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </main>


      <div class="modal fade" id="cancel_order">
        <div class="modal-dialog">
          <div class="modal-content">
            <form action="/controller" method="post">
              <input type="hidden" name="command" value="update_status">
              <input type="hidden" name="status" value="canceled">
              <input type="hidden" name="order_id" value="${order_info.order.id}">
              <div class="modal-header">
                <h4 class="modal-title">${cancel_modal_title}</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
              </div>

              <div class="modal-body">
                ${cancel_modal_body}
              </div>

              <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">${button_no}</button>
                <button type="submit" class="btn btn-success ml-2">${button_yes}</button>
              </div>
            </form>
          </div>
        </div>
      </div>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../../js/vendor/popper.min.js"></script>
      <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
