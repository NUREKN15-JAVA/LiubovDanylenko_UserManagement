<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" class="ua.nure.kn156.danylenko.User" scope="session" />
<html>
    <head>
        <title>User management. Details</title>
    </head>
    <body>
        First name: ${user.firstName}<br>
        Last name: ${user.lastName}<br>
        <fmt:parseDate value="${user.dateOfBirthd}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
        Date of birth <fmt:formatDate value="${parsedDate}" type="date" pattern="dd.MM.yyyy" dateStyle="medium"/><br>
        <form action="<%=request.getContextPath()%>/details" method="get">
            <input type="submit" value="Ok"  name="okButton" />
        </form>
        <c:if test="${requestScope.error != null}">
            <script>
            alert('${requestScope.error}');
            </script>
        </c:if>
    </body>
</html>