<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key = "local.profile-sidebar.text.profile" var="nav_profile" />
<fmt:message bundle="${loc}" key = "local.profile-sidebar.text.main" var="nav_main" />
<fmt:message bundle="${loc}" key = "local.profile-sidebar.text.orders" var="nav_orders" />
<fmt:message bundle="${loc}" key = "local.profile-sidebar.text.comments" var="nav_comments" />

<div class="navbar bg-light navbar-light rounded">
  <span class="navbar-brand">${nav_profile}</span>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a href="/controller?command=profile" class="nav-link">${nav_main}</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=orders" class="nav-link">${nav_orders}</a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link">${nav_comments}</a>
    </li>
  </ul>
</div>
