<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Car</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
  </head>
  <body>
      <jsp:include page = "/jsp/parts/header.jsp" />

      <main role="main" class="container">
        <div class="row">
          <div class="col-2 float-left">
            <jsp:include page = "/jsp/parts/cars_sidebar.jsp" />
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
                      <td>${car.price}$</td>
                    </tr>
                  </tbody>
                  <tfoot>
                    <td colspan="2">
                      <div class="row mx-auto">
                        <c:forEach items="${car.photos}" var = "photo">
                          <div class="col-4">
                            <img src="../img/uploads/cars/${photo.url}" alt="" height="145px" width="260px" class="img-thumbnail">
                          </div>
                        </c:forEach>
                      </div>
                    </td>
                  </tfoot>
                </table>
              </div>
              <div class="card-footer">
                <div class="row float-right">
                  <c:choose>
                    <c:when test = "${not empty user_id}">
                      <a href="/controller?command=get_booking_info&car_id=${car.id}" class="btn btn-md btn-success">Rent now</a>
                    </c:when>
                    <c:when test = "${empty user_id}">
                      <a href="/signin" class="btn btn-md btn-success">Log in to rent</a>
                    </c:when>
                  </c:choose>
                </div>
              </div>
            </div>

            <%-- comments --%>
            <div class="row mt-5">
              <div class="col-12">
                <div class="page-header border-bottom">
                  <h2><small class="float-right">${requestScope.car.numberOfReviews} comments</small> Comments </h2>
                </div>

                <div class="mb-5 px-3">
                  <c:if test = "${not empty user_id}">
                    <form action="/controller" method="post">
                      <input type="hidden" name="command" value="add_review">
                      <input type="hidden" name="car_id" value="${car.id}">
                      <input type="hidden" name="user_id" value="${user.id}">

                      <div class="row my-2">
                        <textarea name="review_text" rows="3" placeholder="Your comment..." class="form-control"></textarea>
                      </div>
                      <div class="row float-right">
                        <button type="reset" class="btn btn-md btn-light border mr-1">Reset</button>
                        <button type="submit" class="btn btn-md btn-primary">Send</button>
                      </div>
                    </form>
                  </c:if>
                </div>


                <c:forEach items="${requestScope.car.reviewList}" var="review">
                  <div class="media border-bottom p-2">
                    <img src="../img/uploads/user/${review.userPhotoUrl}" alt="User photo" class="mr-3 mt-3 rounded-circle" style="width:50px;height:50px">
                    <div class="media-body">
                      <h6 class="font-weight-bold">@${review.username}</h6>
                      ${review.reviewText}
                      <p>
                        <small>
                          <a href="#" class="text-dark">Share</a>
                          <c:if test="${review.userID eq sessionScope.user_id}">
                             - <a href="/controller?command=delete_review&review_id=${review.id}" class="text-dark">Delete</a>
                          </c:if>
                        </small>
                      </p>
                    </div>
                    <div class="media-right font-weith-light">
                      ${review.reviewDate}
                    </div>
                  </div>
                </c:forEach>


              </div>

            </div>
          </div>
        </div>
      </main>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../js/vendor/popper.min.js"></script>
      <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
