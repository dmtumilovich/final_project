<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:redirect url = "/main" />

<head>
    <fmt:setLocale value = "${sessionScope.local}" />
    <fmt:setBundle basename = "local" var = "loc" />
    <fmt:message bundle = "${loc}" key = "local.locbutton.name.ru" var = "ru_button" />
    <fmt:message bundle = "${loc}" key = "local.locbutton.name.en" var = "en_button" />
    <fmt:message bundle = "${loc}" key = "local.loginbutton.name" var = "login_button" />
    <fmt:message bundle = "${loc}" key = "local.signupbutton.name" var = "signup_button" />
    <fmt:message bundle = "${loc}" key = "local.logoutbutton.name" var = "logout_button" />

<body>

  <form action="controller" method="post">
    <input type="hidden" name="command" value="change_lang">
    <input type="hidden" name="local" value="ru" />
  </form>

  <form action="controller" method="post">
    <input type="hidden" name="command" value="change_lang">
    <input type="hidden" name="local" value="en"/>
    <input type="submit" value="${en_button}">
    <input type="" name="" value="">
  </form>

  <c:out value = "${username}" /> <br>

  <c:if test = "${empty username}">
    <a href="/jsp/login.jsp">${login_button}</a>
    <a href="/jsp/registration.jsp">${signup_button}</a>
  </c:if>

  <c:if test = "${not empty username}">
    <form action="controller" method="post">
      <input type="hidden" name="command" value="logout">
      <input type="submit" value="${logout_button}">
    </form>
  </c:if>

</body>
</html>
