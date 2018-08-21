<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta>
    <title>Car</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>

    <jsp:include page = "/jsp/admin/parts/header.jsp" />

    <main role="main" class = "container">
      <div class="row">
        <div class="col-2 float-left">
          <jsp:include page = "/jsp/admin/parts/car_sidebar.jsp" />
        </div>
        <div class="col-10 float-left">
          <div class="card">
            <div class="card-header">
              <h4 class="card-title">${car.brand} ${car.model}</h4>
            </div>
            <div class="card-body">
              <table class="table">
                <thead class="table-head">
                  <h5>Characteristics</h5>
                </thead>
                <tbody>
                  <tr>
                    <td>Class:</td>
                    <td>${car.carClass}</td>
                  </tr>
                  <tr>
                    <td>Color:</td>
                    <td>${car.color}</td>
                  </tr>
                  <tr>
                    <td>Year:</td>
                    <td>${car.yearOfIssue}</td>
                  </tr>
                  <tr>
                    <td>Seats:</td>
                    <td>${car.numberOfSeats}</td>
                  </tr>
                  <tr>
                    <td>Engine volume:</td>
                    <td>${car.engineVolume}</td>
                  </tr>
                  <tr>
                    <td>Price per day:</td>
                    <td>${car.price}</td>
                  </tr>
                </tbody>
                <tfoot>
                  <td colspan="2">
                    <div class="row mx-auto">
                      <c:forEach items="${car.photos}" var = "photo">
                        <div class="col-4">
                          <img src="../../img/uploads/cars/${photo.url}" alt="" height="145px" width="260px" class="img-thumbnail">
                          <form action="/controller" method="post">
                            <input type="hidden" name="command" value="delete_car_photo">
                            <input type="hidden" name="photo_id" value="${photo.id}">
                            <button type="submit" class="btn btn-sm btn-light border btn-block mt-1">Delete</button>
                          </form>
                        </div>
                      </c:forEach>
                      <c:if test="${car.photos.size() < 3}">
                        <div class="col-4">
                          <button type="button" class="btn btn-md btn-success mt-5" data-toggle="modal" data-target="#add_photo">Add photo</button>
                        </div>
                      </c:if>
                    </div>
                  </td>
                </tfoot>
              </table>
            </div>
            <div class="card-footer">
              <div class="row float-right">
                <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#delete_car">Delete car</button>
                <button type="button" class="btn btn-md btn-light border ml-2" data-toggle="modal" data-target="#edit_car">Edit info</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>

    <%-- edit car modal --%>
    <div class="modal fade" id="edit_car">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="/controller" method="post">
            <input type="hidden" name="command" value="edit_car">
            <input type="hidden" name="car_id" value="${car.id}">
            <div class="modal-header">
              <h4 class="modal-title">Edit car info</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
              <table class="table table-borderless">
                <tr>
                  <td>Brand:</td>
                  <td><input type="text" name="edit_brand" class = "form-control form-control-sm" value="${car.brand}" required /></td>
                </tr>
                <tr>
                  <td>Model:</td>
                  <td><input type="text" name="edit_model" class = "form-control form-control-sm" value="${car.model}" required /></td>
                </tr>
                <tr>
                  <td>Class:</td>
                  <td>
                    <select class="custom-select custom-select-md" name="edit_class" required>
                      <option value="${car.carClass}" selected disabled>${car.carClass}</option>
                      <option value="premium">premium</option>
                      <option value="standart">standart</option>
                      <option value="economy">economy</option>
                    </select>
                  </td>
                </tr>
                <tr>
                  <td>Color:</td>
                  <td>
                    <select class="custom-select custom-select-md" name="edit_color" required>
                      <option value="${car.color}" selected disabled>${car.color}</option>
                      <option value="aqua">aqua</option>
                      <option value="black">beige</option>
                      <option value="black">black</option>
                      <option value="black">blue</option>
                      <option value="black">gray</option>
                      <option value="red">red</option>
                      <option value="silver">silver</option>
                      <option value="white">white</option>
                    </select>
                  </td>
                </tr>
                <tr>
                  <td>Year:</td>
                  <td><input type="text" name="edit_year" class = "form-control form-control-sm" value="${car.yearOfIssue}" required /></td>
                </tr>
                <tr>
                  <td>Seats:</td>
                  <td><input type="text" name="edit_seats" class = "form-control form-control-sm" value="${car.numberOfSeats}" required /></td>
                </tr>
                <tr>
                  <td>Engine volume:</td>
                  <td><input type="text" name="edit_engine_volume" class = "form-control form-control-sm" value="${car.engineVolume}" required /></td>
                </tr>
                <tr>
                  <td>Price:</td>
                  <td><input type="text" name="edit_price" class = "form-control form-control-sm" value="${car.price}" required /></td>
                </tr>
              </table>
            </div>

            <div class="modal-footer">
              <button type="submit" class="btn btn-success">Save</button>
            </div>
          </form>
        </div>
      </div>
    </div>


    <%-- add new photo dialog --%>
    <div class="modal fade" id="add_photo">
      <div class="modal-dialog">
        <form action="/upload" method="post" enctype="multipart/form-data">
          <input type="hidden" name="command" value="upload_car_photo">
          <input type="hidden" name="car_id" value="${car.id}">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Add new photo</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body mx-auto">
              <label class="btn btn-sm btn-light border mt-1">
                Browse photo<input type="file" name="car_photo" style="display: none;" required/>
              </label>
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-success">Upload</button>
            </div>
          </div>
        </form>
      </div>
    </div>


    <%-- delete car modal --%>
    <div class="modal fade" id="delete_car">
      <div class="modal-dialog">
        <div class="modal-content">
          <form action="/controller" method="post">
            <input type="hidden" name="command" value="delete_car">
            <input type="hidden" name="car_id" value="${car.id}">

            <div class="modal-header">
              <h4 class="modal-title">Delete car</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
              Are you sure you want to delete this car?
            </div>

            <div class="modal-footer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
              <button type="submit" class="btn btn-success ml-2">Yes</button>
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
