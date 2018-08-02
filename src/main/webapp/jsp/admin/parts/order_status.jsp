<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value = "${sessionScope.local}" />
<fmt:setBundle basename = "local" var = "loc" />
<fmt:message bundle = "${loc}" key = "local.profile.text.title" var = "heading" />

<c:choose>
  <c:when test = "${order.status eq 'RENT'}">
    <span class="text-info">In rent</span>
  </c:when>
  <c:when test = "${order.status eq 'CONFIRMED'}">
    <span class="text-success">Confirmed</span>
  </c:when>
  <c:when test = "${order.status eq 'REJECTED'}">
    <span class="text-danger">Rejected</span>
  </c:when>
  <c:when test = "${order.status eq 'AWAITS'}">
    <span class="text-important">Awaits confirmation</span>
  </c:when>
  <c:when test = "${order.status eq 'OVER'}">
    <span class="text-warning">Rent is over</span>
  </c:when>
  <c:when test = "${order.status eq 'RETURNED'}">
    <span class="text-secondary">Returned</span>
  </c:when>
  <c:otherwise>
    <%-- ??? --%>
    <span>Unknown</span>
  </c:otherwise>
</c:choose>
