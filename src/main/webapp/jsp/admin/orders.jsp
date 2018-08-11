<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Orders</title>

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
          <table class="table table-hover">
            <thead class="thead-light">
              <tr>
                <th>#</th>
                <th>id</th>
                <th>User ID</th>
                <th>Car ID</th>
                <th>Date start</th>
                <th>Date end</th>
                <th>Status</th>
                <th></th>
              </tr>
            </thead>

            <tbody>
              <c:forEach items = "${requestScope.order_list}" var = "order" varStatus = "loop">
                <tr>
                  <td>${(loop.index+1) + (page-1) * 10}</td>
                  <td>${order.id}</td>
                  <td>${order.userID}</td>
                  <td>${order.carID}</td>
                  <td><fmt:formatDate value = "${order.dateStart}" pattern = "dd.MM.yyyy hh:mm" /></td>
                  <td><fmt:formatDate value = "${order.dateEnd}" pattern = "dd.MM.yyyy hh:mm" /></td>
                  <td>
                    <c:choose>
                      <c:when test = "${order.status eq 'RENT'}">
                        <span class="text-info">In rent</span>
                      </c:when>
                      <c:when test = "${order.status eq 'CONFIRMED'}">
                        <span class="text-success">Confirmed</span>
                      </c:when>
                      <c:when test = "${order.status eq 'REJECTED'}">
                        <span class="text-danger">Rejected</span>
                      </c:when>
                      <c:when test = "${order.status eq 'AWAITS'}">
                        <span class="text-important">Awaits confirmation</span>
                      </c:when>
                      <c:when test = "${order.status eq 'OVER'}">
                        <span class="text-warning">Rent is over</span>
                      </c:when>
                      <c:when test = "${order.status eq 'RETURNED'}">
                        <span class="text-secondary">Returned</span>
                      </c:when>
                      <c:when test = "${order.status eq 'CANCELED'}">
                        <span class="text-danger">Canceled</span>
                      </c:when>
                      <c:otherwise>
                        <%-- ??? --%>
                        <span>Unknown</span>
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td>
                    <a href="/controller?command=get_order_info&order_id=${order.id}" class="btn btn-sm btn-light border">Check</a>
                  </td>
                </tr>
              </c:forEach>
              <%-- <tr>
                <td>0</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>30.07.18 12:00</td>
                <td>31.07.18 12:00</td>
                <td class="text-warning">Awaits confirmation</td>
                <td>
                  <a href="#" class="btn btn-sm btn-light border">Check</a>
                </td>
              </tr>
              <tr>
                <td>1</td>
                <td>1</td>
                <td>10</td>
                <td>8</td>
                <td>28.07.18 12:00</td>
                <td>30.07.18 12:00</td>
                <td class="text-success">In rent</td>
                <td>
                  <a href="#" class="btn btn-sm btn-light border">Check</a>
                </td>
              </tr>
              <tr>
                <td>2</td>
                <td>7</td>
                <td>4</td>
                <td>12</td>
                <td>27.07.18 12:00</td>
                <td>28.07.18 12:00</td>
                <td class="text-info">Rent is over</td>
                <td>
                  <a href="#" class="btn btn-sm btn-light border">Check</a>
                </td>
              </tr>
              <tr>
                <td>3</td>
                <td>2</td>
                <td>5</td>
                <td>3</td>
                <td>26.07.18 12:00</td>
                <td>28.07.18 12:00</td>
                <td class="text-info">Rent is over</td>
                <td>
                  <a href="#" class="btn btn-sm btn-light border">Check</a>
                </td>
              </tr>
              <tr>
                <td>4</td>
                <td>9</td>
                <td>6</td>
                <td>7</td>
                <td>25.07.18 12:00</td>
                <td>26.07.18 12:00</td>
                <td class="text-info">Rent is over</td>
                <td>
                  <a href="#" class="btn btn-sm btn-light border">Check</a>
                </td>
              </tr> --%>
            </tbody>
          </table>

          <c:choose>
            <c:when test = "${not empty status}">
              <c:url value="/controller?command=get_orders&status=${status}&page=##" var="url" />
            </c:when>
            <c:otherwise>
              <c:url value="/controller?command=get_orders&page=##" var="url" />
            </c:otherwise>
          </c:choose>
          <paginator:display currentPage="${page}" maxLinks="10" totalPages="${page_count}" url="${url}" />

        </div>
      </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../js/vendor/popper.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
