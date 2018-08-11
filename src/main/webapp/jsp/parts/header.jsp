<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value = "${sessionScope.local}" />
<fmt:setBundle basename = "local" var = "loc" />

<header>
  <nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-3">
    <div class="container">
      <a href="/main" class="navbar-brand">Rent-a-car</a>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a href="/main" class="nav-link">Home</a>
        </li>
        <li class="nav-item">
          <a href="/controller?command=show_cars&page=1" class="nav-link">Cars</a>
        </li>
        <c:if test="${not empty user_id}">
          <li class="nav-item">
            <a href="/controller?command=profile" class="nav-link">Profile</a>
          </li>
        </c:if>
        <li class="nav-item">
          <a href="#" class="nav-link">About</a>
        </li>
        <li class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle" id="dropdown_language" data-toggle="dropdown">Language</a>
          <div class="dropdown-menu">
            <a href="/controller?command=change_lang&local=en" class="dropdown-item">English</a>
            <a href="/controller?command=change_lang&local=ru" class="dropdown-item">Russian</a>
          </div>
        </li>
        <li class="nav-item">
          <c:choose>
            <c:when test="${empty user_id}">
              <a href="/signin" class="btn btn-outline-success">Log In</a>
            </c:when>
            <c:when test="${not empty user_id}">
              <a href="/controller?command=logout" class="btn btn-outline-danger">Logout</a>
            </c:when>
          </c:choose>
        </li>
        <%-- <li class="nav-item">
          <a href="/signup" class="nav-link">Sign Up</a>
        </li> --%>
      </ul>
    </div>
  </nav>
</header>
