<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key="local.car.text.title" var="title" />
<fmt:message bundle="${loc}" key="local.car.text.card-characteristics" var="card_charactirectics" />
<fmt:message bundle="${loc}" key="local.car.text.card-class" var="card_class" />
<fmt:message bundle="${loc}" key="local.car.text.card-color" var="card_color" />
<fmt:message bundle="${loc}" key="local.car.text.card-year" var="card_year" />
<fmt:message bundle="${loc}" key="local.car.text.card-seats" var="card_seats" />
<fmt:message bundle="${loc}" key="local.car.text.card-volume" var="card_volume" />
<fmt:message bundle="${loc}" key="local.car.text.card-price" var="card_price" />
<fmt:message bundle="${loc}" key="local.car.button.rent" var="button_rent" />
<fmt:message bundle="${loc}" key="local.car.button.login" var="button_login_to_rent" />
<fmt:message bundle="${loc}" key="local.car.text.comments-header" var="comments_header" />
<fmt:message bundle="${loc}" key="local.car.text.comments-count" var="comments_count" />
<fmt:message bundle="${loc}" key="local.car.button.comments-reset" var="button_reset" />
<fmt:message bundle="${loc}" key="local.car.button.comments-send" var="button_send" />
<fmt:message bundle="${loc}" key="local.car.button.comments-share" var="button_share" />
<fmt:message bundle="${loc}" key="local.car.button.comments-delete" var="button_delete" />

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${car.brand} ${car.model}</title>

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
                    <h5>${card_charactirectics}</h5>
                  </thead>
                  <tbody>
                    <tr>
                      <td>${card_class}:</td>
                      <td>${car.carClass}</td>
                    </tr>
                    <tr>
                      <td>${card_color}:</td>
                      <td>${car.color}</td>
                    </tr>
                    <tr>
                      <td>${card_year}:</td>
                      <td>${car.yearOfIssue}</td>
                    </tr>
                    <tr>
                      <td>${card_seats}:</td>
                      <td>${car.numberOfSeats}</td>
                    </tr>
                    <tr>
                      <td>${card_volume}:</td>
                      <td>${car.engineVolume}</td>
                    </tr>
                    <tr>
                      <td>${card_price}:</td>
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
                    <c:when test = "${(not empty user_id) and requestScope.is_available eq true}">
                      <a href="/controller?command=get_booking_info&car_id=${car.id}" class="btn btn-md btn-success">${button_rent}</a>
                    </c:when>
                    <c:when test = "${empty user_id}">
                      <a href="/signin" class="btn btn-md btn-success">${button_login_to_rent}</a>
                    </c:when>
                  </c:choose>
                </div>
              </div>
            </div>

            <%-- comments --%>
            <div class="row mt-5">
              <div class="col-12">
                <div class="page-header border-bottom">
                  <h2><small class="float-right">${comments_count}: ${requestScope.car.numberOfReviews}</small> ${comments_header} </h2>
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
                        <button type="reset" class="btn btn-md btn-light border mr-1">${button_reset}</button>
                        <button type="submit" class="btn btn-md btn-primary">${button_send}</button>
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
                          <a href="#" class="text-dark">${button_share}</a>
                          <c:if test="${review.userID eq sessionScope.user_id}">
                             - <a href="/controller?command=delete_review&review_id=${review.id}" class="text-dark">${button_delete}</a>
                          </c:if>
                        </small>
                      </p>
                    </div>
                    <div class="media-right font-weith-light">
                      <fmt:formatDate value = "${review.reviewDate}" pattern = "dd.MM.yyyy HH:mm" />
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
