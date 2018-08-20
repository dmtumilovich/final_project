<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key = "local.edit-profile-sidebar.text.edit" var = "nav_edit" />
<fmt:message bundle="${loc}" key = "local.edit-profile-sidebar.text.main" var="nav_main" />
<fmt:message bundle="${loc}" key = "local.edit-profile-sidebar.text.password" var="nav_password" />

<div class="navbar bg-light navbar-light rounded">
  <span class="navbar-brand float-right">${nav_edit}</span>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a href="/controller?command=edit_profile" class="nav-link">${nav_main}</a>
    </li>
    <li class="nav-item">
      <a href="/user/password" class="nav-link">${nav_password}</a>
    </li>
  </ul>
</div>
