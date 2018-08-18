<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key = "local.ordering.text.title" var = "title" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-car-characteristics" var = "card_characteristics" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-car-class" var = "card_class" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-car-color" var = "card_color" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-car-year" var = "card_year" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-car-seats" var = "card_seats" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-car-volume" var = "card_volume" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-car-price" var = "card_price" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-order-title" var = "card_order_title" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-order-pickup" var = "card_pickup" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-order-dropoff" var = "card_dropoff" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-order-total-cost" var = "card_total_cost" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-order-days" var = "card_days" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-client-title" var = "card_client_title" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-client-username" var = "card_username" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-client-email" var = "card_email" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-client-name" var = "card_name" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-client-surname" var = "card_surname" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-client-phone" var = "card_phone" />
<fmt:message bundle="${loc}" key = "local.ordering.text.card-client-passport" var = "card_passport" />
<fmt:message bundle="${loc}" key = "local.ordering.button.edit-profile" var = "button_edit_profile" />
<fmt:message bundle="${loc}" key = "local.ordering.button.book" var = "button_book" />
<fmt:message bundle="${loc}" key = "local.text.error" var = "error" />

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${title}</title>

    <link rel="stylesheet" href="../../plugin/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">

  </head>
  <body>

      <%@ include file="/jsp/parts/header.jsp" %>
      <main role="main" class="container">
        <div class="row">
          <div class="col-4 float-left">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">${booking_info.car.brand} ${booking_info.car.model}</h4>
              </div>
              <div class="card-body">
                <table class="table">
                  <thead class="table-head">
                    <h5>${card_characteristics}</h5>
                  </thead>
                  <tbody>
                    <tr>
                      <td>${card_class}:</td>
                      <td>${booking_info.car.carClass}</td>
                    </tr>
                    <tr>
                      <td>${card_color}:</td>
                      <td>${booking_info.car.color}</td>
                    </tr>
                    <tr>
                      <td>${card_year}:</td>
                      <td>${booking_info.car.yearOfIssue}</td>
                    </tr>
                    <tr>
                      <td>${card_seats}:</td>
                      <td>${booking_info.car.numberOfSeats}</td>
                    </tr>
                    <tr>
                      <td>${card_volume}:</td>
                      <td>${booking_info.car.engineVolume}</td>
                    </tr>
                    <tr>
                      <td>${card_price}:</td>
                      <td>${booking_info.car.price}$</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>

          </div>
          <div class="col-8 float-left">
            <form action="/controller" method="post">
              <input type="hidden" name="command" value="make_order">
              <input type="hidden" name="car_id" value="${booking_info.car.id}">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">${card_order_title}</h4>
                </div>
                <div class="card-body">
                  <%-- datetime picker --%>
                  <div class="form-group">
                    <label for="pickup">${card_pickup}:</label>
                    <div class="input-group input-group-sm">
                      <div class="input-group-prepend">
                        <span class="input-group-text">
                          <i class="far fa-calendar-alt"></i>
                        </span>
                      </div>
                      <input type="text" class="form-control form-control-sm" value="${date_start}" disabled>
                    </div>
                  </div>

                  <div class="form-group mt-1">
                    <label for="dropoff">${card_dropoff}:</label>
                    <div class="input-group input-group-sm">
                      <div class="input-group-prepend">
                        <span class="input-group-text">
                          <i class="far fa-calendar-alt"></i>
                        </span>
                      </div>
                      <input type="text" class="form-control form-control-sm" value="${date_end}" disabled>
                    </div>
                  </div>
                </div>

                <div class="card-footer">
                  <h5 class=""><strong>${card_total_cost}:</strong> ${booking_info.totalCost}$, <small><i>${card_days}: ${booking_info.numberOfDays}</i></small></h5>
                </div>
              </div>

              <div class="card mt-3">
                <div class="card-header">
                  <h4 class="card-title">${card_client_title}</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <c:if test="${booking_info.allUserData eq false}">
                      <thead>
                        <p class="text-danger ml-2">You did not fill all the fields!</p>
                      </thead>
                    </c:if>
                    <tbody>
                      <tr>
                        <td>${card_username}:</td>
                        <td>@${booking_info.user.username}</td>
                      </tr>
                      <tr>
                        <td>${card_email}:</td>
                        <td>${booking_info.user.email}</td>
                      </tr>
                      <tr>
                        <td>${card_name}:</td>
                        <td>${booking_info.user.name}</td>
                      </tr>
                      <tr>
                        <td>${card_surname}:</td>
                        <td>${booking_info.user.surname}</td>
                      </tr>
                      <tr>
                        <td>${card_phone}:</td>
                        <td>${booking_info.user.phone}</td>
                      </tr>
                      <tr>
                        <td>${card_passport}:</td>
                        <td>${booking_info.user.passport}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="card-footer">
                  <div class="float-right">
                    <a href="/controller?command=edit_profile" class="btn btn-md btn-primary">${button_edit_profile}</a>
                  </div>
                </div>
              </div>

              <div class="mt-2 col-6 float-right row">
                <c:choose>
                  <c:when test="${booking_info.allUserData eq false}">
                    <button type="submit" class="btn btn-lg btn-success btn-block disabled">${button_book}</button>
                  </c:when>
                  <c:otherwise>
                    <button type="submit" class="btn btn-lg btn-success btn-block">${button_book}</button>
                  </c:otherwise>
                </c:choose>
              </div>

              <c:if test = "${not empty error_message}">
                <div class="alert alert-danger alert-dismissible show fade mt-2">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>${error}</strong> <fmt:message bundle = "${loc}" key = "${error_message}" />
                </div>
                <c:remove var = "error_message" scope = "session" />
              </c:if>
            </form>



            <%-- <div class="card mt-3">
              <div class="card-header">
                <h4 class="card-title">Payment info</h4>
              </div>
              <div class="card-body">
                <label for="cardNumber_input">Card number</label>
                <input type="text" class="form-control mb-2" name="card_number" value="" id="cardNumber_input">
                <label for="expire_input">Expiry date(MM/YYYY)</label>
                <div class="input-group">
                  <select class="form-control" name="expire_month">
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                  </select>
                  <select class="form-control" name="expire_year">
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                    <option value="2025">2025</option>
                    <option value="2026">2026</option>
                    <option value="2027">2027</option>
                    <option value="2028">2028</option>
                  </select>
                </div>
              </div>
            </div> --%>

          </div>
        </div>
      </main>


      <script src="../../js/bootstrap-datetimepicker.min.js"></script>
      <script src="../../js/script.js"></script>

      <%-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> --%>
      <%-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script> --%>
      <%-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script> --%>
      <%-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" /> --%>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../../js/vendor/popper.min.js"></script>
      <script src="../../js/bootstrap.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>
      <script src="../../plugin/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
      <script src="../../js/script.js"></script>
  </body>
</html>
