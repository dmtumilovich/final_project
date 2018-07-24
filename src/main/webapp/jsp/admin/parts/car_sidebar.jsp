<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value = "${sessionScope.local}" />
<fmt:setBundle basename = "local" var = "loc" />

<div class="navbar bg-light navbar-light rounded">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a href="/controller?command=show_car_table" class="nav-link">List</a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link">Other info</a>
    </li>
  </ul>
</div>
