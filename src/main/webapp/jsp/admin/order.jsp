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
    <jsp:include page = "/jsp/admin/parts/header.jsp" />

    <main role="main" class="container">
      <div class="row">
        <div class="col-2 float-left">
          <jsp:include page = "/jsp/admin/parts/orders_sidebar.jsp" />
        </div>
        <div class="col-10 float-left">
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
                    <td><fmt:formatDate value = "${order_info.order.dateStart}" pattern = "dd.MM.yyyy HH:mm" /></td>
                  </tr>
                  <tr>
                    <td>Date end:</td>
                    <td><fmt:formatDate value = "${order_info.order.dateEnd}" pattern = "dd.MM.yyyy HH:mm" /></td>
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
                        <c:when test = "${order_info.order.status eq 'CANCELED'}">
                          <span class="text-danger">Canceled</span>
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
                    <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#reject_order">Reject</button>
                    <button type="button" class="btn btn-md btn-success ml-2" data-toggle="modal" data-target="#confirm_order">Confirm</button>
                  </c:when>
                </c:choose>

              </div>
            </div>
          </div>

          <div class="row mt-3">
            <div class="col">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">Client information</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <tbody>
                      <tr>
                        <td>Username:</td>
                        <td>@${order_info.user.username}</td>
                      </tr>
                      <tr>
                        <td>Name:</td>
                        <td>${order_info.user.name}</td>
                      </tr>
                      <tr>
                        <td>Surname:</td>
                        <td>${order_info.user.surname}</td>
                      </tr>
                      <tr>
                        <td>Phone:</td>
                        <td>${order_info.user.phone}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="card-footer">
                  <div class="row float-right">
                    <a href="#" class="btn btn-md btn-light border">See more info</a>
                  </div>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">Car information</h4>
                </div>
                <div class="card-body">
                  <table class="table">
                    <tbody>
                      <tr>
                        <td>Brand:</td>
                        <td>${order_info.car.brand}</td>
                      </tr>
                      <tr>
                        <td>Model:</td>
                        <td>${order_info.car.model}</td>
                      </tr>
                      <tr>
                        <td>Class:</td>
                        <td>${order_info.car.carClass}</td>
                      </tr>
                      <tr>
                        <td>Price per day:</td>
                        <td>${order_info.car.price}$</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="card-footer">
                  <div class="row float-right">
                    <a href="/controller?command=get_car_info&car_id=${order_info.car.id}" class="btn btn-md btn-light border">See more info</a>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </main>

    <%-- confirm order modal --%>
    <div class="modal fade" id="confirm_order">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="/controller" method="post">
            <input type="hidden" name="command" value="confirm_order">
            <input type="hidden" name="order_id" value="${order_info.order.id}">
              <div class="modal-header">
                <h4 class="modal-title">Confirm order</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
              </div>

              <div class="modal-body">
                Are you sure you want to confirm this order?
              </div>

              <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-success ml-2">Confirm</button>
              </div>
          </form>
        </div>
      </div>
    </div>

    <%-- reject order modal --%>
    <div class="modal fade" id="reject_order">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="/controller" method="post">
            <input type="hidden" name="command" value="reject_order">
            <input type="hidden" name="order_id" value="${order_info.order.id}">
              <div class="modal-header">
                <h4 class="modal-title">Reject order</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
              </div>

              <div class="modal-body">
                Are you sure you want to reject this order?
                <p>This will reject all other pending requests for this car, which intersect the given time interval.</p>
              </div>

              <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-success ml-2">Reject</button>
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
