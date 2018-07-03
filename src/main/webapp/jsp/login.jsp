<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
  <c:if test = "${not empty username}">
    <%-- <jsp:forward page = "index.jsp" /> --%>
    Username is
    <c:out value = "${username}" /> <br>
  </c:if>
  <form name="login" method="post" action="/controller">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <input type="text" name="username" value=""/> <br>
    Password:<br/>
    <input type="password" name="password" value=""/> <br>
    <input type="submit" value="Login"> <br>
  </form>
</body>
</html>
