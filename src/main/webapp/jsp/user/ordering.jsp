<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Ordering</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">

  </head>
  <body>
      <jsp:include page = "/jsp/parts/header.jsp" />

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
                    <h5>Characteristics</h5>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Class:</td>
                      <td>${booking_info.car.carClass}</td>
                    </tr>
                    <tr>
                      <td>Color:</td>
                      <td>${booking_info.car.color}</td>
                    </tr>
                    <tr>
                      <td>Year:</td>
                      <td>${booking_info.car.yearOfIssue}</td>
                    </tr>
                    <tr>
                      <td>Seats:</td>
                      <td>${booking_info.car.numberOfSeats}</td>
                    </tr>
                    <tr>
                      <td>Engine volume:</td>
                      <td>${booking_info.car.engineVolume}</td>
                    </tr>
                    <tr>
                      <td>Price per day:</td>
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
                  <h4 class="card-title">Pick date and time</h4>
                </div>
                <div class="card-body">
                  <%-- datetime picker --%>
                  <div class="form-group">
                    <label for="pickup_input">Pick-up date:</label>
                    <div class="input-group date" id="pickup" data-target-input="nearest">
                      <input type="text" name="date_start" class="form-control form-control-sm datetimepicker-input" data-target="#pickup" required/>
                      <div class="input-group-append" data-target="#pickup" data-toggle="datetimepicker">
                          <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                      </div>
                    </div>

                    <label for="dropoff_input">Drop-off date:</label>
                    <div class="input-group date" id="dropoff" data-target-input="nearest">
                      <input id="dropoff_input" type="text" name="date_end" class="form-control form-control-sm datetimepicker-input" data-target="#dropoff" required>
                      <div class="input-group-append" data-target="#dropoff" data-toggle="datetimepicker">
                        <div class="input-group-text">
                          <i class="fa fa-calendar"></i>
                        </div>
                      </div>
                    </div>
                  </div>
                  <%-- ??? --%>
                  <script type="text/javascript">
                    $(function () {
                      $('#pickup').datetimepicker({
                          locale: 'ru'
                      });
                    });
                  </script>
                  <script type="text/javascript">
                    $(function () {
                      $('#dropoff').datetimepicker({
                          locale: 'ru'
                      });
                    });
                  </script>
                </div>

                <div class="card-footer">
                  <h5 class=""><strong>Total cost:</strong> 160$ <small><i>for 2 days</i></small></h5>
                </div>
              </div>

              <div class="card mt-3">
                <div class="card-header">
                  <h4 class="card-title">Client data</h4>
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
                        <td>Username:</td>
                        <td>@${booking_info.user.username}</td>
                      </tr>
                      <tr>
                        <td>Email:</td>
                        <td>${booking_info.user.email}</td>
                      </tr>
                      <tr>
                        <td>Name:</td>
                        <td>${booking_info.user.name}</td>
                      </tr>
                      <tr>
                        <td>Surname:</td>
                        <td>${booking_info.user.surname}</td>
                      </tr>
                      <tr>
                        <td>Phone number:</td>
                        <td>${booking_info.user.phone}</td>
                      </tr>
                      <tr>
                        <td>Passport:</td>
                        <td>${booking_info.user.passport}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="card-footer">
                  <div class="float-right">
                    <a href="/controller?command=edit_profile" class="btn btn-md btn-primary">Edit</a>
                  </div>
                </div>
              </div>

              <div class="mt-3 col-6 float-right row">
                <c:choose>
                  <c:when test="${booking_info.allUserData eq false}">
                    <button type="submit" class="btn btn-lg btn-success btn-block disabled">Book now</button>
                  </c:when>
                  <c:otherwise>
                    <button type="submit" class="btn btn-lg btn-success btn-block">Book now</button>
                  </c:otherwise>
                </c:choose>
              </div>
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

      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../../js/vendor/popper.min.js"></script>
      <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
