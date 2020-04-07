<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../user_admin/head.jsp" %>

    <title>Administrators</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>


<section>
    <div class="users">



        <a href="/admin/admins/add" class="btn--small btn--highlighted">Dodaj administratora</a>

        <h2>Użytkownicy:</h2>
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Imię i Nazwisko</th>
                <th>Email</th>
                <th>Dary</th>
                <th>Worki</th>
                <th colspan="4">Akcja</th>
            </tr>
            <%--                --%>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>0</td>
                    <td>0</td>
                    <td><a href="/admin/admins/edit/${user.id}" class="btn btn--small btn--highlighted">Edytuj</a> </td>
                    <td><a href="/admin/admins/delete/${user.id}" class="btn btn--small btn--highlighted">Usuń</a> </td>
                    <td><a href="/admin/admins/block/${user.id}" class="btn btn--small btn--highlighted">Zablokuj</a> </td>
                    <td><a href="/admin/password/${user.id}" class="btn btn--small btn--highlighted">Zmień hasło</a> </td>
              </tr>
            </c:forEach>
        </table>
    </div>
</section>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
