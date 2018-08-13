<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key="local.header.text.title" var="title" />
<fmt:message bundle="${loc}" key="local.header.text.nav-home" var="nav_home" />
<fmt:message bundle="${loc}" key="local.header.text.nav-cars" var="nav_cars" />
<fmt:message bundle="${loc}" key="local.header.text.nav-contact" var="nav_contact" />
<fmt:message bundle="${loc}" key="local.header.text.nav-login" var="nav_login" />
<fmt:message bundle="${loc}" key="local.header.text.nav-profile" var="nav_profile" />
<fmt:message bundle="${loc}" key="local.header.text.nav-logout" var="nav_logout" />
<fmt:message bundle="${loc}" key="local.header.text.nav-language" var="nav_language" />
<fmt:message bundle="${loc}" key="local.header.button.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.header.button.ru" var="ru_button" />

<header>
  <nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-3">
    <div class="container">
      <a href="/main" class="navbar-brand">${title}</a>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a href="/main" class="nav-link">${nav_home}</a>
        </li>
        <li class="nav-item">
          <a href="/find" class="nav-link">${nav_cars}</a>
        </li>
        <c:if test="${not empty user_id}">
          <li class="nav-item">
            <a href="/controller?command=profile" class="nav-link">${nav_profile}</a>
          </li>
        </c:if>
        <li class="nav-item">
          <a href="#" class="nav-link">${nav_contact}</a>
        </li>
        <li class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle" id="dropdown_language" data-toggle="dropdown">${nav_language}</a>
          <div class="dropdown-menu">
            <a href="/controller?command=change_lang&local=en" class="dropdown-item">${en_button}</a>
            <a href="/controller?command=change_lang&local=ru" class="dropdown-item">${ru_button}</a>
          </div>
        </li>
        <li class="nav-item">
          <c:choose>
            <c:when test="${empty user_id}">
              <a href="/signin" class="btn btn-outline-success">${nav_login}</a>
            </c:when>
            <c:when test="${not empty user_id}">
              <a href="/controller?command=logout" class="btn btn-outline-danger">${nav_logout}</a>
            </c:when>
          </c:choose>
        </li>
      </ul>
    </div>
  </nav>
</header>
