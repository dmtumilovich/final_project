<%@ include file="/jsp/parts/bundle.jsp" %>

<fmt:message bundle="${loc}" key="local.cars-sidebar.text.all" var="nav_all" />
<fmt:message bundle="${loc}" key="local.cars-sidebar.text.premium" var="nav_premium" />
<fmt:message bundle="${loc}" key="local.cars-sidebar.text.standart" var="nav_standart" />
<fmt:message bundle="${loc}" key="local.cars-sidebar.text.economy" var="nav_economy" />

<div class="navbar bg-light navbar-light rounded">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a href="/controller?command=show_cars&date_start=${date_start}&date_end=${date_end}" class="nav-link">${nav_all}</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=show_cars&car_class=premium&date_start=${date_start}&date_end=${date_end}" class="nav-link">${nav_premium}</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=show_cars&car_class=standart&date_start=${date_start}&date_end=${date_end}" class="nav-link">${nav_standart}</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=show_cars&car_class=economy&date_start=${date_start}&date_end=${date_end}" class="nav-link">${nav_economy}</a>
    </li>

  </ul>
</div>
