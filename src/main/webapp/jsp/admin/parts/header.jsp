<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value = "${sessionScope.local}" />
<fmt:setBundle basename = "local" var = "loc" />
<fmt:message bundle = "${loc}" key = "local.profile.text.title" var = "heading" />

<header>
  <nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-3">
    <div class="container">
      <a class="navbar-brand" href="/admin/panel">Admin panel</a>
      <ul class="navbar-nav ">
        <li class="nav-item">
          <a href="/controller?command=get_orders" class="nav-link">Orders</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link">Users</a>
        </li>
        <li class="nav-item">
          <a href="/controller?command=show_car_table&page=1" class="nav-link">Cars</a>
        </li>
        <li class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle" id="dropdown_language" data-toggle="dropdown">Language</a>
          <div class="dropdown-menu">
            <a href="/controller?command=change_lang&local=en" class="dropdown-item">English</a>
            <a href="/controller?command=change_lang&local=ru" class="dropdown-item">Russian</a>
          </div>
        </li>
        <form action="/controller" method="post">
          <input type="hidden" name="command" value="logout">
          <button type="submit" class="btn btn btn-outline-danger">Logout</button>
        </form>
      </ul>
    </div>
  </nav>
</header>
