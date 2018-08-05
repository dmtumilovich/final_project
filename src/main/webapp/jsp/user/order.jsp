<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Order</title>

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
            <div class="col-5 float-left">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">${order_info.car.brand} ${order_info.car.model}</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <thead class="table-head">
                      <h5>Characteristics</h5>
                    </thead>
                    <tbody>
                      <tr>
                        <td>Class:</td>
                        <td>${order_info.car.carClass}</td>
                      </tr>
                      <tr>
                        <td>Color:</td>
                        <td>${order_info.car.color}</td>
                      </tr>
                      <tr>
                        <td>Year:</td>
                        <td>${order_info.car.yearOfIssue}</td>
                      </tr>
                      <tr>
                        <td>Seats:</td>
                        <td>${order_info.car.numberOfSeats}</td>
                      </tr>
                      <tr>
                        <td>Engine volume:</td>
                        <td>${order_info.car.engineVolume}</td>
                      </tr>
                      <tr>
                        <td>Price per day:</td>
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
                  <h4 class="card-title">Order information</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <tbody>
                      <tr>
                        <td>Order ID:</td>
                        <td>${order_info.order.id}</td>
                      </tr>
                      <tr>
                        <td>Date start:</td>
                        <td>${order_info.order.dateStart}</td>
                      </tr>
                      <tr>
                        <td>Date end:</td>
                        <td>${order_info.order.dateEnd}</td>
                      </tr>
                      <tr>
                        <td>Total cost:</td>
                        <td>${order_info.order.totalPrice}$</td>
                      </tr>
                      <tr>
                        <td>Status:</td>
                        <td>
                          <c:choose>
                            <c:when test = "${order_info.order.status eq 'RENT'}">
                              <span class="text-info">In rent</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'CONFIRMED'}">
                              <span class="text-success">Confirmed</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'REJECTED'}">
                              <span class="text-danger">Rejected</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'AWAITS'}">
                              <span class="text-important">Awaits confirmation</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'OVER'}">
                              <span class="text-warning">Rent is over</span>
                            </c:when>
                            <c:when test = "${order_info.order.status eq 'RETURNED'}">
                              <span class="text-secondary">Returned</span>
                            </c:when>
                            <c:otherwise>
                              <%-- ??? --%>
                              <span>Unknown</span>
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
                        <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#reject_order">Cancel booking</button>
                      </c:when>
                    </c:choose>

                  </div>
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
