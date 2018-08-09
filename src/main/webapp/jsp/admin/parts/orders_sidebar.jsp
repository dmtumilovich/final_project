<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value = "${sessionScope.local}" />
<fmt:setBundle basename = "local" var = "loc" />

<div class="navbar bg-light navbar-light rounded">
  <span class="navbar-brand">Orders</span>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a href="/controller?command=get_orders" class="nav-link">All</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=get_orders&status=awaits" class="nav-link">Waiting for confirmation</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=get_orders&status=rent" class="nav-link">In rent</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=get_orders&status=confirmed" class="nav-link">Confirmed</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=get_orders&status=rejected" class="nav-link">Disapproved</a>
    </li>
  </ul>
</div>
