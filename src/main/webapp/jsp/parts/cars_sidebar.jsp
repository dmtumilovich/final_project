<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar bg-light navbar-light rounded">
  <%-- <span class="navbar-brand">Cars</span> --%>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a href="/controller?command=show_cars&date_start=${date_start}&date_end=${date_end}" class="nav-link">All</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=show_cars&car_class=premium&date_start=${date_start}&date_end=${date_end}" class="nav-link">Premium</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=show_cars&car_class=standart&date_start=${date_start}&date_end=${date_end}" class="nav-link">Standart</a>
    </li>
    <li class="nav-item">
      <a href="/controller?command=show_cars&car_class=economy&date_start=${date_start}&date_end=${date_end}" class="nav-link">Economy</a>
    </li>

  </ul>
</div>
