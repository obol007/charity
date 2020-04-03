<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="head.jsp" %>

    <title>Admin Page</title>
</head>

<body>
<header>
    <%@include file="adminNav.jsp" %>
</header>


    <table border="1">

        <tr>
            <th>Użytkownik</th>
            <th>Email</th>
            <%--            <th>Liczba dotacji</th>--%>
            <%--            <th>Liczba worków</th>--%>
        </tr>
<%--        <c:forEach items="${users}" var="user">--%>
<%--            <tr>--%>
<%--                <td>${user.fullName}</td>--%>
<%--                <td>${user.email}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

    </table>












<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>