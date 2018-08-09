<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Cars</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>

    <jsp:include page = "/jsp/admin/parts/header.jsp" />

    <main role="main" class="container">
      <div class="row">
        <div class="col-2 float-left">
          <jsp:include page = "/jsp/admin/parts/car_sidebar.jsp" />
        </div>

        <div class="col-10 float-left">
          <div class="float-right mb-2">
            <button type="button" class="btn btn-md btn-success" data-toggle="modal" data-target="#add_car">Add new car</button>
          </div>

          <table class="table table-hover">
            <thead class="thead-light">
              <tr>
                <th>#</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Class</th>
                <th>Price</th>
                <th>Status</th>
                <th>Comments</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.car_list}" var = "car" varStatus="loop">
                <tr>
                  <td>${loop.index}</td>
                  <td>${car.brand}</td>
                  <td>${car.model}</td>
                  <td>${car.carClass}</td>
                  <td>${car.price}</td>
                  <td>${car.status}</td>
                  <td>${car.commentsCount}</td>
                  <td>
                    <a href="/controller?command=get_car_info&car_id=${car.carID}" class="btn btn-sm btn-light border mr-1">View</a>
                    <%-- <a href="#" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#delete_car">Delete</a> --%>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>

    </main>

    <%-- add car modal --%>
    <div class="modal fade" id="add_car">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="/controller" method="post">
            <input type="hidden" name="command" value="add_car">
            <div class="modal-header">
              <h4 class="modal-title">Add a car</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
              <table class="table table-borderless">
                <tr>
                  <td>Brand:</td>
                  <td><input type="text" name="add_brand" class = "form-control form-control-sm" required /></td>
                </tr>
                <tr>
                  <td>Model:</td>
                  <td><input type="text" name="add_model" class = "form-control form-control-sm" required /></td>
                </tr>
                <tr>
                  <td>Class:</td>
                  <td><input type="text" name="add_class" class = "form-control form-control-sm" required /></td>
                </tr>
                <tr>
                  <td>Color:</td>
                  <td><input type="text" name="add_color" class = "form-control form-control-sm" required /></td>
                </tr>
                <tr>
                  <td>Year:</td>
                  <td><input type="text" name="add_year" class = "form-control form-control-sm" required /></td>
                </tr>
                <tr>
                  <td>Seats:</td>
                  <td><input type="text" name="add_seats" class = "form-control form-control-sm" required /></td>
                </tr>
                <tr>
                  <td>Engine volume:</td>
                  <td><input type="text" name="add_engine_volume" class = "form-control form-control-sm" required /></td>
                </tr>
                <tr>
                  <td>Price:</td>
                  <td><input type="text" name="add_price" class = "form-control form-control-sm" required /></td>
                </tr>
              </table>
            </div>

            <div class="modal-footer">
              <button type="submit" class="btn btn-success">Add</button>
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
