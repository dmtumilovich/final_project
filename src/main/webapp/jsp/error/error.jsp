<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>

<%@ include file="/jsp/parts/bundle.jsp" %>

<!DOCTYPE html>
<html>
  <head>

    <title>${title}</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/error.css">
    <link rel="stylesheet" href="../../css/style.css">
  </head>
  <body>

      <main role="main" class="container">
        <div id="clouds">
           <div class="cloud x1"></div>
           <div class="cloud x1_5"></div>
           <div class="cloud x2"></div>
           <div class="cloud x3"></div>
           <div class="cloud x4"></div>
           <div class="cloud x5"></div>
       </div>
       <div class='c'>
           <div class='_404'>404</div>
           <hr>
           <div class='_1'>THE PAGE</div>
           <div class='_2'>WAS NOT FOUND</div>
           <a class='btn' href='/main'>BACK TO MARS</a>
       </div>
      </main>

      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../../js/vendor/jquery-slim.min.js"><\/script>')</script>
      <script src="../../js/vendor/popper.min.js"></script>
      <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>
